package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;

import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public class SimplePolygon implements Polygon {

    private enum CrossingType {
        GOING_IN,
        GOING_OUT,
        NOT_CROSSING;
    }

    private final List<LineSegment> lineSegments;

    public SimplePolygon(List<LineSegment> lineSegments) {
        this.lineSegments = lineSegments;
    }


    @Override
    public List<LineSegment> getLines() {
        return lineSegments;
    }

    @Override
    public double getArea() {
        double area = 0.0;
        for (LineSegment line: lineSegments) {
            area += (line.getPoint1().x - line.getPoint2().x)
                    * (line.getPoint1().y + line.getPoint2().y) / 2.0;
        }
        return area;
    }

    @Override
    public boolean containsPoint(NamedPoint point) {
        // Works only for consecutive lines.
        Vec2d pointOutside = getPointOutside();
        LineSegment testLine = new SimpleLineSegment(pointOutside, point.getVec2D());
        int timesWentInside = 0;

        CrossingType lastTest = CrossingType.NOT_CROSSING;

        for(LineSegment line: getLines()) {
            if(testLine.isCrossing(line)) {
                if (line.ccw(pointOutside) < 0) {
                    if(lastTest != CrossingType.GOING_IN) {
                        timesWentInside++;
                        lastTest = CrossingType.GOING_IN;
                    } else {
                        lastTest = CrossingType.NOT_CROSSING;
                    }
                } else {
                    if(lastTest != CrossingType.GOING_OUT) {
                        timesWentInside--;
                        lastTest = CrossingType.GOING_OUT;
                    } else {
                        lastTest = CrossingType.NOT_CROSSING;
                    }
                }
            } else {
                lastTest = CrossingType.NOT_CROSSING;
            }
        }

        if (timesWentInside < 0 || timesWentInside > 1) {
            throw new RuntimeException("Point inside test failed. Counted " + timesWentInside + "going ins.");
        }

        return timesWentInside == 1;
    }

    private Vec2d getPointOutside() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;

        for(LineSegment line: getLines()) {
            minX = Math.min(Math.min(line.getPoint1().x, line.getPoint2().x), minX);
            minY = Math.min(Math.min(line.getPoint1().y, line.getPoint2().y), minY);
        }

        if (minX < 1000.0d - Double.MAX_VALUE || minY < 1000.0d - Double.MAX_VALUE) {
            throw new RuntimeException("getPointOutside() badly implemented!");
        }

        return new Vec2d(minX, minY);
    }
}
