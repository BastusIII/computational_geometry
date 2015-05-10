package edu.fh.cs.compgeometry.stramm.nameds;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 5/10/15.
 */
public class SimpleNamedPoint implements NamedPoint {

    private final Vec2d point;

    private final String name;

    public SimpleNamedPoint(Vec2d point, String name) {
        this.point = point;
        this.name = name;
    }

    @Override
    public double getX() {
        return point.x;
    }

    @Override
    public double getY() {
        return point.y;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name + " at " + point.x + "," + point.y;
    }
}
