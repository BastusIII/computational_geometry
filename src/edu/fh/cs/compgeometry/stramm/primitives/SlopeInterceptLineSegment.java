package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by Basti on 28.05.2015.
 */
public class SlopeInterceptLineSegment extends SimpleLineSegment implements InterceptLineSegment {

    private final double slope;
    private final double intercept;

    public SlopeInterceptLineSegment(Vec2d point1, Vec2d point2) {
        super(point1, point2);
        this.slope = calcSlope();
        this.intercept = calcYAxisIntercept();
    }

    private double calcSlope() {
        double xDiff = this.getPoint2().x - this.getPoint1().x;
        double yDiff = this.getPoint2().y - this.getPoint1().y;
        if (yDiff == 0.0) {
            return 0.0;
        }
        return yDiff / xDiff;
    }

    private double calcYAxisIntercept() {
        return this.getPoint1().y - this.getSlope() * this.getPoint1().x;
    }

    @Override
    public double getSlope() {
        return slope;
    }

    @Override
    public double getIntercept() {
        return intercept;
    }

    @Override
    // for a probably more efficient way, see http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect for algorithm details
    public Vec2d calcIntersection(InterceptLineSegment lineSegment) {
        // the lines are collinear
        if (this.getSlope() == lineSegment.getSlope()) {
            return null;
        }
        double xVal = (lineSegment.getIntercept() - this.getIntercept()) / (this.getSlope() - lineSegment.getSlope());
        double yVal = this.getSlope() * xVal + this.getIntercept();
        return new Vec2d(xVal, yVal);
    }
}
