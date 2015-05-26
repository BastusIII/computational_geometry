package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

import java.util.Collection;

/**
 * Created by Basti on 26.05.2015.
 */
public interface SweepLine {

    void proceed();

    double getPosition();

    boolean finished();

    Collection<Intersection> getIntersections();

    EventList getEventList();

    Neighborhood getNeighborhood();
}
