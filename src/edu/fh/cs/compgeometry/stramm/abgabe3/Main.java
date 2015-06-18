package edu.fh.cs.compgeometry.stramm.abgabe3;

import edu.fh.cs.compgeometry.stramm.linesweep.LinearSweepLine;
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
 * Main class Abgabe 3 using line sweep algorithm.
 *
 * @author Created by Basti on 21.05.2015.
 */
public class Main {

    // Toggle debugging -> printing to screen
    public static final boolean DEBUG = false;
    // Toggle validation -> generate Matlab Validation Script
    private static final boolean VALIDATE = true;

    public static void main(String[] args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        final List<String> fileNames = new ArrayList<>();
        //fileNames.add("s_1000_1.dat");
        fileNames.add("s_1000_10.dat");
        //fileNames.add("s_10000_1.dat");
        //fileNames.add("s_100000_1.dat");

        for (String fileName : fileNames) {
            crossLinesFromFile(new File(pathToData + fileName));
        }
    }

    private static void crossLinesFromFile(final File file) {
        LineSegmentParser parser = new LineSegmentParser();
        Collection<LineSegment> lineSegments;
        try {
            lineSegments = parser.readLineSegments(file, true);
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
            // print the found intersections
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append("Following intersections were found:");
            stringBuffer.append(System.lineSeparator());
            for (Intersection intersection : sweepLine.getIntersections()) {
                stringBuffer.append(intersection);
                stringBuffer.append(System.lineSeparator());
            }
            System.out.println(stringBuffer.toString());
        }

        if (VALIDATE) {
            // generate matlab script to validate intersections
            MatlabValidation.generateMatlabIntersectionPointValidationScript(sweepLine.getIntersections(), "validate_sweep_line_", 2, true);
            System.out.println("Errors:");
            System.out.println();
            // print similar x values
            System.out.println("Problematic x values:");
            if (!sweepLine.getEventList().getErrors().isEmpty()) {
                System.out.println(sweepLine.getEventList().getErrors());
            }
            // print similar y values
            System.out.println("Problematic y values:");
            if (!sweepLine.getNeighborhood().getErrors().isEmpty()) {
                System.out.println(sweepLine.getNeighborhood().getErrors());
            }
            System.out.println();
            // print duplicates
            System.out.println("Duplicated intersections:");
            while (!sweepLine.getIntersections().isEmpty()) {
                Intersection intersection = sweepLine.getIntersections().remove(0);
                if (sweepLine.getIntersections().contains(intersection) || sweepLine.getIntersections().contains(new Intersection(intersection.getIntersectionPoint(), intersection.getLines().get(1), intersection.getLines().get(0)))) {
                    System.out.println("Duplicate intersection: " + intersection);
                }
            }
        }
    }
    /*
All cleaned for Segments having the same x-value for both points

Threshold: 1.0E-14
Lines crossing in .\data\s_1000_1.dat: 4
Time taken: 0.264

Threshold: 1.0E-14
Lines crossing in .\data\s_10000_1.dat: 725
Time taken: 4.084

Threshold: 1.0E-14
Lines crossing in .\data\s_100000_1.dat: 77105
Time taken: 350.859

Threshold: 1.0E-14
Lines crossing in .\data\s_1000_10.dat: 796
Time taken: 0.277
    */
}
