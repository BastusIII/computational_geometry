package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Basti on 26.05.2015.
 */
public class SimpleNeighborhoodList extends AbstractNeighborhood {

    private List<Neighbor> neighborList = new ArrayList<>();

    @Override
    public Neighbor[][] toggleNeighbors(Neighbor first, Neighbor second) {
        int indexFirst = neighborList.indexOf(first);
        int indexSecond = neighborList.indexOf(second);
        // one or both of the lines have already ended
        if (indexFirst == -1 || indexSecond == -1) {
            return null;
        }

        neighborList.remove(indexFirst);
        neighborList.add(indexFirst, second);
        neighborList.remove(indexSecond);
        neighborList.add(indexSecond, first);

        // check if the order was alright
        if (indexFirst > indexSecond) {
            int temp = indexFirst;
            indexFirst = indexSecond;
            indexSecond = temp;
        }

        Neighbor[][] newRelations = new Neighbor[2][2];

        // not first element
        if (indexFirst > 0) {
            // second is now below
            newRelations[0] = new Neighbor[]{neighborList.get(indexFirst-1), neighborList.get(indexFirst)};
        }
        if (indexSecond < neighborList.size() - 1) {
            newRelations[1] = new Neighbor[]{neighborList.get(indexSecond), neighborList.get(indexSecond + 1)};
        }

        return newRelations;
    }

    @Override
    public Neighbor[][] addNeighbor(Neighbor neighbor, double xValue) {
        neighborList.add(neighbor);
        // TODO: update only necessary neighbors
        for (Neighbor n : neighborList) {
            n.updateYVal(xValue);
        }
        Collections.sort(neighborList, this);
        int index = neighborList.indexOf(neighbor);

        Neighbor[][] newRelations = new Neighbor[2][2];
        Neighbor[] invalidNeighbors = new Neighbor[2];
        if (index > 0) {
            newRelations[0] = new Neighbor[]{neighbor, neighborList.get(index - 1)};
            if (neighbor.getYVal() == neighborList.get(index - 1).getYVal()) {
                invalidNeighbors[0] = neighborList.get(index - 1);
            }
        }
        if (index < neighborList.size() - 1) {
            newRelations[1] = new Neighbor[]{neighbor, neighborList.get(index + 1)};
            if (neighbor.getYVal() == neighborList.get(index + 1).getYVal()) {
                invalidNeighbors[1] = neighborList.get(index + 1);
            }
        }
        for (Neighbor invalidNeighbor : invalidNeighbors) {
            if(invalidNeighbor != null) {
                this.addError("Invalid data. Line " + neighbor + " starts with the same y-value as its neighbor " + invalidNeighbor + " at the current sweep line position.");
            }
        }
        return newRelations;
    }



    @Override
    public Neighbor[][] removeNeighbor(Neighbor neighbor) {
        int index = neighborList.indexOf(neighbor);
        Neighbor[][] newRelations = new Neighbor[1][2];

        if (index > 0 && index < neighborList.size() - 1) {
            newRelations[0] = new Neighbor[] {neighborList.get(index - 1), neighborList.get(index + 1)};
        }
        neighborList.remove(neighbor);
        return newRelations;
    }
}
