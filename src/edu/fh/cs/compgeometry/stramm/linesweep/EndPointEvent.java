package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public class EndPointEvent extends AbstractEvent {

    Neighbor myNeighbor;

    public EndPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue);
        this.myNeighbor = neighbor;
    }

    @Override
    public boolean handle() {
        Neighbor[][] relations = this.getSweepLine().getNeighborhood().removeNeighbor(myNeighbor);
        return checkNewRelations(relations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndPointEvent that = (EndPointEvent) o;
        return myNeighbor == that.myNeighbor;

    }

    @Override
    public String toString() {
        return "EndPointEvent at x="+getXVal()+": "+myNeighbor;
    }
}
