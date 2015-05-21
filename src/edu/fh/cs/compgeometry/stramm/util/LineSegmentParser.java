package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Parser for lines from a Fischer formatted file.
 */
public class LineSegmentParser {

    public Collection<LineSegment> readLineSegments(final File file) throws FileNotFoundException {
        return this.readLineSegments(file, false);
    }

    public Collection<LineSegment> readLineSegments(final File file, boolean sanitize) throws FileNotFoundException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        final Collection<LineSegment> lineSegments = new HashSet<>();

        try {
            if(sanitize) {
                while (reader.ready()) {
                    LineSegment currentSegment = readLine(reader.readLine());
                    // Same x value of line points, thus either vertical or equal points. Both is invalid
                    if(currentSegment.getPoint1().x == currentSegment.getPoint2().x) {
                        continue;
                    }
                    lineSegments.add(readLine(reader.readLine()));
                }
            }
            else {
                while (reader.ready()) {
                    lineSegments.add(readLine(reader.readLine()));
                }
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
