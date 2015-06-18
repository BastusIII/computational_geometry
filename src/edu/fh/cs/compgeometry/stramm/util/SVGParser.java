package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.nameds.SimpleNamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.SimpleNamedPolygon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for SVG-files.
 */
public class SVGParser {

    private Pattern attributePattern = Pattern.compile("[^= ]+=\"[^\"]*\"");

    private Pattern pathPattern = Pattern.compile("<path([^(/>)]*)/>");

    private Collection<NamedPolygon> polygons = new HashSet<>();

    private Collection<NamedPoint> points = new HashSet<>();

    /**
     * Extracts all polygons and points from a given SVG-File.
     * @param file The SVG-File.
     * @throws ParserException File not found.
     */
    public void parseFile(final File file) throws ParserException {

        try {
            Scanner pathScanner = new Scanner(file);
            String path;
            while ((path = pathScanner.findWithinHorizon(pathPattern, 0)) != null) {
                parsePath(path);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }


    }

    /**
     * Converts a path element to polygon or point.
     * @param path The path-element as String.
     * @throws ParserException For non-valid paths.
     */
    private void parsePath(String path) throws ParserException {
        Map<String, String> attributeMap = new HashMap<>();
        Matcher matcher = attributePattern.matcher(path);
        while (matcher.find()) {
            String attribute = matcher.group();
            String[] attributePair = new String[2];
            int equalsPos = 0;
            while (attribute.charAt(equalsPos) != '=') {
                equalsPos++;
            }
            attributePair[0] = attribute.substring(0, equalsPos);
            attributePair[1] = attribute.substring(equalsPos + 2, attribute.length() - 1);
            attributeMap.put(attributePair[0], attributePair[1]);
        }
        // Add polygons.
        if (attributeMap.containsKey("d")) {
            SVGLineParser lineParser = new SVGLineParser(attributeMap.get("d"));
            NamedPolygon polygon = new SimpleNamedPolygon(lineParser.parseLines(), attributeMap.get("id"));
            polygons.add(polygon);
        }

        // Add points.
        if (attributeMap.containsKey("sodipodi:type")) {
            NamedPoint point = new SimpleNamedPoint(new Vec2d(Double.parseDouble(attributeMap.get("sodipodi:cx"))
                    , Double.parseDouble(attributeMap.get("sodipodi:cy"))),
                    attributeMap.get("id"));
            points.add(point);
        }
    }

    public Collection<NamedPolygon> getPolygons() {
        return polygons;
    }

    public Collection<NamedPoint> getPoints() {
        return points;
    }
}
