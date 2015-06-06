package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.EventList;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighborhood;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;
import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public class LinearSweepLine implements SweepLine {

    private final Neighborhood neighborhood;

    private final EventList eventList;

    private final List<Intersection> intersections = new ArrayList<>();

    public LinearSweepLine(final Collection<LineSegment> lineSegments) {
        this.eventList = new LinearEventList();
        this.neighborhood = new LinearNeighborhoodList(this);
        for (LineSegment lineSegment : lineSegments) {
            this.insertEvents(lineSegment);
        }
    }

    @Override
    public void proceed() {
        eventList.handleEvent();
    }

    @Override
    public boolean finished() {
        return eventList.isFinished();
    }

    @Override
    public List<Intersection> getIntersections() {
        return intersections;
    }

    @Override
    public EventList getEventList() {
        return eventList;
    }

    @Override
    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    private void insertEvents(final LineSegment lineSegment) {
        edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor neighbor = new NeighborLineSegment(lineSegment);
        eventList.addEvent(new StartPointEvent(this, Math.min(lineSegment.getPoint1().x, lineSegment.getPoint2().x), neighbor));
        eventList.addEvent(new EndPointEvent(this, Math.max(lineSegment.getPoint1().x, lineSegment.getPoint2().x), neighbor));
    }
}
