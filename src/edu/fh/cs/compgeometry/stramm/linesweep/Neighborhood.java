package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

/**
 * Created by Basti on 26.05.2015.
 * TODO: check if all methods are needed
 */
public interface Neighborhood {

    void toggleNeighbors(LineSegment lower);

    void toggleNeighbors(LineSegment first, LineSegment second);

    void addNeighborAbove(LineSegment newNeighbor, LineSegment below);

    void addNeighbor(LineSegment newNeighbor, int index);

    void removeNeighborAbove(LineSegment neighbor);

    void removeNeighbor(int index);

}
