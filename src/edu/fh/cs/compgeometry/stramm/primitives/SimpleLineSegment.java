package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public class SimpleLineSegment implements LineSegment {


    private final Vec2d point1;

    private final Vec2d point2;

    public SimpleLineSegment(Vec2d point1, Vec2d point2) {
        this.point1 = point1;
        this.point2 = point2;
    }


    @Override
    public Vec2d getPoint1() {
        return point1;
    }

    @Override
    public Vec2d getPoint2() {
        return point2;
    }

    @Override
    public boolean isCCW(Vec2d point) {
        return false;
    }

    @Override
    public boolean isCrossing(LineSegment lineSegment) {
        return false;
    }
}
