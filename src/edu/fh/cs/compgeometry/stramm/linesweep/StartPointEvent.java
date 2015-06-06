package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.linesweep.baseclasses.BaseEvent;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;

/**
 * Created by Basti on 21.05.2015.
 */
public class StartPointEvent extends BaseEvent {

    private Neighbor neighbor;

    public StartPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue);
        this.neighbor = neighbor;
    }

    @Override
    public void handle() {
        getSweepLine().getNeighborhood().addNeighbor(neighbor);
    }

    @Override
    public String toString() {
        return "StartPointEvent at x=" + getXVal() + ", " + neighbor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StartPointEvent that = (StartPointEvent) o;

        return !(neighbor != null ? !neighbor.equals(that.neighbor) : that.neighbor != null);

    }
}
