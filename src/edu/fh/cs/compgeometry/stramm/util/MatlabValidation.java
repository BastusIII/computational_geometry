package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Basti on 29.05.2015.
 */
public class MatlabValidation {

    public static final String FILE_BASE_PATH = "data"+File.separator+"matlabValidation"+File.separator;

    public static void generateMatlabIntersectionValidationScript(Collection<? extends Collection<LineSegment>> intersections, String scriptName, int plot, boolean terminateAtFail) {
        PrintWriter out;
        try {
            out = generateScriptFile(scriptName);
        } catch (IOException e) {
            System.out.println("Error creating file: "+e.getLocalizedMessage());
            return;
        }
        out.println(generateBase());
        for(Collection<LineSegment> intersection: intersections) {
            out.println(generateScriptLine(intersection, plot, terminateAtFail));
        }
        out.close();
    }

    public static void generateMatlabIntersectionPointValidationScript(Collection<Intersection> intersections, String scriptName, int plot, boolean terminateAtFail) {
        PrintWriter out;
        try {
            out = generateScriptFile(scriptName);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getLocalizedMessage());
            return;
        }
        out.println(generateBase());
        for(Intersection intersection: intersections) {
            out.println(generateScriptLine(intersection, plot, terminateAtFail));
        }
        out.close();
    }

    private static String generateBase() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("import matlab.unittest.TestCase;").append(System.lineSeparator());
        stringBuilder.append("import matlab.unittest.constraints.AbsoluteTolerance;").append(System.lineSeparator());
        stringBuilder.append("testCase = TestCase.forInteractiveUse;").append(System.lineSeparator());
        stringBuilder.append("tolerance = AbsoluteTolerance(10e-10);");
        return stringBuilder.toString();
    }

    private static String generateScriptLine(Collection<LineSegment> intersection, int plot, boolean terminateAtFail) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("validateIntersection(");
        for(LineSegment lineSegment: intersection) {
            stringBuilder.append(parseLineSegment(lineSegment));
        }
        stringBuilder.append("testcase, ").append(plot).append(", ").append(terminateAtFail?1:0).append(");");
        return stringBuilder.toString();
    }

    private static String generateScriptLine(Intersection intersection, int plot, boolean terminateAtFail) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("validateIntersectionPoint(");
        for(LineSegment lineSegment: intersection.getLines()) {
            stringBuilder.append(parseLineSegment(lineSegment));
        }
        stringBuilder.append("[").append(intersection.getIntersectionPoint().x).append(" ").append(intersection.getIntersectionPoint().y).append("], ");
        stringBuilder.append("testcase, tolerance, ").append(plot).append(", ").append(terminateAtFail?1:0).append(");");
        return stringBuilder.toString();
    }

    private static PrintWriter generateScriptFile(String scriptName) throws IOException {
        String path = FILE_BASE_PATH+scriptName+".m";
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
