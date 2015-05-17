package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by femy on 5/9/15.
 */
public class LinePolygon implements Polygon {

    private enum CrossingType {
        GOING_IN,
        GOING_OUT,
        NOT_CROSSING;
    }

    private final List<LineSegment> lineSegments;

    public LinePolygon(List<LineSegment> lineSegments) {
        this.lineSegments = lineSegments;
    }


    @Override
    public List<LineSegment> getLines() {
        return lineSegments;
    }

    @Override
    public double getArea() {
        double area = 0.0;
        for (LineSegment line : lineSegments) {
            area += (line.getPoint1().x - line.getPoint2().x)
                    * (line.getPoint1().y + line.getPoint2().y) / 2.0;
        }
        return area;
    }

    @Override
    public boolean containsPoint(NamedPoint point) {
        return containsPoint(point.getVec2D());
    }

    @Override
    public boolean containsPoint(Vec2d point) {
        // Works only for consecutive lines.
        Vec2d pointOutside = getPointOutside();
        LineSegment testLine = new SimpleLineSegment(pointOutside, point);
        int timesWentInside = 0;

        CrossingType lastTest = CrossingType.NOT_CROSSING;

        // Check intersects for all lines.
        for (int i = 0; i < this.getLines().size(); i++) {
            LineSegment line = this.getLines().get(i);
            if (testLine.isCrossing(line)) {
                if (line.ccw(pointOutside) < 0) {
                    // If the test line crosses two consecutive lines in the same direction,
                    // it is assumed to have crossed the corner.

                    if (lastTest != CrossingType.GOING_IN
                            || (i > 1 && !line.getPoint1().equals(this.getLines().get(i - 1).getPoint2())) ) {
                        timesWentInside++;
                        lastTest = CrossingType.GOING_IN;
                    } else {
                        lastTest = CrossingType.NOT_CROSSING;
                    }

                } else {
                    if (lastTest != CrossingType.GOING_OUT
                            || (i > 1 && !line.getPoint1().equals(this.getLines().get(i - 1).getPoint2())) ) {
                        timesWentInside--;
                        lastTest = CrossingType.GOING_OUT;
                    }else{
                        lastTest = CrossingType.NOT_CROSSING;
                    }
                }
            } else {
                lastTest = CrossingType.NOT_CROSSING;
            }
        }

        // Fix if test line crosses the corner of the first and last line.
        LineSegment firstLine = this.getLines().get(0);
        LineSegment lastLine = this.getLines().get(this.getLines().size() - 1);
        if (testLine.isCrossing(firstLine) && testLine.isCrossing(lastLine)) {
            if (firstLine.ccw(pointOutside) > 0 && lastLine.ccw(pointOutside) > 0) {
                timesWentInside++;
            }
            if (firstLine.ccw(pointOutside) < 0 && lastLine.ccw(pointOutside) < 0) {
                timesWentInside--;
            }
        }

        if (timesWentInside < 0 || timesWentInside > 1) {
            throw new RuntimeException("Point inside test failed. Counted " + timesWentInside + "going ins.");
        }

        return timesWentInside == 1;
    }

    @Override
    public boolean containsPolygon(Polygon polygon) {
        for (LineSegment line : polygon.getLines()) {
            if (!this.containsPoint(line.getPoint1())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean overlapsPolygon(Polygon polygon) {
        for (LineSegment line : polygon.getLines()) {
            if (this.containsPoint(line.getPoint1())) {
                return true;
            }
        }
        // Check revers, if one point of the other polygon lies within this one and not vice versa.
        for (LineSegment line : this.getLines()) {
            if (polygon.containsPoint(line.getPoint1())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void swapDirection() {
        List<LineSegment> newLines = new ArrayList<>();
        for (LineSegment line : this.getLines()) {
            newLines.add(new SimpleLineSegment(line.getPoint2(), line.getPoint1()));
        }
        this.getLines().clear();
        for (int i = newLines.size(); i > 0; --i) {
            this.getLines().add(newLines.get(i - 1));
        }
    }

    @Override
    public void setAreaPositive() {
        if (getArea() <= 0) {
            swapDirection();
        }
    }

    @Override
    public void setAreaNegative() {
        if (getArea() >= 0) {
            swapDirection();
        }
    }

    private Vec2d getPointOutside() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;

        for (LineSegment line : getLines()) {
            minX = Math.min(Math.min(line.getPoint1().x, line.getPoint2().x), minX);
            minY = Math.min(Math.min(line.getPoint1().y, line.getPoint2().y), minY);
        }

        if (minX < 1000.0d - Double.MAX_VALUE || minY < 1000.0d - Double.MAX_VALUE) {
            throw new RuntimeException("getPointOutside() badly implemented!");
        }

        return new Vec2d(minX, minY);
    }
}
