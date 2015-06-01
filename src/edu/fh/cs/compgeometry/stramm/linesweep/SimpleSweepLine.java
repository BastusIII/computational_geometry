package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.Intersection;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Basti on 21.05.2015.
 */
public class SimpleSweepLine implements SweepLine {

    private final Neighborhood neighborhood;

    private final EventList eventList;

    private final Collection<Intersection> intersections = new ArrayList<>();

    public SimpleSweepLine(final EventList eventList, final Neighborhood neighborhood, final Collection<LineSegment> lineSegments) {
        this.eventList = eventList;
        this.neighborhood = neighborhood;
        for (LineSegment lineSegment : lineSegments) {
            this.insertEvents(lineSegment);
        }
    }

    @Override
    public void proceed() {
        eventList.handleEvent();
    }

    @Override
    public double getPosition() {
        return eventList.getPosition();
    }

    @Override
    public boolean finished() {
        return eventList.isFinished();
    }

    @Override
    public Collection<Intersection> getIntersections() {
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
        Neighbor neighbor = new SimpleNeighbor(lineSegment);
        eventList.addEvent(new StartPointEvent(this, Math.min(lineSegment.getPoint1().x, lineSegment.getPoint2().x), neighbor));
        eventList.addEvent(new EndPointEvent(this, Math.max(lineSegment.getPoint1().x, lineSegment.getPoint2().x), neighbor));
    }
}
