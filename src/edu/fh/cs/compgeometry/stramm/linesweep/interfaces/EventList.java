package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import java.util.Comparator;

/**
 * A sorted list of events designed for used in the sweep line algorithms.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface EventList extends Comparator<Event>, ErrorList {

    /**
     * Add an event only if the events x value is greater than the current events x value.
     * Thus only if it is right of the sweep line and in the future. Use to add intersections.
     *
     * @param event the event to add.
     */
    void addEventRight(Event event);

    /**
     * Add an event to the event list. It is also added when its x Value is smaller than the current x value. Use for initialisation.
     *
     * @param event the event to add.
     */
    void addEvent(Event event);

    /**
     * Handle the current event. Thus the first event in the list. The event is deleted after processed.
     */
    void handleEvent();

    /**
     * get the current position of the event lsit.
     *
     * @return the position.
     */
    double getPosition();

    /**
     * Check if all events are processed.
     *
     * @return true if finished.
     */
    boolean isFinished();
}
