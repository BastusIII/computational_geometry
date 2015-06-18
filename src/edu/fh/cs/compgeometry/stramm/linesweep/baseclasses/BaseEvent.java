package edu.fh.cs.compgeometry.stramm.linesweep.baseclasses;

import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Event;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;

/**
 * Base class for events.
 *
 * @author Created by Basti on 21.05.2015.
 */
public abstract class BaseEvent implements Event {

    private final SweepLine sweepLine;

    private final double xValue;

    protected BaseEvent(SweepLine sweepLine, double xValue) {
        this.sweepLine = sweepLine;
        this.xValue = xValue;
    }

    @Override
    public double getXVal() {
        return xValue;
    }

    @Override
    public SweepLine getSweepLine() {
        return sweepLine;
    }
}
