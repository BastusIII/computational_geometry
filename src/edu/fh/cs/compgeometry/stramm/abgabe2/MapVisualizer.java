package edu.fh.cs.compgeometry.stramm.abgabe2;

import edu.fh.cs.compgeometry.stramm.nameds.NamedPoint;
import edu.fh.cs.compgeometry.stramm.nameds.NamedPolygon;
import edu.fh.cs.compgeometry.stramm.primitives.LineSegment;
import edu.fh.cs.compgeometry.stramm.util.ParserException;
import edu.fh.cs.compgeometry.stramm.util.SVGParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Draw dat map.
 */
public class MapVisualizer extends JPanel {

    private final SVGParser parser;

    public MapVisualizer(SVGParser parser) {
        this.parser = parser;
    }

    public static void main(String[] args) throws ParserException {
        JFrame frame = new JFrame();
        frame.setTitle("Deutschland");
        frame.setSize(592, 801);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();
        final String pathToData = "." + File.separator + "data" + File.separator;
        SVGParser parser = new SVGParser();
        parser.parseFile(new File(pathToData + "DeutschlandMitStaedten.svg"));

        contentPane.add(new MapVisualizer(parser));
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setOpaque(true);
        for (NamedPolygon polygon : parser.getPolygons()) {
            for (LineSegment line : polygon.getLines()) {
                g.drawLine((int) line.getPoint1().x, (int) line.getPoint1().y
                        , (int) line.getPoint2().x, (int) line.getPoint2().y);
            }
        }

        for (NamedPoint point : parser.getPoints()) {
            g.drawString(point.getName(), (int) point.getX(), (int) point.getY());
        }

    }
}