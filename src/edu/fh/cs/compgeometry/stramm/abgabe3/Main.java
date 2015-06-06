package edu.fh.cs.compgeometry.stramm.abgabe3;

import edu.fh.cs.compgeometry.stramm.linesweep.*;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;
import edu.fh.cs.compgeometry.stramm.util.LineSegmentParser;
import edu.fh.cs.compgeometry.stramm.util.MatlabValidation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public class Main {

    public static final boolean DEBUG = false;
    private static final boolean VALIDATE = true;

    public static void main(String[] args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        final List<String> fileNames = new ArrayList<>();
        //fileNames.add("s_1000_1.dat");
        fileNames.add("s_1000_10.dat");
        //fileNames.add("s_10000_1.dat");
        //fileNames.add("s_100000_1.dat");
        //fileNames.add("test2.dat");

        for (String fileName : fileNames) {
            crossLinesFromFile(new File(pathToData + fileName));
        }
    }

    private static void crossLinesFromFile(final File file) {
        LineSegmentParser parser = new LineSegmentParser();
        Collection<LineSegment> lineSegments;
        try {
            lineSegments = parser.readLineSegments(file, false);
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        long millis = System.currentTimeMillis();

        SweepLine sweepLine = new LinearSweepLine(lineSegments);

        while (!sweepLine.finished()) {
            sweepLine.proceed();
        }

        double timeTaken = (double) (System.currentTimeMillis() - millis) / 1000;

        System.out.println("Threshold: " + SimpleLineSegment.THRESHOLD);
        System.out.println("Lines crossing in " + file.toString() + ": " + sweepLine.getIntersections().size());
        System.out.println("Time taken: " + timeTaken);

        if (DEBUG) {
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append("Following intersections were found:");
            stringBuffer.append(System.lineSeparator());
            for (Intersection intersection : sweepLine.getIntersections()) {
                stringBuffer.append(intersection);
                stringBuffer.append(System.lineSeparator());
            }
            System.out.println(stringBuffer.toString());
        }

        if(VALIDATE) {
            MatlabValidation.generateMatlabIntersectionPointValidationScript(sweepLine.getIntersections(), "validate_sweep_line_", 2, true);
            System.out.println("Errors:");
            System.out.println();
            System.out.println("Problematic x values:");
            if(!sweepLine.getEventList().getErrors().isEmpty()) {
                System.out.println(sweepLine.getEventList().getErrors());
            }
            System.out.println("Problematic y values:");
            if(!sweepLine.getNeighborhood().getErrors().isEmpty()) {
                System.out.println(sweepLine.getNeighborhood().getErrors());
            }
            System.out.println();
            System.out.println("Duplicated intersections:");
            while(!sweepLine.getIntersections().isEmpty()) {
                Intersection intersection = sweepLine.getIntersections().remove(0);
                if(sweepLine.getIntersections().contains(intersection) || sweepLine.getIntersections().contains(new Intersection(intersection.getIntersectionPoint(), intersection.getLines().get(1), intersection.getLines().get(0)))) {
                    System.out.println("Duplicate intersection: " + intersection);
                }
            }
        }
    }
    /*
    Threshold: 1.0E-14
    Lines crossing in .\data\s_1000_1.dat: 4
    Time taken: 0.265

    Threshold: 1.0E-14
    Lines crossing in .\data\s_10000_1.dat: 733
    Time taken: 4.14

    Threshold: 1.0E-14
    Lines crossing in .\data\s_100000_1.dat: 78958
    Time taken: 361.602

    Threshold: 1.0E-14
    Lines crossing in .\data\s_1000_10.dat: 797
    Time taken: 0.295
    */
}
