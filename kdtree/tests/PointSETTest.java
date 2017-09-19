import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PointSETTest {

    @Test
    public void testContains_input10() {

        PointSET ps = TestFileReader.parsePointSET("input10.txt");

        // query points
        Point2D inSet = new Point2D(0.144, 0.179);
        Point2D outSet = new Point2D(0.143, 0.179);

        // conditions
        assertTrue(ps.contains(inSet));
        assertFalse(ps.contains(outSet));
    }

    @Test
    public void testNearest_input10() {

        PointSET ps = TestFileReader.parsePointSET("input10.txt");

        // query point
        Point2D query = new Point2D(0.143, 0.179);

        // expected
        Point2D expected = new Point2D(0.144, 0.179);

        // result
        Point2D result = ps.nearest(query);

        // test
        assertTrue(expected.equals(result));
    }

    @Test
    public void testRange_input10() {
        PointSET ps = TestFileReader.parsePointSET("input10.txt");

        RectHV search = new RectHV(0.2, 0.2, 0.4, 0.6);

        Iterable<Point2D> result = ps.range(search);

        int count = 0;
        for (Point2D q : result) {
            count++;
        }

        assertEquals(count,2);
    }

    @Test
    public void testDraw() {
        PointSETVisualizer.drawInput("input10.txt");
        StdDraw.pause(10);
    }

}