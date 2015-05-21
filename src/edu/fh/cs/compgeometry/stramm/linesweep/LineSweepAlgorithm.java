package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.util.Collection;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public class LineSweepAlgorithm {

    List<Event> eventList;
    SweepLine sweepLine;
    public Collection<Intersection> findIntersections(Collection<LineSegment> lines) {

        while(!eventList.isEmpty()) {
            eventList.get(0).handle();
        }

        return null;
    }
}
