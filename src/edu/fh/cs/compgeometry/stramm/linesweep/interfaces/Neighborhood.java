package edu.fh.cs.compgeometry.stramm.linesweep.interfaces;

import java.util.Comparator;

/**
 * A sorted list of neighbors designed for used in the sweep line algorithms.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface Neighborhood extends Comparator<Neighbor>, ErrorList {

    /**
     * Toggle the position of the given neighbors. Only toggles the neighbors if both neighbors are contained in the list and have not been removed before.
     * The relations between the neighbors are updated. Each new pair of neighbors are checked for intersections. All new intersections are added to the events list as intersection events.
     *
     * @param first  the first neighbor, after toggle is the second.
     * @param second the second neighbor, after toggle is the first.
     * @return true if the neighbors were toggled, thus both neighbors were contained in the list.
     */
    boolean toggleNeighbors(Neighbor first, Neighbor second);

    /**
     * Add a neighbor to the neighborhood. It is added sorted, based on its current y value.
     * The relations between the neighbors are updated. Each new pair of neighbors are checked for intersections. All new intersections are added to the events list as intersection events.
     *
     * @param newNeighbor the neighbor to add.
     */
    void addNeighbor(Neighbor newNeighbor);

    /**
     * Remove a neighbor from the neighborhood.
     * The relations between the neighbors are updated. Each new pair of neighbors are checked for intersections. All new intersections are added to the events list as intersection events.
     *
     * @param neighbor the neighbor to remove.
     */
    void removeNeighbor(Neighbor neighbor);
}
