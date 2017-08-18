import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class that examines 4 points at a time and checks whether they are on the same line segment, returning all such line
 * segments
 */
public class BruteCollinearPoints {

    private final Point[] points;
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] pts) {
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
    }   // finds all line segments containing 4 pts

    private LineSegment[] findLineSegments(Point[] points) {
        int n = points.length;
        Queue<LineSegment> lsq = new Queue<>();

        for (int p = 0; p < n - 3; p++) {
            Comparator<Point> c = points[p].slopeOrder();
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < n; s++) {
                        if ((c.compare(points[q], points[r])) == 0 && (c.compare(points[r], points[s]) == 0))
                            lsq.enqueue(new LineSegment(points[p], points[s]));
                    }
                }
            }
        }

        LineSegment[] ls = new LineSegment[lsq.size()];
        for (int i = 0; i < ls.length; i++) {
            ls[i] = lsq.dequeue();
        }
        return ls;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }     // the number of line segments

    public LineSegment[] segments() {
        return lineSegments.clone();
    }           // the line segments on the collinear points
}