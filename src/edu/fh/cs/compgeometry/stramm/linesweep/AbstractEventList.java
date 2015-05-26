package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 26.05.2015.
 */
public abstract class AbstractEventList implements EventList {
    @Override
    public int compare(Event o1, Event o2) {
        return Double.compare(o1.getXVal(), o2.getXVal());
    }
}
