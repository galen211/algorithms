import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class FastCollinearPoints {

    private final LineSegment[] lineSegments;
    private Point[] points;

    public FastCollinearPoints(Point[] p) {
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

        points = p;
        Queue<LineSegment> ls = new Queue<>();

        Point[] sub = new Point[points.length - 1];
        for (int i = 0; i < p.length; i++) {
            Point origin = p[i];
            sub = subArray(points, sub, i);
            sortAngle(origin, sub);

            Comparator<Point> c = origin.slopeOrder();
            int k;
            for (int j = 1; j < sub.length; j++) {
                k = j - 1;
                if (c.compare(sub[j-1], sub[j]) == 0){
                    while (j < sub.length && c.compare(sub[j-1], sub[j]) ==0) {
                        j++;
                    }
                    if ((j - k) > 3) {
                        ls.enqueue(new LineSegment(origin, sub[j - 1]));
                    }
                }

            }
        }

        lineSegments = new LineSegment[ls.size()];
        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = ls.dequeue();
        }


       /* // old
        Queue<LineSegment> lsq = new Queue<>();

        for (int i = 0; i < p.length - 2; i++) {
            Comparator<Point> c = p[i].slopeOrder();
            for (int j = i + 1; j < p.length - 1; j++) {
                for (int k = j + 1; k < p.length; k++) {
                    if (c.compare(p[j], p[k]) == 0) { // segment with three collinear points i,j,k
                        int v = p.length - 1;
                        while (v > k) { // segment with 4+ collinear points
                            if (c.compare(p[k], p[v]) == 0) {
                                lsq.enqueue(new LineSegment(p[i], p[v]));
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
        }*/
    }

    private Point[] subArray(Point[] in, Point[] out, int origin) {
        assert out.length == in.length-1;

        int k = 0;
        for (int i = 0; i < in.length; i++) {
            if (i == origin) continue;
            out[k] = in[i];
            k++;
        }
        return out;
    }

    private static void sortBySlope(Point origin, Point[] q) {
        Comparator<Point> comparator = origin.slopeOrder();
        Point[] aux = new Point[q.length];

        sortBySlope(comparator, q, aux, 0, q.length - 1);
        assert isSorted(comparator, q);
    }

    private static void sortBySlope(Comparator<Point> comparator, Point[] q, Point[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortBySlope(comparator, q, aux, lo, mid);
        sortBySlope(comparator, q, aux, mid + 1, hi);
        merge(comparator, q, aux, lo, mid, hi);
    }

    private static void merge(Comparator<Point> comparator, Point[] q, Point[] aux, int lo, int mid, int hi) {
        assert isSorted(comparator, q, lo, mid);
        assert isSorted(comparator, q, mid + 1, hi);

        // copy to aux
        for (int i = lo; i <= hi; i++) {
            aux[i] = q[i];
        }

        // merge back to a[]
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) q[k] = aux[i++];
            else if (j > hi) q[k] = aux[i++];
            else if (comparator.compare(aux[j], aux[i]) < 0) q[k] = aux[j++];
            else q[k] = aux[i++];
        }

        // should now be sorted from lo to hi
        assert isSorted(comparator, q, lo, hi);
    }


    private static boolean isSorted(Comparator c, Point[] p, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (c.compare(p[i], p[i - 1]) <= 0)
                return false;
        }
        return true;
    }

    private static boolean isSorted(Comparator c, Point[] p) {
        return isSorted(c, p, 0, p.length - 1);
    }

    private static void sortAngle(Point origin, Point[] p) {
        Comparator c = origin.slopeOrder();
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (c.compare(p[i], p[j]) > 0) {
                    Point hold = p[i];
                    p[i] = p[j];
                    p[j] = hold;
                }
            }
        }
    }

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
    }          // the line segments
}