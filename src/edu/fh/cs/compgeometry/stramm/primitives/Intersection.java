package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Basti on 21.05.2015.
 */
public class Intersection {

    private Vec2d intersectionPoint;
    LineSegment[] lines;

    public Intersection(Vec2d intersectionPoint, LineSegment... lines) {
        this.intersectionPoint = intersectionPoint;
        this.lines = lines;
    }

    public Collection<? extends LineSegment> getLines() {

        return Arrays.asList(lines);
    }

    public void setLines(LineSegment... lines) {
        this.lines = lines;
    }

    public Vec2d getIntersectionPoint() {

        return intersectionPoint;
    }

    public void setIntersectionPoint(Vec2d intersectionPoint) {
        this.intersectionPoint = intersectionPoint;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        for (LineSegment lineSegment : this.getLines()) {
            stringBuffer.append(lineSegment);
            stringBuffer.append(" X ");
        }
        stringBuffer.delete(stringBuffer.length() - 3, stringBuffer.length());
        stringBuffer.append(" at ");
        stringBuffer.append(this.getIntersectionPoint());
        return stringBuffer.toString();
    }
}
