package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.Comparator;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighborhood extends Comparator<Neighbor> {

    boolean toggleNeighbors(Neighbor first, Neighbor second);

    boolean addNeighbor(Neighbor newNeighbor);

    boolean removeNeighbor(Neighbor neighbor);
}
