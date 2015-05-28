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
    public boolean toggleNeighbors(Neighbor first, Neighbor second) {
        int indexFirst = neighborList.indexOf(first);
        int indexSecond = neighborList.indexOf(second);
        // one or both of the lines have already ended
        if (indexFirst == -1 || indexSecond == -1) {
            return false;
        }
        // check if the order is alright
        if (indexFirst > indexSecond) {
            int temp = indexFirst;
            indexFirst = indexSecond;
            indexSecond = temp;
        }
        neighborList.remove(indexFirst);
        neighborList.remove(indexSecond - 1);
        neighborList.add(indexFirst, second);
        neighborList.add(indexSecond, first);

        Neighbor nowBelow = neighborList.get(indexFirst);
        Neighbor nowAbove = neighborList.get(indexSecond);

        nowBelow.setNeighborBelow(nowAbove.getNeighborBelow());
        nowAbove.setNeighborAbove(nowBelow.getNeighborAbove());
        nowBelow.setNeighborAbove(nowAbove);
        nowAbove.setNeighborBelow(nowBelow);

        return true;
    }

    @Override
    public boolean addNeighbor(Neighbor neighbor) {
        neighborList.add(neighbor);
        Collections.sort(neighborList, this);
        int index = neighborList.indexOf(neighbor);
        if (index > 0) {
            if (neighbor.getYVal() < neighborList.get(index - 1).getYVal()) {
                throw new RuntimeException("The Neighbors are sorted wrong.");
            }
            neighbor.setNeighborBelow(neighborList.get(index - 1));
            neighborList.get(index - 1).setNeighborAbove(neighbor);
        }
        if (index < neighborList.size() - 1) {
            if (neighbor.getYVal() > neighborList.get(index + 1).getYVal()) {
                throw new RuntimeException("The neighbors are sorted wrong.");
            }
            neighbor.setNeighborAbove(neighborList.get(index + 1));
            neighborList.get(index + 1).setNeighborBelow(neighbor);
        }
        return true;
    }

    @Override
    public boolean removeNeighbor(Neighbor neighbor) {
        Neighbor above = neighbor.getNeighborAbove();
        Neighbor below = neighbor.getNeighborBelow();
        if (above != null) {
            above.setNeighborBelow(below);
        }
        if (below != null) {
            below.setNeighborAbove(below);
        }
        neighborList.remove(neighbor);
        return true;
    }
}
