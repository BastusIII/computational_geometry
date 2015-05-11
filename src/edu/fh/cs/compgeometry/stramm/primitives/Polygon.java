package edu.fh.cs.compgeometry.stramm.primitives;

import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public interface Polygon {

    List<LineSegment> getLines();

    double getArea();

    boolean containsPoint(NamedPoint point);
}
