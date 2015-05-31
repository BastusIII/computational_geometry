package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

/**
 * Created by Basti on 21.05.2015.
 */
public class IntersectionEvent extends AbstractEvent {

    private Vec2d intersection;
    Neighbor lowerNeighbor;
    Neighbor upperNeighbor;

    public IntersectionEvent(SweepLine sweepLine, Vec2d intersection, Neighbor lower, Neighbor upper) {
        super(sweepLine, intersection.x);
        this.intersection = intersection;
        this.lowerNeighbor = lower;
        this.upperNeighbor = upper;

    }

    @Override
    public boolean handle() {
        Neighbor[][] relations = this.getSweepLine().getNeighborhood().toggleNeighbors(lowerNeighbor, upperNeighbor);
        if(relations != null) {
            this.getSweepLine().getIntersections().add(new Intersection(intersection, lowerNeighbor, upperNeighbor));
            return checkNewRelations(relations);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntersectionEvent that = (IntersectionEvent) o;

        return lowerNeighbor == that.lowerNeighbor && upperNeighbor == that.upperNeighbor;
    }

    @Override
    public String toString() {
        return "IntersectionEvent at x="+getXVal()+": "+lowerNeighbor+" X "+upperNeighbor;
    }
}
