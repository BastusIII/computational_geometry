package edu.fh.cs.compgeometry.stramm.util;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Generate a matlab script that can be used to validate a collections if intersections.
 *
 * @author Created by Basti on 21.05.2015.
 */
public class MatlabValidation {

    /**
     * The path the script will be saved to.
     */
    public static final String FILE_BASE_PATH = "data" + File.separator + "matlabValidation" + File.separator;
    /**
     * The maximum numbers of intersections that will be plotted.
     */
    public static final int MAX_PLOTS = 20;

    /**
     * Generate script from a collection of LineSegments.
     *
     * @param intersections   the collection of collections of line segments that intersect.
     * @param scriptName      the name the script will be saved to in the base path.
     * @param plot            0: no plots | 1: plot only fails | 2: plot all
     * @param terminateAtFail set rue if the matlab script should stop at a fail.
     */
    public static void generateMatlabIntersectionValidationScript(Collection<? extends Collection<LineSegment>> intersections, String scriptName, int plot, boolean terminateAtFail) {
        PrintWriter out;
        try {
            out = generateScriptFile(scriptName);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getLocalizedMessage());
            return;
        }
        out.println(generateBase());
        for (Collection<LineSegment> intersection : intersections) {
            out.println(generateScriptLine(intersection, plot, terminateAtFail));
        }
        out.close();
    }

    /**
     * Generate script from a collection of intersections.
     *
     * @param intersections   collection of intersections.
     * @param scriptName      the name the script will be saved to in the base path.
     * @param plot            0: no plots | 1: plot only fails | 2: plot all
     * @param terminateAtFail set rue if the matlab script should stop at a fail.
     */
    public static void generateMatlabIntersectionPointValidationScript(Collection<Intersection> intersections, String scriptName, int plot, boolean terminateAtFail) {
        PrintWriter out;
        try {
            out = generateScriptFile(scriptName);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getLocalizedMessage());
            return;
        }
        out.println(generateBase());
        int counter = 0;
        for (Intersection intersection : intersections) {
            out.println(generateScriptLine(intersection, counter < MAX_PLOTS ? plot : 0, terminateAtFail));
            counter++;
        }
        out.close();
    }

    private static String generateBase() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("import matlab.unittest.TestCase;").append(System.lineSeparator());
        stringBuilder.append("import matlab.unittest.constraints.AbsoluteTolerance;").append(System.lineSeparator());
        stringBuilder.append("testcase = TestCase.forInteractiveUse;").append(System.lineSeparator());
        stringBuilder.append("tolerance = AbsoluteTolerance(10e-10);");
        return stringBuilder.toString();
    }

    private static String generateScriptLine(Collection<LineSegment> intersection, int plot, boolean terminateAtFail) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("validateIntersection(");
        for (LineSegment lineSegment : intersection) {
            stringBuilder.append(parseLineSegment(lineSegment));
        }
        stringBuilder.append("testcase, ").append(plot).append(", ").append(terminateAtFail ? 1 : 0).append(");");
        return stringBuilder.toString();
    }

    private static String generateScriptLine(Intersection intersection, int plot, boolean terminateAtFail) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("validateIntersectionPoint(");
        for (LineSegment lineSegment : intersection.getLines()) {
            stringBuilder.append(parseLineSegment(lineSegment));
        }
        stringBuilder.append("[").append(intersection.getIntersectionPoint().x).append(" ").append(intersection.getIntersectionPoint().y).append("], ");
        stringBuilder.append("testcase, tolerance, ").append(plot).append(", ").append(terminateAtFail ? 1 : 0).append(");");
        return stringBuilder.toString();
    }

    private static PrintWriter generateScriptFile(String scriptName) throws IOException {
        String path = FILE_BASE_PATH + scriptName + ".m";
        System.out.println(path);
        File file = new File(path);
        file.createNewFile();
        return new PrintWriter(file);
    }

    private static String parseLineSegment(LineSegment lineSegment) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(lineSegment.getPoint1().x).append(" ").append(lineSegment.getPoint2().x).append("]").append(", ");
        stringBuilder.append("[").append(lineSegment.getPoint1().y).append(" ").append(lineSegment.getPoint2().y).append("]").append(", ");
        return stringBuilder.toString();
    }
}
