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

}
