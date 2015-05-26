package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

/**
 * Created by Basti on 26.05.2015.
 */
public class SimpleNeighbor extends SimpleLineSegment implements Neighbor {

    private double yVal = Double.NaN;

    public SimpleNeighbor(final LineSegment lineSegment) {
        super(lineSegment.getPoint1(), lineSegment.getPoint2());
    }

    @Override
    public double getYVal() {
        return this.yVal;
    }

    @Override
    public void updateYVal(final double xVal) {
        // calculate the intersection between the horizontal line x=xVal and the line segment.
        double x1_xVal = Math.abs(xVal - this.getPoint1().x);
        double xVal_x2 = Math.abs(this.getPoint2().x - xVal);
        double y1_y2 = Math.abs(this.getPoint2().y - this.getPoint1().y);
        this.yVal = y1_y2 * xVal_x2 / (x1_xVal + xVal_x2);
    }
}
