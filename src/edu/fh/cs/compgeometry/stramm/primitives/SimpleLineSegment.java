package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public class SimpleLineSegment implements LineSegment {

    public static final double THRESHOLD = 1e-12;

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
    public double ccw(Vec2d point) {
        double ccw = this.point1.y * point.x -
        this.point2.y * point.x +
        this.point2.x * point.y -
        this.point1.x * point.y -
        this.point1.y * point2.x +
        this.point1.x * point2.y;
        return Math.abs(ccw) >= THRESHOLD ? ccw : 0;
    }

    @Override
    public boolean isCrossing(LineSegment lineSegment) {
        boolean checkSideSelf = this.ccw(lineSegment.getPoint1()) * this.ccw(lineSegment.getPoint2()) >= 0;
        boolean checkSideGiven = lineSegment.ccw(this.getPoint1()) * lineSegment.ccw(this.getPoint2()) >= 0;
        return !(checkSideSelf && checkSideGiven);
    }

    @Override
    public String toString() {
        return point1.toString() + ":" + point2.toString();
    }
}
