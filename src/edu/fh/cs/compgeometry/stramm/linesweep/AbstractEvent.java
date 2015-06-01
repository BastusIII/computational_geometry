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

    private final double xValue;

    protected AbstractEvent(SweepLine sweepLine, double xValue) {
        this.sweepLine = sweepLine;
        this.xValue = xValue;
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
    public void checkIntersection(Neighbor myNeighbor, Neighbor neighbor) {
        if(myNeighbor != null && neighbor != null) {
            Vec2d intersection = myNeighbor.calcIntersection(neighbor);
            if (intersection != null) {
                Event event = new IntersectionEvent(this.getSweepLine(), intersection, myNeighbor, neighbor);
                getSweepLine().getEventList().addEvent(event);
            }
        }
    }
    @Override
    public boolean checkNewRelations(Neighbor[][] relations) {
        boolean newRelations = false;
        for(Neighbor[] relation: relations) {
            newRelations = relation[0] != null && relation[1] != null;
            this.checkIntersection(relation[0], relation[1]);
        }
        return newRelations;
    }
}
