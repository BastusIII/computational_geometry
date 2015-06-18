package edu.fh.cs.compgeometry.stramm.abgabe2;

import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.util.ParserException;
import edu.fh.cs.compgeometry.stramm.util.SVGParser;

import java.io.File;

/**
 * Main class for Aufgabe 2.
 */
public class Main {

    public static void main(String... args) throws ParserException {
        final String pathToData = "." + File.separator + "data" + File.separator;
        SVGParser parser = new SVGParser();
        parser.parseFile(new File(pathToData + "DeutschlandMitStaedten.svg"));

        for (NamedPolygon polygon : parser.getPolygons()) {
            double area = polygon.getArea();
            System.out.println("Found " + polygon.getName() + " with a size of: " + area
                    + " approximately " + String.format("%.0f", area * 70550.19 / 60026.125) + " km².");
        }

        for (NamedPoint point : parser.getPoints()) {
            for (NamedPolygon polygon : parser.getPolygons()) {
                if (polygon.containsPoint(point)) {
                    System.out.println(point.getName() + " lays in: " + polygon.getName());
                }
            }
        }
    }
}
