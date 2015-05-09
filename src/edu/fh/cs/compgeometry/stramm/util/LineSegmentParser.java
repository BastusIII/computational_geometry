package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by femy on 4/16/15.
 */
public class LineSegmentParser {

    public Collection<LineSegment> readLineSegments(final File file) throws FileNotFoundException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        final Collection<LineSegment> lineSegments = new HashSet<>();

        try {
            while (reader.ready()) {
                lineSegments.add(readLine(reader.readLine()));
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return lineSegments;
    }

    public LineSegment readLine(final String line) {
        final StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
        final Vec2d point1 = new Vec2d(Double.parseDouble(tokenizer.nextToken())
                , Double.parseDouble(tokenizer.nextToken()));
        final Vec2d point2 = new Vec2d(Double.parseDouble(tokenizer.nextToken())
                , Double.parseDouble(tokenizer.nextToken()));

        return new SimpleLineSegment(point1, point2);
    }
}
