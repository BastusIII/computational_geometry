package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public abstract class AbstractEvent implements Event {

    private final SweepLine sweepLine;

    private final double xValue;

    protected AbstractEvent(SweepLine sweepLine, double xValue) {
        this.sweepLine = sweepLine;
        this.xValue = xValue;
    }

    @Override
    public double getXVal() {
        return xValue;
    }
}
