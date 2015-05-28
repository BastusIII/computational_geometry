package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public abstract class AbstractEvent implements Event {

    private final SweepLine sweepLine;

    private final List<Neighbor> myNeighbors = new ArrayList<>();

    private final double xValue;

    protected AbstractEvent(SweepLine sweepLine, double xValue, Neighbor... neighbors) {
        this.sweepLine = sweepLine;
        this.xValue = xValue;
        myNeighbors.addAll(Arrays.asList(neighbors));
    }

    @Override
    public double getXVal() {
        return xValue;
    }

    @Override
    public SweepLine getSweepLine() {
        return sweepLine;
    }

    @Override
    public List<Neighbor> getMyNeighbors() {
        return myNeighbors;
    }

    @Override
    public void updateNeighbors() {
        for (Neighbor neighbor : this.getMyNeighbors()) {
            neighbor.updateYVal(this.getXVal());
            if (neighbor.getNeighborAbove() != null) {
                neighbor.getNeighborAbove().updateYVal(this.getXVal());
            }
            if (neighbor.getNeighborBelow() != null) {
                neighbor.getNeighborBelow().updateYVal(this.getXVal());
            }
        }
    }


    protected void checkIntersection(Neighbor myNeighbor, Neighbor neighbor) {
        if (neighbor != null) {
            Vec2d intersection = myNeighbor.calcIntersection(neighbor);
            // the intersection lies right of the SweepLine and thus has to be observed
            if (intersection != null && intersection.x > this.getXVal()) {
                Event event = new IntersectionEvent(this.getSweepLine(), intersection, myNeighbor, neighbor);
                getSweepLine().getEventList().addEvent(event);
            }
        }
    }
}
