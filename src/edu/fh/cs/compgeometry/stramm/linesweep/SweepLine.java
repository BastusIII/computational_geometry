package edu.fh.cs.compgeometry.stramm.linesweep;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public class SweepLine {

    private  final List<LineSegment> lineSegments = new ArrayList<>();

    private  final List<Event> events;

    public SweepLine(List<Event> events) {
        this.events = events;
    }

    public void changeNeighbors(LineSegment lineSegment1, LineSegment lineSegment2) {

    }

    public void insertLine(LineSegment lineSegment1) {

        // TODO: Einfügen an die richtige Stelle
        lineSegments.add(lineSegment1);
        // TODO: Schnittberechung der neuen Neighbors -> auslagern in subclass
    }

    public void removeLine(LineSegment lineSegment1) {

    }

    public void setX(double xValue) {
        // voodoo
    }
}
