package edu.fh.cs.compgeometry.stramm.nameds;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LinePolygon;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.Polygon;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public class SimpleNamedPolygon implements NamedPolygon {

    private final Polygon polygon;

    private final String name;

    public SimpleNamedPolygon(Polygon polygon, String name) {
        this.polygon = polygon;
        this.name = name;
    }

    public SimpleNamedPolygon(List<LineSegment> lineSegments, String name) {
        this.polygon = new LinePolygon(lineSegments);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<LineSegment> getLines() {
        return polygon.getLines();
    }

    @Override
    public double getArea() {
        return polygon.getArea();
    }

    @Override
    public String toString() {
        return name + " with " + getLines().size() + " lines an area of " + getArea() + ".";
    }

    @Override
    public boolean containsPoint(NamedPoint point) {
        try {
            return polygon.containsPoint(point);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getLocalizedMessage() + "@" + getName() + ":" + point.getName());
        }
    }

    @Override
    public boolean containsPoint(Vec2d point) {
        return polygon.containsPoint(point);
    }

    @Override
    public boolean containsPolygon(Polygon polygon) {
        return polygon.containsPolygon(polygon);
    }

    @Override
    public boolean overlapsPolygon(Polygon polygon) {
        return polygon.overlapsPolygon(polygon);
    }

    @Override
    public void swapDirection() {
        polygon.swapDirection();
    }

    @Override
    public void setAreaPositive() {
        polygon.setAreaPositive();
    }

    @Override
    public void setAreaNegative() {
        polygon.setAreaNegative();
    }
}
