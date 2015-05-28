package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public interface Event {

    void handle();

    double getXVal();

    SweepLine getSweepLine();

    List<Neighbor> getMyNeighbors();

    void updateNeighbors();
}
