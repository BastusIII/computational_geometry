package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public interface Polygon {

    List<LineSegment> getLines();

    double getArea();

    boolean containsPoint(NamedPoint point);

    boolean containsPoint(Vec2d point);

    boolean containsPolygon(Polygon polygon);

    boolean overlapsPolygon(Polygon polygon);

    void swapDirection();

    void setAreaPositive();

    void setAreaNegative();

}
