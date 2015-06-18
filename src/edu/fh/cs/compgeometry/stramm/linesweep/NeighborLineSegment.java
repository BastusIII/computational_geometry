package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SlopeInterceptLineSegment;

/**
 * Implementation of a neighbor line segment.
 *
 * @author Created by Basti on 21.05.2015.
 */
public class NeighborLineSegment extends SlopeInterceptLineSegment implements Neighbor {

    private double yVal = Double.NaN;
    private double lastUpdateXVal = Double.NaN;

    public NeighborLineSegment(final LineSegment lineSegment) {
        this(lineSegment.getPoint1(), lineSegment.getPoint2());
    }

    public NeighborLineSegment(final Vec2d point1, final Vec2d point2) {
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
        // xVal lies on one of the endpoints of the line
        else if (this.getPoint1().x == xVal) {
            this.yVal = this.getPoint1().y;
        } else if (this.getPoint2().x == xVal) {
            this.yVal = this.getPoint2().y;
        }
        // calculate the intersection between the horizontal line x=xVal and the line segment.
        else {
            this.yVal = this.getSlope() * xVal + this.getIntercept();
            this.lastUpdateXVal = xVal;
        }
    }

    @Override
    public void initYVal() {
        this.yVal = this.getPoint1().x < this.getPoint2().x ? this.getPoint1().y : this.getPoint2().y;
    }

    @Override
    public String toString() {
        return super.toString() + ", current y=" + this.yVal;
    }
}
