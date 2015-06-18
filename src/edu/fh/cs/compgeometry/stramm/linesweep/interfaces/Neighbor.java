package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import edu.fh.cs.compgeometry.stramm.primitives.InterceptLineSegment;

/**
 * A neighbor line segment containing an additional y value used for sorting and the x value it was updated at last.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface Neighbor extends InterceptLineSegment {

    double getYVal();

    /**
     * Calculate the y value of the line to a given x value.
     *
     * @param xVal
     */
    void updateYVal(double xVal);

    /**
     * Initialize the y value from the line segment.
     */
    void initYVal();
}
