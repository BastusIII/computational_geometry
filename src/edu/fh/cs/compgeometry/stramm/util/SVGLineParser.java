package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LinePolygon;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.Polygon;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser for d-elements of SVG Paths.
 */
public class SVGLineParser {

    private final String d;

    private final Map<Character, Runnable> commandMap;

    private int currentPosition;

    private Vec2d M;

    private Vec2d lastPoint;

    private List<LineSegment> lineSegments = new ArrayList<>();

    private List<Polygon> polygons = new ArrayList<>();

    public SVGLineParser(final String d) {
        this.d = d;
        commandMap = new HashMap<>();
        initializeCommandMap();
    }

    /**
     * Creates the mapping from svg-codes to actions.
     */
    private void initializeCommandMap() {
        commandMap.put(PathCMD.MOVETO_ABS.symbol, this::setMoveToAbs);
        commandMap.put(PathCMD.MOVETO_REL.symbol, this::setMoveToRel);
        commandMap.put(PathCMD.LINETO_ABS.symbol, this::addLineToAbs);
        commandMap.put(PathCMD.LINETO_REL.symbol, this::addLineToRel);
        commandMap.put(PathCMD.HORIZONTALTO_ABS.symbol, this::addHorizontalToAbs);
        commandMap.put(PathCMD.HORIZONTlTO_REL.symbol, this::addHorizontalToRel);
        commandMap.put(PathCMD.VERTICALTO_ABS.symbol, () -> {
            throw new RuntimeException("Vertical lines not implemented.");
        });
        commandMap.put(PathCMD.VERTICALTO_REL.symbol, () -> {
            throw new RuntimeException("Vertical lines not implemented.");
        });
        commandMap.put(PathCMD.CLOSEPATH_ABS.symbol, this::closePathAbs);
        commandMap.put(PathCMD.CLOSEPATH_REL.symbol, this::closePathRel);
    }

    /**
     * Parse d-element. Fixes rotation of inner polygons.
     *
     * @return The parsed polygon.
     * @throws ParserException If polygons are not valid for area calculations.
     */
    public Polygon parseLines() throws ParserException {
        while (currentPosition < d.length()) {
            if (commandMap.containsKey(d.charAt(currentPosition))) {
                commandMap.get(d.charAt(currentPosition)).run();

            } else {
                currentPosition++;
            }
        }
        return mergePolygons();
    }

    private Polygon mergePolygons() throws ParserException {
        final List<LineSegment> finalLines = new ArrayList<>();
        for (Polygon polygon : polygons) {
            // Count how many times the polygon is contained in another.
            int numberOfContains = 0;
            for (Polygon otherPolygon : polygons) {
                if (!polygon.equals(otherPolygon)) {
                    if (polygon.overlapsPolygon(otherPolygon)) {
                        if (otherPolygon.containsPolygon(polygon)) {
                            numberOfContains++;
                        } else {
                            if (!polygon.containsPolygon(otherPolygon)) {
                                throw new ParserException("A polygon overlaps with another, but is not fully contained.");
                            }
                        }
                    }
                }
            }
            // Set area of polygons, that are an uneven amount inside of others negative.
            if (numberOfContains % 2 == 0) {
                polygon.setAreaPositive();
            } else {
                polygon.setAreaNegative();
            }

            finalLines.addAll(polygon.getLines());
        }
        return new LinePolygon(finalLines);
    }

    private void closePathRel() {
        // Keine ahnung was relativer close path sein soll!
        closePathAbs();
    }

    private void closePathAbs() {
        //addPoint(M);
        polygons.add(new LinePolygon(lineSegments));
        lineSegments = new ArrayList<>();
        currentPosition++;
    }

    private void addHorizontalToRel() {
        addPoint(new Vec2d(lastPoint.x + parseDouble(), lastPoint.y));
    }

    private void addHorizontalToAbs() {
        addPoint(new Vec2d(parseDouble(), lastPoint.y));
    }

    private void addLineToRel() {
        addPoint(addVecs(lastPoint, new Vec2d(parseDouble(), parseDouble())));
    }

    private void addLineToAbs() {
        addPoint(new Vec2d(parseDouble(), parseDouble()));
    }

    private void setMoveToRel() {
        M = addVecs(lastPoint, new Vec2d(parseDouble(), parseDouble()));
        lastPoint = M;
    }

    private void setMoveToAbs() {
        M = new Vec2d(parseDouble(), parseDouble());
        lastPoint = M;
    }

    private void addPoint(Vec2d point) {
        lineSegments.add(new SimpleLineSegment(lastPoint, point));
        lastPoint = point;
    }

    private double parseDouble(int start, int end) {
        return Double.parseDouble(d.substring(start, end));
    }

    private double parseDouble() {
        while (!isCharFromDouble(d.charAt(currentPosition))) {
            currentPosition++;
        }
        int length = 0;
        while (isCharFromDouble(d.charAt(currentPosition + length))) {
            length++;
        }
        double value = parseDouble(currentPosition, currentPosition + length);

        currentPosition += length;
        return value;
    }

    private boolean isCharFromDouble(char c) {
        return ((c >= '0' && c <= '9')
                || c == '-' || c == '+' || c == '.');
    }

    private Vec2d addVecs(Vec2d one, Vec2d two) {
        return new Vec2d(one.x + two.x, one.y + two.y);
    }

    /**
     * SVG path commands.
     */
    private enum PathCMD {
        MOVETO_ABS('M'),
        MOVETO_REL('m'),
        LINETO_ABS('L'),
        LINETO_REL('l'),
        VERTICALTO_ABS('V'),
        VERTICALTO_REL('v'),
        HORIZONTALTO_ABS('H'),
        HORIZONTlTO_REL('h'),
        CLOSEPATH_ABS('Z'),
        CLOSEPATH_REL('z');

        private char symbol;

        PathCMD(char symbol) {
            this.symbol = symbol;
        }

    }
}
