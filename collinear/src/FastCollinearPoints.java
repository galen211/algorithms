import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private final LineSegment[] lineSegments;
    private final Point[] points;

    public FastCollinearPoints(Point[] pts) {

        if (pts == null) throw new IllegalArgumentException("Cannot initialize null object");
        for (int i = 0; i < pts.length; i++) {
            if (pts[i] == null) throw new IllegalArgumentException("Cannot initialize null point");
        }
        Arrays.sort(pts);
        for (int i = 1; i < pts.length; i++) {
            if (pts[i - 1].compareTo(pts[i]) == 0) throw new IllegalArgumentException("Repeated point");
        }

        points = pts.clone();

        lineSegments = findLineSegments(points);
    }

    private LineSegment[] findLineSegments(Point[] points) {
        // begin computing line segments
        int n = points.length;
        Queue<LineSegment> lsq = new Queue<>();

        Point[] aux = points.clone(); // auxillary array for sorting by slope order
        for (Point p : points) {

            Comparator<Point> c = p.slopeOrder();
            sort(aux, c);  // since p.slopeTo(p) == Double.NEGATIVE_INFINITY, aux[0] = p

            int j = 2;
            while (j < n) {

                if (c.compare(aux[j], aux[j - 1]) == 0) { // 3 collinear points

                    int k = j + 1;
                    while (k < n && c.compare(aux[k], aux[j]) == 0) {
                        k++;
                    }

                    Arrays.sort(aux, j - 1, k);
                    k--; // last collinear point in aux

                    if (p.compareTo(aux[j - 1]) < 0 && (k - j) > 0) {
                        lsq.enqueue(new LineSegment(p, aux[k]));
                    }
                    j = k;
                }
                j++;
            }
        }

        LineSegment[] ls = new LineSegment[lsq.size()];
        for (int i = 0; i < ls.length; i++) {
            ls[i] = lsq.dequeue();
        }
        return ls;
    }

    private static void sort(Point[] p, Comparator<Point> comparator) {
        int n = p.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(comparator, p[j], p[j - 1]); j--) {
                exch(p, j, j - 1);
            }
        }
    }

    private static boolean less(Comparator<Point> c, Point p, Point q) {
        if (c.compare(p, q) < 0) {
            return true;
        }
        return false;
    }

    private static void exch(Point[] p, int i, int j) {
        Point hold = p[i];
        p[i] = p[j];
        p[j] = hold;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    } // the number of line segments

    public LineSegment[] segments() {
        return lineSegments.clone();
    } // the line segments
}