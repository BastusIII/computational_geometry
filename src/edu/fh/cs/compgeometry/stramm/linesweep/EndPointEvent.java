package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public class EndPointEvent extends AbstractEvent {

    public EndPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue, neighbor);
    }

    @Override
    public void handle() {
        Neighbor myNeighbor = this.getMyNeighbors().get(0);
        this.getSweepLine().getNeighborhood().removeNeighbor(myNeighbor);
    }
}
