package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.linesweep.baseclasses.BaseEvent;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

/**
 * The intersection point event.
 *
 * @author Created by Basti on 21.05.2015.
 */
public class IntersectionEvent extends BaseEvent {

    // corresponding lower neighbor
    Neighbor lowerNeighbor;
    // corresponding upper neighbor
    Neighbor upperNeighbor;
    // The intersection point
    private Vec2d intersection;

    public IntersectionEvent(SweepLine sweepLine, Vec2d intersection, Neighbor lower, Neighbor upper) {
        super(sweepLine, intersection.x);
        this.intersection = intersection;
        this.lowerNeighbor = lower;
        this.upperNeighbor = upper;

    }

    @Override
    public void handle() {
        // did the intersection occur or has one line already ended?
        if (this.getSweepLine().getNeighborhood().toggleNeighbors(lowerNeighbor, upperNeighbor)) {
            this.getSweepLine().getIntersections().add(new Intersection(intersection, lowerNeighbor, upperNeighbor));
        }
    }

    @Override
    public String toString() {
        return "IntersectionEvent at x=" + getXVal() + ", " + lowerNeighbor + " X " + upperNeighbor + " at (" + intersection.x + "," + intersection.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntersectionEvent that = (IntersectionEvent) o;

        if (lowerNeighbor != null ? !lowerNeighbor.equals(that.lowerNeighbor) : that.lowerNeighbor != null)
            return false;
        return !(upperNeighbor != null ? !upperNeighbor.equals(that.upperNeighbor) : that.upperNeighbor != null);
    }
}
