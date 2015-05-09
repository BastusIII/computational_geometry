package edu.fh.cs.compgeometry.stramm.util;

import com.sun.javafx.geom.Vec2d;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.nameds.SimpleNamedPolygon;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.primitives.SimpleLineSegment;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by femy on 5/9/15.
 */
public class SVGParser {

    public Collection<NamedPolygon> readPolygons(final File file) {
        Collection<NamedPolygon>  polygons = new HashSet<>();
        try {
            StreamTokenizer pathTokenizer = new StreamTokenizer(new FileInputStream(file));
            Pattern pathPattern = Pattern.compile("<path([^(/>)]*)/>", Pattern.DOTALL);
            Scanner pathScanner = new Scanner(file);
            String path;
            while ((path = pathScanner.findWithinHorizon(pathPattern, 0)) != null) {
                NamedPolygon state = parsePath(path);
                System.out.println(state);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return polygons;
    }

    private NamedPolygon parsePath(String path) {
        Pattern attributePattern = Pattern.compile("[^= ]+=\"[^\"]*\"", Pattern.DOTALL);
        Scanner attributeScanner = new Scanner(path);
        Map<String, String> attributeMap = new HashMap<>();
        String attribute;
        while ((attribute = attributeScanner.findWithinHorizon(attributePattern, 0)) != null) {
            String[] attributePair = attribute.split("=");
            attributeMap.put(attributePair[0], attributePair[1].substring(1,attributePair[1].length()-1));
        }
        System.out.println("! " + attributeMap.get("id"));
        if (attributeMap.containsKey("d")) {
            return new SimpleNamedPolygon(parseLines(attributeMap.get("d")), attributeMap.get("id"));
        }
        return null;
    }

    private List<LineSegment> parseLines(String d) {
        List<LineSegment> lineSegments = new ArrayList<>();
        String[] lines = d.split("\n");
        Vec2d M = null;
        Vec2d lastPoint = null;

        for (String line: lines) {
            if(line.startsWith("M")) {
                String[] coordinates = line.substring(1).split(",");
                M = new Vec2d(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
                lastPoint = M;
            }

            if(line.startsWith("l") || line.startsWith("L")) {
                String[] coordinates = line.substring(1).split(",");
                lineSegments.add(new SimpleLineSegment(lastPoint
                        , new Vec2d(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]))));
            }

            if(line.startsWith("z") || line.startsWith("Z")) {
                String[] coordinates = line.substring(1).split(",");
                lineSegments.add(new SimpleLineSegment(lastPoint, M));
            }

        }
        return lineSegments;
    }
}
