package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public class SimpleLineSegment implements LineSegment {

    // according to wiki, double has 15–17 significant decimal digits precision.
    public static final double THRESHOLD = 1e-14; //Math.pow(10.0, -12.0);

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
        // The size of the ccw-value is the length of the perpendicular (germ: Lot) from the point to the line
        // The threshold guarantees, that Points very near to a line will be detected as on the line
        return Math.abs(ccw) >= THRESHOLD ? ccw : 0;
    }

    @Override
    public boolean isCrossing(LineSegment lineSegment) {
        // No threshold test, data is even more fuzzy!!!
        if (this.point1.equals(this.point2)) {
            return lineSegment.isOnLine(this.point1);
        }
        double ccwOtherOne = this.ccw(lineSegment.getPoint1());
        double ccwOtherTwo = this.ccw(lineSegment.getPoint2());
        // special case 2: the line segments are colinear
        if (ccwOtherOne == 0 && ccwOtherTwo == 0) {
            return isColinearOverlapping(lineSegment);
        }
        double ccwThisOne = lineSegment.ccw(this.getPoint1());
        double ccwThisTwo = lineSegment.ccw(this.getPoint2());
        // check if the given line segment's points are on different sides of this line segment. They may lie on the segment, too.
        boolean checkSideSelf = ccwOtherOne * ccwOtherTwo <= 0.0;
        // check if this line segment's points are on different sides of the given line segment. They may lie on the segment, too.
        boolean checkSideGiven = ccwThisOne * ccwThisTwo <= 0.0;
        // the return value will be true, for each segment, the points of the other segment lie on different sides
        return checkSideSelf && checkSideGiven;
    }

    private boolean isColinearOverlapping(LineSegment lineSegment) {
        return isOnLine(lineSegment.getPoint1()) || isOnLine(lineSegment.getPoint2());
    }

    @Override
    public boolean isOnLine(Vec2d point) {
        if (this.ccw(point) != 0) {
            return false;
        }

        double lineLength = this.getPoint1().distance(this.getPoint2());
        double point1ToPoint = this.getPoint1().distance(point);
        double point2ToPoint = this.getPoint2().distance(point);

        return point1ToPoint <= THRESHOLD || point2ToPoint <= THRESHOLD
                || (point1ToPoint < lineLength && point2ToPoint < lineLength);
    }

    @Override
    public String toString() {
        return point1.x + ":" + point1.y + "->" + point2.x + ":" + point2.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleLineSegment that = (SimpleLineSegment) o;

        if (point1 != null ? !point1.equals(that.point1) : that.point1 != null) return false;
        return !(point2 != null ? !point2.equals(that.point2) : that.point2 != null);

    }
}
