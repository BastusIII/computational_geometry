package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import java.util.Comparator;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighborhood extends Comparator<Neighbor>, ErrorList {

    boolean toggleNeighbors(Neighbor first, Neighbor second);

    void addNeighbor(Neighbor newNeighbor);

    void removeNeighbor(Neighbor neighbor);
}
