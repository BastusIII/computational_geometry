package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import edu.fh.cs.compgeometry.stramm.primitives.InterceptLineSegment;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighbor extends InterceptLineSegment {

    double getYVal();

    void updateYVal(double xVal);

    void initYVal();
}
