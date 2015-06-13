package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.linesweep.baseclasses.BaseEvent;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;

/**
 * Created by Basti on 21.05.2015.
 */
public class EndPointEvent extends BaseEvent {

    Neighbor neighbor;

    public EndPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue);
        this.neighbor = neighbor;
    }

    @Override
    public void handle() {
        getSweepLine().getNeighborhood().removeNeighbor(neighbor);
    }

    @Override
    public String toString() {
        return "EndPointEvent at x=" + getXVal() + ", " + neighbor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EndPointEvent that = (EndPointEvent) o;

        return !(neighbor != null ? !neighbor.equals(that.neighbor) : that.neighbor != null);
    }
}
