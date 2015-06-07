package edu.fh.cs.compgeometry.stramm.abgabe1;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;
import edu.fh.cs.compgeometry.stramm.util.LineSegmentParser;
import edu.fh.cs.compgeometry.stramm.util.MatlabValidation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Basti
 * Date: 16.04.15
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private static final boolean VALIDATE = true;
    private static final boolean DEBUG = false;

    public static void main(String[] args) {

        final String pathToData = "." + File.separator + "data" + File.separator;
        final List<String> fileNames = new ArrayList<>();
        //fileNames.add("s_1000_1.dat");
        fileNames.add("s_1000_1_sanitized.dat");
        //fileNames.add("s_1000_10.dat");
        //fileNames.add("s_10000_1.dat");
        //fileNames.add("s_100000_1.dat");
        //fileNames.add("test2.dat");

        System.out.println("Threshold: " + SimpleLineSegment.THRESHOLD);
        for (String fileName: fileNames) {
            crossLinesFromFile(new File(pathToData + fileName));
        }

    }

    private static void crossLinesFromFile(final File file) {
        LineSegmentParser parser = new LineSegmentParser();
        Collection<LineSegment> lineSegments;
        try {
            lineSegments = parser.readLineSegments(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        List<LineSegment> lineList = new ArrayList<>(lineSegments);
        List<List<LineSegment>> intersections = new ArrayList<>();

        long millis = System.currentTimeMillis();
        int count = 0;

        for (int i = 0; i < lineList.size(); i++) {
            for (int j = i + 1; j < lineList.size(); j++) {
                if(lineList.get(i).isCrossing(lineList.get(j))) {
                    count++;
                    intersections.add(new ArrayList<>(Arrays.asList(lineList.get(i), lineList.get(j))));
                }
            }
        }

        double timeTaken = (double)(System.currentTimeMillis() - millis) / 1000;

        if(DEBUG) {
            for(List<LineSegment> intersection: intersections) {
                System.out.println(lineList.get(0) + " X " + lineList.get(1));
            }
        }

        if(VALIDATE) {
            MatlabValidation.generateMatlabIntersectionValidationScript(intersections, "validate_simple_algorithm", 2, true);
        }

        System.out.println("Lines crossing in " + file.toString() + ": " + count);
        System.out.println("Time taken: " + timeTaken);
    }
}
/*
    Threshold: 1.0E-6
    Lines crossing in ./data/s_1000_1.dat: 11
    Time taken: 0.09
    Lines crossing in ./data/s_10000_1.dat: 732
    Time taken: 4.026
    Lines crossing in ./data/s_100000_1.dat: 77127
    Time taken: 364.471

    Threshold: 1.0E-14
    Lines crossing in .\data\s_1000_1.dat: 11
    Time taken: 0.077
    Lines crossing in .\data\s_10000_1.dat: 732
    Time taken: 4.503
    Lines crossing in .\data\s_100000_1.dat: 77126
    Time taken: 360.412
*/

