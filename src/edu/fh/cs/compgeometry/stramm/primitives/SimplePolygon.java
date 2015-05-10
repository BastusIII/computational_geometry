package edu.fh.cs.compgeometry.stramm.primitives;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public class SimplePolygon implements Polygon {

    private final List<LineSegment> lineSegments;

    public SimplePolygon(List<LineSegment> lineSegments) {
        this.lineSegments = lineSegments;
    }


    @Override
    public List<LineSegment> getLines() {
        return lineSegments;
    }

    @Override
    public double getArea() {
        double area = 0.0;
        for (LineSegment line: lineSegments) {
            area += (line.getPoint1().x - line.getPoint2().x)
                    * (line.getPoint1().y + line.getPoint2().y) / 2.0;
        }
        return area;
    }
}
