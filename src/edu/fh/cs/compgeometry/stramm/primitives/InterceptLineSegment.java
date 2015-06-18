package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Extends the line segment by functions to get the lines slope intercept form.
 *
 * @author Created by Basti on 21.05.2015.
 */
public interface InterceptLineSegment extends LineSegment {

    /**
     * Calculates the intersection point between this and the given line segment.
     *
     * @param lineSegment line segment.
     * @return the intersection point.
     */
    Vec2d calcIntersection(InterceptLineSegment lineSegment);

    double getSlope();

    double getIntercept();

}
