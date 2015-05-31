package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public class StartPointEvent extends AbstractEvent {

    private Neighbor myNeighbor;

    public StartPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue);
        this.myNeighbor = neighbor;
    }

    @Override
    public boolean handle() {
        Neighbor[][] relations = this.getSweepLine().getNeighborhood().addNeighbor(myNeighbor, this.getXVal());
        return checkNewRelations(relations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartPointEvent that = (StartPointEvent) o;
        return myNeighbor == that.myNeighbor;
    }

    @Override
    public String toString() {
        return "StartPointEvent at x="+getXVal()+": "+myNeighbor;
    }
}
