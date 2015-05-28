package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.InterceptLineSegment;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighbor extends InterceptLineSegment {

    double getYVal();

    void updateYVal(double xVal);

    Neighbor getNeighborAbove();

    void setNeighborAbove(Neighbor neighborAbove);

    Neighbor getNeighborBelow();

    void setNeighborBelow(Neighbor neighborBelow);
}
