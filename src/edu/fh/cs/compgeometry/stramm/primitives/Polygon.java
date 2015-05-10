package edu.fh.cs.compgeometry.stramm.primitives;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public interface Polygon {

    List<LineSegment> getLines();

    double getArea();
}
