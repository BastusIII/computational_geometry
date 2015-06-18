package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

/**
 * A sweep line event.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface Event {

    /**
     * This method has to be called when the event occurs. It processes the event and manipulates the sweepline if new events were detected.
     */
    void handle();

    /**
     * Get the x value the event occurs at.
     *
     * @return the x val.
     */
    double getXVal();

    /**
     * Get the sweep line we are working on.
     *
     * @return the sweep line.
     */
    SweepLine getSweepLine();
}
