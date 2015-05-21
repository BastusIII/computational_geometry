package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

import java.util.Collection;

/**
 * Created by Basti on 21.05.2015.
 */
public class Intersection {

    private Vec2d intersectionPoint;
    private Collection<LineSegment> lines;

    public Intersection(Vec2d intersectionPoint, Collection<LineSegment> lines) {
        this.intersectionPoint = intersectionPoint;
        this.lines = lines;
    }

    public Collection<LineSegment> getLines() {

        return lines;
    }

    public void setLines(Collection<LineSegment> lines) {
        this.lines = lines;
    }

    public Vec2d getIntersectionPoint() {

        return intersectionPoint;
    }

    public void setIntersectionPoint(Vec2d intersectionPoint) {
        this.intersectionPoint = intersectionPoint;
    }
}
