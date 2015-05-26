package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public interface LineSegment {

    /**
     * Get the first point of this line segment.
     *
     * @return
     */
    Vec2d getPoint1();

    /**
     * Get the second point of this line segment.
     *
     * @return
     */
    Vec2d getPoint2();

    /**
     * Return the ccw value of a given point to the line segment itsself.
     * <p>
     * If the ccw value is 0, this means the point lies on the line defined by the line segment.
     *
     * @param point the point.
     * @return the ccw value.
     */
    double ccw(Vec2d point);

    /**
     * Check if two lines are crossing, means have at least one point in common.
     *
     * @param lineSegment the line segment to check for crossing.
     * @return true if the lines cross, else false.
     */
    boolean isCrossing(LineSegment lineSegment);

    boolean isOnLine(Vec2d point);

    /**
     * Calculates the intersection point between this and the given line segment.
     *
     * @param lineSegment line segment.
     * @return the intersection point.
     */
    Vec2d calcIntersection(LineSegment lineSegment);
}
