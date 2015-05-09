package edu.fh.cs.compgeometry.stramm.abgabe2;

import edu.fh.cs.compgeometry.stramm.util.SVGParser;

import java.io.File;

/**
 * Created by femy on 5/9/15.
 */
public class Main {

    public static void main(String... args) {
        final String pathToData = "." + File.separator + "data" + File.separator;
        SVGParser parser = new SVGParser();
        parser.readPolygons(new File(pathToData + "DeutschlandMitStaedten.svg"));
    }
}
