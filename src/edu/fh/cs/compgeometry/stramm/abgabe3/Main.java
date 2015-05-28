package edu.fh.cs.compgeometry.stramm.abgabe3;

import edu.fh.cs.compgeometry.stramm.linesweep.SimpleEventList;
import edu.fh.cs.compgeometry.stramm.linesweep.SimpleNeighborhoodList;
import edu.fh.cs.compgeometry.stramm.linesweep.SimpleSweepLine;
import edu.fh.cs.compgeometry.stramm.linesweep.SweepLine;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;
import edu.fh.cs.compgeometry.stramm.util.LineSegmentParser;

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

    public static void main(String[] args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        final List<String> fileNames = new ArrayList<>();
        fileNames.add("s_1000_1.dat");
        //fileNames.add("s_10000_1.dat");
        //fileNames.add("s_100000_1.dat");
        //fileNames.add("test.dat");

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

        SweepLine sweepLine = new SimpleSweepLine(new SimpleEventList(), new SimpleNeighborhoodList(), lineSegments);

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
    }
    /*
    Threshold: 1.0E-14
    Lines crossing in .\data\s_1000_1.dat: 17
    Time taken: 0.156

    Threshold: 1.0E-14
    Lines crossing in .\data\s_10000_1.dat: 196
    Time taken: 7.4

    Threshold: 1.0E-14
    Lines crossing in .\data\s_100000_1.dat: 8665
    Time taken: 919.555
    */
}
