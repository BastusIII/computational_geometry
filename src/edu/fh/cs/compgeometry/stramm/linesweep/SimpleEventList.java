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
        // insert the event only if it is on the right side of the sweep position and not already contained
        if(this.getPosition() < event.getXVal() && !eventList.contains(event)) {
            eventList.add(event);
            Collections.sort(eventList, this);
        }
    }

    @Override
    public void handleEvent() {
        Event toHandle = eventList.get(0);
        boolean newRelations = toHandle.handle();

        eventList.remove(0);
        if(newRelations && getPosition() == toHandle.getXVal()) {
            this.addError("WARNING: EventList: Occurrence of events with the same x value: "+eventList.get(0)+" and "+toHandle);
        }
    }

    @Override
    public double getPosition() {
        if(eventList.isEmpty()) {
            return Double.NEGATIVE_INFINITY;
        }
        return eventList.get(0).getXVal();
    }

    @Override
    public boolean isFinished() {
        return eventList.isEmpty();
    }
}
