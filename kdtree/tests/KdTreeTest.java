import edu.princeton.cs.algs4.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KdTreeTest {

    @Test
    public void isEmpty() throws Exception {

        KdTree kd = TestFileReader.parseKdTree("assignmentsample.txt");

        assertFalse(kd.isEmpty());

        KdTree empty = new KdTree();

        assertTrue(empty.isEmpty());
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void contains() throws Exception {

        KdTree kd = TestFileReader.parseKdTree("assignmentsample.txt");

        Point2D query = new Point2D(0.4, 0.7);

        assertTrue(kd.contains(query));

        KdTree kd_input10 = TestFileReader.parseKdTree("input10.txt");

        Point2D query_input10 = new Point2D(0.320, 0.708);

        assertTrue(kd_input10.contains(query_input10));

        Point2D query_input10false = new Point2D(0.321, 0.708);

        assertFalse(kd_input10.contains(query_input10false));
    }

    @Test
    public void range() {
        KdTree kd = TestFileReader.parseKdTree("input10.txt");

        RectHV rect = new RectHV(0.05, 0.45, 0.1, 0.55);

        Iterable<Point2D> points = kd.range(rect);
        // can visually confirm using RangeSearchVisualizer
    }

    @Test
    public void testRangeInput5() {
        KdTree kd = TestFileReader.parseKdTree("input5.txt");

        RectHV query = new RectHV(0.31, 0.36, 0.4,0.37);

        Iterable<Point2D> result = kd.range(query);

        StdOut.println("Rectangle " + query.toString());
        for (Point2D q : result) {
            StdOut.println(q.toString());
        }
    }

    @Test
    public void testRangeInput5_2() {
        KdTree kd = TestFileReader.parseKdTree("input5.txt");

        RectHV query = new RectHV(0.2, 0.3, 0.5,0.7);

        Iterable<Point2D> result = kd.range(query);

        StdOut.println("Rectangle " + query.toString());
        for (Point2D q : result) {
            StdOut.println(q.toString());
        }
    }

    @Test
    public void nearest_100K() {
        KdTree kd = TestFileReader.parseKdTree("input100K.txt");

        Point2D query;
        Point2D result;
        for (int i = 0; i < 100; i++) {
            query = new Point2D(StdRandom.uniform(0.0,1.0), StdRandom.uniform(0.0,1.0));
            StdOut.println("Query Point: " + query.toString());
            result = kd.nearest(query);
            StdOut.println("Result: " + result.toString());
        }
    }

    @Test
    public void nearest_input5() {
        KdTree kd = TestFileReader.parseKdTree("input5.txt");

        Point2D query = new Point2D(0.47, 0.47);

        Point2D result = kd.nearest(query);

        Point2D expected = new Point2D(0.5, 0.4);

        assertTrue(result.equals(expected));
    }

    @Test
    public void nearest_input10() {
        KdTree kd = TestFileReader.parseKdTree("input10.txt");

        Point2D query = new Point2D(0.15, 0.38);
        Point2D result = kd.nearest(query);
        Point2D expected = new Point2D(0.083, 0.51);

        assertTrue(result.equals(expected));
    }

    @Test
    public void nearest_input10K() { // most rigorous test for nearest

        KdTree kd = TestFileReader.parseKdTree("input10K.txt");
        PointSET ps = TestFileReader.parsePointSET("input10K.txt");

        // initialize query points
        Point2D[] points = new Point2D[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
        }

        Point2D[] results_ps = new Point2D[100];
        Point2D[] results_kd = new Point2D[100];
        boolean[] eq = new boolean[100];
        for (int i = 0; i < results_kd.length; i++) {
            results_ps[i] = ps.nearest(points[i]);
            results_kd[i] = kd.nearest(points[i]);
            eq[i] = results_kd[i].equals(results_ps[i]);
        }

        // print the incongruities
        for (int i = 0; i < eq.length; i++) {
            if (!eq[i]) {
                StdOut.println("Query Point: " + points[i].toString() + " kd.nearest() = " + results_kd[i].toString() + " ps.nearest() = " + results_ps[i].toString());
                StdOut.println("Query Point: " + points[i].toString() + " distanceTo [KD] = " + points[i].distanceTo(results_kd[i]) + " distanceTo [PS] = " + points[i].distanceTo(results_ps[i]));
                StdOut.println("--------------------------------------------------------");
            }
        }
    }

}