package edu.fh.cs.compgeometry.stramm.linesweep;

/**
 * Created by Basti on 21.05.2015.
 */
public class IntersectionEvent extends AbstractEvent {

    protected IntersectionEvent(SweepLine sweepLine, double xValue) {
        super(sweepLine, xValue);
    }

    @Override
    public void handle() {
        // TODO: implement
    }
}
