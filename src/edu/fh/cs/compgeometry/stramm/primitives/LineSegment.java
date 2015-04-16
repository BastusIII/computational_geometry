package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public interface LineSegment {

    Vec2d getPoint1();

    Vec2d getPoint2();

    double ccw(Vec2d point);

    boolean isCrossing(LineSegment lineSegment);

}
