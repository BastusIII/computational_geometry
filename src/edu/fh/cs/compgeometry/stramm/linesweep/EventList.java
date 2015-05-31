package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.Comparator;

/**
 * Created by Basti on 26.05.2015.
 */
public interface EventList extends Comparator<Event>, ErrorList {

    void addEvent(Event event);

    void handleEvent();

    double getPosition();

    boolean isFinished();
}
