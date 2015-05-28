package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SlopeInterceptLineSegment;

/**
 * Created by Basti on 26.05.2015.
 */
public class SimpleNeighbor extends SlopeInterceptLineSegment implements Neighbor {

    Neighbor neighborAbove = null;
    Neighbor neighborBelow = null;
    private double yVal = Double.NaN;
    private double lastUpdateXVal = Double.NaN;

    public SimpleNeighbor(final LineSegment lineSegment) {
        this(lineSegment.getPoint1(), lineSegment.getPoint2());
    }

    public SimpleNeighbor(final Vec2d point1, final Vec2d point2) {
        super(point1, point2);
    }

    @Override
    public double getYVal() {
        return this.yVal;
    }

    @Override
    public void updateYVal(final double xVal) {
        // do not update if called for same xValue.
        if (this.lastUpdateXVal == xVal) {
            return;
        }
        // calculate the intersection between the horizontal line x=xVal and the line segment.
        this.yVal = this.getSlope() * xVal + this.getIntercept();
        this.lastUpdateXVal = xVal;
    }

    @Override
    public Neighbor getNeighborAbove() {
        return neighborAbove;
    }

    @Override
    public void setNeighborAbove(Neighbor neighborAbove) {
        this.neighborAbove = neighborAbove;
    }

    @Override
    public Neighbor getNeighborBelow() {
        return neighborBelow;
    }

    @Override
    public void setNeighborBelow(Neighbor neighborBelow) {
        this.neighborBelow = neighborBelow;
    }
}
