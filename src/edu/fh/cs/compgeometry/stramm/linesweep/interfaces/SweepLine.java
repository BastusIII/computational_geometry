package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

import java.util.List;

/**
 * Created by Basti on 26.05.2015.
 */
public interface SweepLine {

    void proceed();

    boolean finished();

    List<Intersection> getIntersections();

    EventList getEventList();

    Neighborhood getNeighborhood();
}
