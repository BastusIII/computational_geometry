package edu.fh.cs.compgeometry.stramm.linesweep;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.linesweep.baseclasses.BaseErrorList;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Event;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighbor;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.Neighborhood;
import edu.fh.cs.compgeometry.stramm.linesweep.interfaces.SweepLine;

import java.util.ArrayList;
import java.util.List;

/**
 * A linear implementation of the neighborhood list.
 *
 * @author Created by Basti on 21.05.2015.
 */
public class LinearNeighborhoodList extends BaseErrorList implements Neighborhood {

    // List containing rhe neighbored line segments
    private List<Neighbor> neighborList;
    // Instance of sweep line to access the event list.
    private SweepLine sweepLine;

    public LinearNeighborhoodList(SweepLine sweepLine) {
        this.neighborList = new ArrayList<>();
        this.sweepLine = sweepLine;
    }

    @Override
    public boolean toggleNeighbors(Neighbor lower, Neighbor upper) {

        int indexFirst = -1;
        int indexSecond = -1;
        int found = 0;
        for (int index = 0; index < neighborList.size() && found < 2; index++) {
            // checking the reference is good enough here
            if (neighborList.get(index).equals(lower)) {
                indexFirst = index;
                found++;
            } else if (neighborList.get(index).equals(upper)) {
                indexSecond = index;
                found++;
            }
        }
        // one of the elements was no longer in the list. It ended before.
        if (found < 2) {
            return false;
        }

        neighborList.remove(indexFirst);
        neighborList.add(indexFirst, upper);
        neighborList.remove(indexSecond);
        neighborList.add(indexSecond, lower);

        // check if the order was alright
        if (indexFirst > indexSecond) {
            int temp = indexFirst;
            indexFirst = indexSecond;
            indexSecond = temp;
        }

        // first is not first element
        if (indexFirst > 0) {
            Vec2d intersection = neighborList.get(indexFirst - 1).calcIntersection(neighborList.get(indexFirst));
            if (intersection != null) {
                Event event = new IntersectionEvent(sweepLine, intersection, neighborList.get(indexFirst - 1), neighborList.get(indexFirst));
                sweepLine.getEventList().addEventRight(event);
            }
        }

        // second is not last element
        if (indexSecond < neighborList.size() - 1) {
            Vec2d intersection = neighborList.get(indexSecond).calcIntersection(neighborList.get(indexSecond + 1));
            if (intersection != null) {
                Event event = new IntersectionEvent(sweepLine, intersection, neighborList.get(indexSecond), neighborList.get(indexSecond + 1));
                sweepLine.getEventList().addEventRight(event);
            }
        }
        return true;
    }

    @Override
    public void removeNeighbor(Neighbor neighbor) {

        int index;
        for (index = 0; index < neighborList.size(); index++) {
            // checking the reference is good enough here
            if (neighborList.get(index).equals(neighbor)) {
                break;
            }
        }

        // last element
        if (index == neighborList.size()) {
            throw new RuntimeException("NeighborLineSegment to remove is not in neighborhood.");
        }

        // if the removed neighbor is on the bottom or top, no new relations occur
        if (index > 0 && index < neighborList.size() - 1) {
            Vec2d intersection = neighborList.get(index - 1).calcIntersection(neighborList.get(index + 1));
            if (intersection != null) {
                Event event = new IntersectionEvent(sweepLine, intersection, neighborList.get(index - 1), neighborList.get(index + 1));
                sweepLine.getEventList().addEventRight(event);
            }
        }

        neighborList.remove(index);
    }

    @Override
    public void addNeighbor(Neighbor neighbor) {
        neighbor.initYVal();
        int index;
        for (index = 0; index < neighborList.size(); index++) {
            neighborList.get(index).updateYVal(Math.min(neighbor.getPoint1().x, neighbor.getPoint2().x));
            int compare = this.compare(neighborList.get(index), neighbor);
            if (compare == 0) {
                this.addError("WARNING: EventList: Occurrence of neighbors with the same y value: " + neighborList.get(index) + " and " + neighbor);
            } else if (compare > 0) {
                neighborList.add(index, neighbor);
                break;
            }
        }
        // last element
        if (index == neighborList.size()) {
            neighborList.add(neighbor);
        }
        // not first element
        if (index > 0) {
            Vec2d intersection = neighbor.calcIntersection(neighborList.get(index - 1));
            if (intersection != null) {
                Event event = new IntersectionEvent(sweepLine, intersection, neighborList.get(index - 1), neighbor);
                sweepLine.getEventList().addEventRight(event);
            }
        }
        // not last element
        if (index < neighborList.size() - 1) {
            Vec2d intersection = neighbor.calcIntersection(neighborList.get(index + 1));
            if (intersection != null) {
                Event event = new IntersectionEvent(sweepLine, intersection, neighbor, neighborList.get(index + 1));
                sweepLine.getEventList().addEventRight(event);
            }
        }
    }

    @Override
    public int compare(Neighbor o1, Neighbor o2) {
        return Double.compare(o1.getYVal(), o2.getYVal());
    }
}
