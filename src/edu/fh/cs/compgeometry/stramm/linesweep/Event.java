package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;

import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public interface Event {

    /**
     *
     * @return true, if neighbors have possibly changed, else false. This can be used to detect if two events with the same x value are problematic.
     */
    boolean handle();

    double getXVal();

    SweepLine getSweepLine();

    void checkIntersection(Neighbor myNeighbor, Neighbor neighbor);

    boolean checkNewRelations(Neighbor[][] relations);
}
