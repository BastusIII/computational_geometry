package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.nameds.SimpleNamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.SimpleNamedPolygon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by femy on 5/9/15.
 */
public class SVGParser {

    private Pattern attributePattern = Pattern.compile("[^= ]+=\"[^\"]*\"", Pattern.DOTALL);

    private Pattern pathPattern = Pattern.compile("<path([^(/>)]*)/>", Pattern.DOTALL);

    private Collection<NamedPolygon> polygons = new HashSet<>();

    private Collection<NamedPoint> points = new HashSet<>();

    public void parseFile(final File file) {

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

    private void parsePath(String path) {
        Scanner attributeScanner = new Scanner(path);
        Map<String, String> attributeMap = new HashMap<>();
        String attribute;
        while ((attribute = attributeScanner.findWithinHorizon(attributePattern, 0)) != null) {
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
            System.out.println("Found polygon: " + polygon.toString());
            polygons.add(polygon);
        }

        // Add points.
        if (attributeMap.containsKey("sodipodi:type")) {
            NamedPoint point = new SimpleNamedPoint(new Vec2d(Double.parseDouble(attributeMap.get("sodipodi:cx"))
                    , Double.parseDouble(attributeMap.get("sodipodi:cx"))),
                    attributeMap.get("id"));
            System.out.println("Found point: " + point.toString());
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
