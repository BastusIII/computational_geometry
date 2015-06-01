package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 28.05.2015.
 */
public abstract class AbstractNeighborhood extends SimpleErrorList implements Neighborhood {

    @Override
    public int compare(Neighbor o1, Neighbor o2) {
        return Double.compare(o1.getYVal(), o2.getYVal());
    }
}
