package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;

import java.util.List;

/**
 * A polygon.
 */
public interface Polygon {

    /**
     * Returns all lines of the border of this polygon.
     *
     * @return The border lines.
     */
    List<LineSegment> getLines();

    /**
     * Returns the area of the polygon.
     *
     * @return The area.
     */
    double getArea();

    /**
     * Checks if a point lies in the polygon.
     *
     * @param point The point to test.
     * @return true if the point lies within the polygon.
     */
    boolean containsPoint(NamedPoint point);

    /**
     * Checks if a point lies in the polygon.
     *
     * @param point The point to test.
     * @return true if the point lies within the polygon.
     */
    boolean containsPoint(Vec2d point);

    /**
     * Checks if a polygon is fully contained by this polygon.
     *
     * @param polygon The polygon to test.
     * @return true if the polygon is fully contained.
     */
    boolean containsPolygon(Polygon polygon);


    /**
     * Checks if the given polygon has overlapping areas with  this one.
     *
     * @param polygon The polygon to test.
     * @return true if the polygons have overlapping areas.
     */
    boolean overlapsPolygon(Polygon polygon);

    /**
     * Swaps the rotation direction of the polygon, thereby inverting its area.
     */
    void swapDirection();

    /**
     * Sets the area of this polygon positive.
     */
    void setAreaPositive();

    /**
     * Sets the area of this polygon negative.
     */
    void setAreaNegative();

}
