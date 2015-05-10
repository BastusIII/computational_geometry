package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by femy on 5/10/15.
 */
public class SVGLineParser {

    private final String d;

    private int currentPosition;

    private final Map<Character, PathCMD> symbolMap;

    private Vec2d M;

    private Vec2d lastPoint;

    private List<LineSegment> lineSegments = new ArrayList<>();

    public SVGLineParser(final String d) {
        this.d = d;
        symbolMap = new HashMap<>();
        for (PathCMD command : PathCMD.values()) {
            symbolMap.put(command.symbol, command);
        }
    }

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

    public List<LineSegment> parseLines() {
        while (currentPosition < d.length()) {
            if (symbolMap.containsKey(d.charAt(currentPosition))) {
                PathCMD command = symbolMap.get(d.charAt(currentPosition));
                switch (command) {
                    case MOVETO_ABS:
                        setMoveToAbs();
                        break;
                    case MOVETO_REL:
                        setMoveToRel();
                        break;
                    case LINETO_ABS:
                        addLineToAbs();
                        break;
                    case LINETO_REL:
                        addLineToRel();
                        break;
                    case HORIZONTALTO_ABS:
                        addHorizontalToAbs();
                        break;
                    case HORIZONTlTO_REL:
                        addHorizontalToRel();
                        break;
                    case CLOSEPATH_ABS:
                        closePathAbs();
                        break;
                    case CLOSEPATH_REL:
                        closePathRel();
                        break;
                    default:
                        throw new RuntimeException("GG");
                }
            } else {
                currentPosition++;
            }
        }
        return lineSegments;
    }

    private void closePathRel() {
        // Keine ahnung was relativer close path sein soll!
        closePathAbs();
    }

    private void closePathAbs() {
        addPoint(M);
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
}
