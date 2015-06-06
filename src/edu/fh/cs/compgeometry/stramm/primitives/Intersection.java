package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Basti on 21.05.2015.
 */
public class Intersection {

    LineSegment[] lines;
    private Vec2d intersectionPoint;

    public Intersection(Vec2d intersectionPoint, LineSegment... lines) {
        this.intersectionPoint = intersectionPoint;
        this.lines = lines;
    }

    public List<? extends LineSegment> getLines() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Intersection that = (Intersection) o;

        if (intersectionPoint != null ? !intersectionPoint.equals(that.intersectionPoint) : that.intersectionPoint != null)
            return false;
        return Arrays.equals(lines, that.lines);

    }

    @Override
    public int hashCode() {
        int result = intersectionPoint != null ? intersectionPoint.hashCode() : 0;
        result = 31 * result + (lines != null ? Arrays.hashCode(lines) : 0);
        return result;
    }
}
