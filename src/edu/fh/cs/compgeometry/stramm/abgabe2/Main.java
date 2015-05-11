package edu.fh.cs.compgeometry.stramm.abgabe2;

import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.util.SVGParser;

import java.io.File;

/**
 * Created by femy on 5/9/15.
 */
public class Main {

    public static void main(String... args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        SVGParser parser = new SVGParser();
        parser.parseFile(new File(pathToData + "DeutschlandMitStaedten.svg"));

        for(NamedPolygon polygon: parser.getPolygons()) {
            System.out.println("Found " + polygon.getName() + " with a size of: " + polygon.getArea());
        }

        for(NamedPoint point: parser.getPoints()) {
            for (NamedPolygon polygon: parser.getPolygons()) {
                if (polygon.containsPoint(point)) {
                    System.out.println(point.getName() + " lays in: " + polygon.getName());
                }
            }
        }
    }
}
