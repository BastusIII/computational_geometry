package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

/**
 * Created by Basti on 21.05.2015.
 */
public interface Event {

    void handle();

    double getXVal();

    SweepLine getSweepLine();
}
