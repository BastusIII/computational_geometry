package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighbor extends LineSegment {

    double getYVal();

    void updateYVal(double xVal);
}
