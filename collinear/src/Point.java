/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally, if the two points are (x0, y0) and (x1,
     * y1), then the slope is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be +0.0 if the line
     * segment connecting the two points is horizontal; Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if ((that.x == x) && (that.y == y)) {
            return Double.NEGATIVE_INFINITY;
        } else if ((that.x - x) == 0 && ((that.y - y) != 0)) {
            return Double.POSITIVE_INFINITY;
        } else if ((that.y - y) == 0 && ((that.x - x) != 0)) {
            return +0.0;
        } else {
            return (double) (that.y - y) / (that.x - x);
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking point (x0, y0) is less
     * than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point (x0 = x1 and y0 = y1); a negative
     * integer if this point is less than the argument point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (y < that.y) {
            return -1;
        } else if (y > that.y) {
            return 1;
        } else {
            if (x < that.x) {
                return -1;
            } else if (x > that.x) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder(this);
    }

    private class SlopeOrder implements Comparator<Point> {

        private Point point;

        private SlopeOrder(Point p) {
            point = p;
        }

        @Override
        public int compare(Point q1, Point q2)  {

            double s1 = point.slopeTo(q1);
            double s2 = point.slopeTo(q2);
            if (s1 < s2) {
                return -1;
            } else if (s1 > s2) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    /*private static class PointOrder implements Comparator<Point> {

        @Override
        public int compare(Point p0, Point p1) {
            if (p0.y < p1.y) {
                return -1;
            } else if (p0.y > p1.y) {
                return 1;
            } else {
                if (p0.x < p1.x) {
                    return -1;
                } else if (p0.x > p1.x) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }*/


    /**
     * Returns a string representation of this point. This method is provide for debugging; your program should not rely
     * on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static int ccw(Point a, Point b, Point c) {
        double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area < 0) return -1; // clockwise turn
        else if (area > 0) return 1; // counter-clockwise turn
        else return 0; // collinear
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {

        int n = 25;
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(10);
            int y = StdRandom.uniform(10);
            points[i] = new Point(x, y);
        }

        for (Point point : points) {
            int r = StdRandom.uniform(n);
            int c = point.compareTo(points[r]);
            switch (c) {
                case -1:
                    StdOut.println(point.toString() + " is less than " + points[r].toString());
                    break;
                case 1:
                    StdOut.println(point.toString() + " is greater than " + points[r].toString());
                    break;
                case 0:
                    StdOut.println(point.toString() + " is equal to " + points[r].toString());
                    break;
            }
        }
    }
}
