package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;

import java.util.List;

/**
 * The sweep line containing the data structures for eventlist and neighborhood and the calculated intersections.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface SweepLine {

    /**
     * Proceed wil consume the next event and handle it.
     */
    void proceed();

    /**
     * Check if there are still events to process. Call as check before consume.
     *
     * @return true if the eventlist is not empty.
     */
    boolean finished();

    List<Intersection> getIntersections();

    EventList getEventList();

    Neighborhood getNeighborhood();
}
