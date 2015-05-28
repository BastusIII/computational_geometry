package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

/**
 * Created by Basti on 21.05.2015.
 */
public class IntersectionEvent extends AbstractEvent {

    private Vec2d intersection = null;

    public IntersectionEvent(SweepLine sweepLine, Vec2d intersection, Neighbor lower, Neighbor upper) {
        super(sweepLine, intersection.x, upper, lower);
        this.intersection = intersection;
        // Secure, that the Neighbors are in the right order
        if (lower.getYVal() > upper.getYVal()) {
            Neighbor temp = this.getMyNeighbors().remove(0);
            this.getMyNeighbors().add(temp);
        }
    }

    @Override
    public void handle() {
        Neighbor lower = this.getMyNeighbors().get(0);
        Neighbor upper = this.getMyNeighbors().get(1);
        if (this.getSweepLine().getNeighborhood().toggleNeighbors(lower, upper)) {
            // lower now on top after toggling, thus check intersection with neighbor above
            this.checkIntersection(lower, lower.getNeighborAbove());
            // upper vice versa
            this.checkIntersection(upper, upper.getNeighborBelow());

            this.getSweepLine().getIntersections().add(new Intersection(intersection, this.getMyNeighbors()));
        }
    }
}
