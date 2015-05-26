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

    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        final List<String> fileNames = new ArrayList<>();
        fileNames.add("s_1000_1.dat");
        //fileNames.add("s_10000_1.dat");
        //fileNames.add("s_100000_1.dat");

        System.out.println("Threshold: " + SimpleLineSegment.THRESHOLD);
        for (String fileName : fileNames) {
            crossLinesFromFile(new File(pathToData + fileName));
        }
    }

    private static void crossLinesFromFile(final File file) {
        LineSegmentParser parser = new LineSegmentParser();
        Collection<LineSegment> lineSegments = null;
        try {
            lineSegments = parser.readLineSegments(file, true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        long millis = System.currentTimeMillis();
        int count = 0;

        SweepLine sweepLine = new SimpleSweepLine(new SimpleEventList(), new SimpleNeighborhoodList(), lineSegments);

        while (!sweepLine.finished()) {
            sweepLine.proceed();
        }

        if (DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            for (Intersection intersection : sweepLine.getIntersections()) {
                for (LineSegment lineSegment : intersection.getLines()) {
                    stringBuffer.append(lineSegment);
                    stringBuffer.append(" X ");
                }
                stringBuffer.delete(stringBuffer.length() - 3, stringBuffer.length());
                stringBuffer.append(System.lineSeparator());
            }
        }


        double timeTaken = (double) (System.currentTimeMillis() - millis) / 1000;

        System.out.println("Lines crossing in " + file.toString() + ": " + count);
        System.out.println("Time taken: " + timeTaken);
    }

}
