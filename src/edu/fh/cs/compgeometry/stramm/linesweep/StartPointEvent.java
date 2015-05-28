package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public class StartPointEvent extends AbstractEvent {

    public StartPointEvent(SweepLine sweepLine, double xValue, Neighbor neighbor) {
        super(sweepLine, xValue, neighbor);
    }

    @Override
    public void handle() {
        Neighbor myNeighbor = this.getMyNeighbors().get(0);
        this.getSweepLine().getNeighborhood().addNeighbor(myNeighbor);
        this.updateNeighbors();
        if (myNeighbor.getNeighborAbove() != null && myNeighbor.getYVal() == myNeighbor.getNeighborAbove().getYVal() || myNeighbor.getNeighborBelow() != null && myNeighbor.getYVal() == myNeighbor.getNeighborBelow().getYVal()) {
            throw new RuntimeException("Invalid data. Line " + myNeighbor + " starts with the same y value as a current line.");
        }
        this.checkIntersection(myNeighbor, myNeighbor.getNeighborBelow());
        this.checkIntersection(myNeighbor, myNeighbor.getNeighborAbove());
    }
}
