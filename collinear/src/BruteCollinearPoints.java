import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

/**
 * Class that examines 4 points at a time and checks whether they are on the same line segment, returning all such line
 * segments
 */
public class BruteCollinearPoints {

    private final Point[] points;
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] p) {
        if (p == null)
            throw new IllegalArgumentException("Cannot initialize null BruteCollinearPoints");

        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                throw new IllegalArgumentException("Cannot initialize null Point");
            }
            for (int j = 0; j < p.length; j++) {
                if (j == i)
                    continue;
                if (p[i].compareTo(p[j]) == 0) {
                    throw new IllegalArgumentException("Repeated point");
                }
            }
        }

        points = sortPoints(p);

        Queue<LineSegment> lsq = new Queue<>();

        for (int i = 0; i < points.length - 2; i++) {
            Comparator<Point> c = points[i].slopeOrder();
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (c.compare(points[j], points[k]) == 0) { // segment with three collinear points i,j,k
                        int v = points.length - 1;
                        while (v > k) { // segment with 4+ collinear points
                            if (c.compare(points[k], points[v]) == 0) {
                                lsq.enqueue(new LineSegment(points[i], points[v]));
                                break;
                            }
                            v--;
                        }
                    }
                }
            }
        }

        lineSegments = new LineSegment[lsq.size()];
        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = lsq.dequeue();
        }
    }   // finds all line segments containing 4 points

    private Point[] sortPoints(Point[] p) {
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (p[i].compareTo(p[j]) > 0) {
                    Point hold = p[i];
                    p[i] = p[j];
                    p[j] = hold;
                }
            }
        }
        return p;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }     // the number of line segments

    public LineSegment[] segments() {
        return lineSegments;
    }           // the line segments on the collinear points
}