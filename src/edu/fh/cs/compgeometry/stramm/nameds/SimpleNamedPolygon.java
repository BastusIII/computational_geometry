package edu.fh.cs.compgeometry.stramm.nameds;

import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.Polygon;
import edu.fh.cs.compgeometry.stramm.primitives.SimplePolygon;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public class SimpleNamedPolygon implements NamedPolygon{

    private final Polygon polygon;

    private final String name;

    public SimpleNamedPolygon(Polygon polygon, String name) {
        this.polygon = polygon;
        this.name = name;
    }

    public  SimpleNamedPolygon(List<LineSegment> lineSegments, String name) {
        this.polygon = new SimplePolygon(lineSegments);
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
}
