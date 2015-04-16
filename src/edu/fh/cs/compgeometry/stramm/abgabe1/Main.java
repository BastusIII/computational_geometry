package edu.fh.cs.compgeometry.stramm.abgabe1;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.util.LineSegmentParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        LineSegmentParser parser = new LineSegmentParser();
        Collection<LineSegment> lineSegments = null;
        try {
            lineSegments = parser.readLineSegments(new File("." + File.separator + "data" + File.separator + "s_1000_1.dat"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }

        List<LineSegment> lineList = new ArrayList<>(lineSegments);
        int count = 0;

        for(int i = 0; i<lineList.size();  i++) {
            for (int j=i+1; j<lineList.size(); j++) {
                count = lineList.get(i).isCrossing(lineList.get(j))?count+1:count;
            }
        }
        System.out.println(lineSegments);
        System.out.println("hoi: " + count + " lel " + lineList.get(1));
    }
}
