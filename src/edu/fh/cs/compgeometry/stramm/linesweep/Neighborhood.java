package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.Comparator;

/**
 * Created by Basti on 26.05.2015.
 */
public interface Neighborhood extends Comparator<Neighbor>, ErrorList {

    Neighbor[][] toggleNeighbors(Neighbor first, Neighbor second);

    Neighbor[][] addNeighbor(Neighbor newNeighbor, double xValue);

    Neighbor[][] removeNeighbor(Neighbor neighbor);
}
