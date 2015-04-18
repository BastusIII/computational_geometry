package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;
import junit.framework.TestCase;
import org.junit.Test;


/**
 * Created by femy on 4/18/15.
 */
public class SimpleLineSegmentTest extends TestCase {

    @Test
    public void testCCW() {
//        puts "expect: 0"
//        puts ccw(0, 0, 2, 2, 1, 1)
        LineSegment lineSegment1 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        Vec2d point1 = new Vec2d(1.0, 1.0);
        assertCCW(lineSegment1, point1, 0.0);

//
//        puts "expect: > 0"
//        puts ccw(0, 0, 2, 2, 1, 2)
        LineSegment lineSegment2 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        Vec2d point2 = new Vec2d(1.0, 2.0);
        assertCCW(lineSegment2, point2, 1.0);
//
//        puts "expect: < 0"
//        puts ccw(0, 0, 2, 2, 2, 1)
        LineSegment lineSegment3 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        Vec2d point3 = new Vec2d(2.0, 1.0);
        assertCCW(lineSegment3, point3, -1.0);
//
//        puts "expect: true"
//        puts checkLines([0.0, 0.0, 2.0, 2.0], [0.0, 2.0, 2.0, 0.0])
        LineSegment lineSegment4 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        LineSegment lineSegment5 = new SimpleLineSegment(new Vec2d(0.0, 2.0), new Vec2d(2.0, 0.0));
        assertEquals("Lines should cross.", lineSegment4.isCrossing(lineSegment5), true);
//
//        puts "expect: true"
//        puts checkLines([0.0, 0.0, 2.0, 2.0], [0.0, 1.0, 1.0, 1.0])
        LineSegment lineSegment6 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        LineSegment lineSegment7 = new SimpleLineSegment(new Vec2d(0.0, 1.0), new Vec2d(1.0, 1.0));
        assertEquals("Lines should cross.", lineSegment6.isCrossing(lineSegment7), true);
//
//        puts "expect: false"
//        puts checkLines([0.0, 0.0, 2.0, 2.0], [0.25, 0.25, 0.75, 0.75])
        // denke doch
        LineSegment lineSegment8 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        LineSegment lineSegment9 = new SimpleLineSegment(new Vec2d(0.25, 0.25), new Vec2d(0.75, 0.75));
        assertEquals("Lines should cross.", lineSegment8.isCrossing(lineSegment9), true);
//
//        puts "expect: false"
//        puts checkLines([0.0, 0.0, 2.0, 2.0], [0.0, 1.0, 1.0, 2.0])
        LineSegment lineSegment10 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        LineSegment lineSegment11 = new SimpleLineSegment(new Vec2d(0.0, 1.0), new Vec2d(1.0, 2.0));
        assertEquals("Lines should not cross.", lineSegment10.isCrossing(lineSegment11), false);
//
//        puts "expect: false"
//        puts checkLines([0.0, 0.0, 2.0, 2.0], [1.0, 1.0, 3.0, 3.0])
        // denke doch
        LineSegment lineSegment12 = new SimpleLineSegment(new Vec2d(0.0, 0.0), new Vec2d(2.0, 2.0));
        LineSegment lineSegment13 = new SimpleLineSegment(new Vec2d(1.0, 1.0), new Vec2d(3.0, 3.0));
        assertEquals("Lines should cross.", lineSegment12.isCrossing(lineSegment13), true);
//
//        puts "expect: 0"
//        puts ccw(2, 1, 4, 2, 5, 2.5)
        LineSegment lineSegment14 = new SimpleLineSegment(new Vec2d(2.0, 1.0), new Vec2d(4.0, 2.0));
        Vec2d point4 = new Vec2d(5.0, 2.5);
        assertCCW(lineSegment14, point4, 0.0);
    }

    private void assertCCW(LineSegment lineSegment, Vec2d point, double ccw) {
        assertEquals(lineSegment.toString() + " ccw with " + point.toString() + "should be " +ccw +"."
                , ccw, Math.signum(lineSegment.ccw(point)));
    }
}
