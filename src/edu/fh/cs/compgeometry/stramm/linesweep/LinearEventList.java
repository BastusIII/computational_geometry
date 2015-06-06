package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.linesweep.baseclasses.BaseErrorList;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Event;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.EventList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basti on 26.05.2015.
 */
public class LinearEventList extends BaseErrorList implements EventList {

    private List<Event> eventList = new ArrayList<>();


    @Override
    public int compare(Event o1, Event o2) {
        return Double.compare(o1.getXVal(), o2.getXVal());
    }

    @Override
    public void addEventRight(Event event) {
        // insert the event only if it is on the right side of the sweep position and not already contained
        if (this.getPosition() < event.getXVal()) {
            addEvent(event);
        }
    }


    @Override
    public void addEvent(Event event) {
        int index;
        for (index = 0; index < eventList.size(); index++) {
            int compare = this.compare(eventList.get(index), event);
            // there is already an element with the same x value in the list
            if (compare == 0) {
                // same value is okay, cause its the same event, do not add it to the event list again
                if (event.equals(eventList.get(index))) {
                    break;
                }
                // there are 2 different events with the same x value, this can cause problems
                else {
                    this.addError("WARNING: EventList: Occurrence of events with the same x value: " + eventList.get(index) + " and " + event);
                }
            } else if (this.compare(eventList.get(index), event) > 0) {
                eventList.add(index, event);
                break;
            }
        }
        // add the event at the end
        if (index == eventList.size()) {
            eventList.add(event);
        }
    }

    @Override
    public void handleEvent() {
        eventList.get(0).handle();
        eventList.remove(0);
    }

    @Override
    public double getPosition() {
        if (eventList.isEmpty()) {
            return Double.NEGATIVE_INFINITY;
        }
        return eventList.get(0).getXVal();
    }

    @Override
    public boolean isFinished() {
        return eventList.isEmpty();
    }
}
