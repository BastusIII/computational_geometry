package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Basti on 26.05.2015.
 */
public class SimpleEventList extends AbstractEventList {

    private List<Event> eventList = new ArrayList<>();

    @Override
    public void addEvent(Event event) {
        eventList.add(event);
        Collections.sort(eventList, this);
    }

    @Override
    public void handleEvent() {
        eventList.get(0).handle();
        eventList.remove(0);
    }

    @Override
    public double getPosition() {
        return eventList.get(0).getXVal();
    }

    @Override
    public boolean isFinished() {
        return eventList.isEmpty();
    }
}
