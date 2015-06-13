package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by Basti on 28.05.2015.
 */
public class SlopeInterceptLineSegment extends SimpleLineSegment implements InterceptLineSegment {

    private double slope = Double.NaN;
    ;
    private double intercept = Double.NaN;
    private boolean initialized = false;

    public SlopeInterceptLineSegment(Vec2d point1, Vec2d point2) {
        super(point1, point2);
    }

    private void initialize() {
        this.slope = calcSlope();
        this.intercept = calcYAxisIntercept();
        initialized = true;
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
        return this.getPoint1().y - this.slope * this.getPoint1().x;
    }

    @Override
    public double getSlope() {
        if (!initialized) {
            initialize();
        }
        return slope;
    }

    @Override
    public double getIntercept() {
        if (!initialized) {
            initialize();
        }
        return intercept;
    }

    @Override
    // for a probably more efficient way, see http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect for algorithm details
    public Vec2d calcIntersection(InterceptLineSegment lineSegment) {
        if (lineSegment == null) {
            return null;
        }
        // the lines are collinear
        if (this.getSlope() == lineSegment.getSlope()) {
            return null;
        }
        double xVal = (lineSegment.getIntercept() - this.getIntercept()) / (this.getSlope() - lineSegment.getSlope());
        double yVal = this.getSlope() * xVal + this.getIntercept();
        return new Vec2d(xVal, yVal);
    }
}
