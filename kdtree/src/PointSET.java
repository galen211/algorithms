import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

    private final SET<Point2D> set;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new SET<>();
    }

    /**
     * is the set empty?
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * number of points in the set
     *
     * @return the number of points in the set
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p the point to add
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Cannot apply insert() to null point");

        if (!set.contains(p)) {
            set.add(p);
        }
    }

    /**
     * does the set contain point p?
     *
     * @param p the point
     * @return true if the invoking object contains point p
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains() cannot query null point");
        return set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D point2D : set) {
            StdDraw.point(point2D.x(), point2D.y());
        }
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     *
     * @param rect the given rectangle
     * @return the points inside the given rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("call to range() must include valid RectHV");

        SET<Point2D> pts = new SET<>();

        for (Point2D q : set) {
            if (rect.contains(q)) {
                pts.add(q);
            }
        }

        return pts;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p the given point
     * @return the nearest neighbor of the given point
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("call to nearest() must include valid point");

        if (set.isEmpty()) {
            return null;
        }

        Point2D min = set.max(); // initialize to a point in the set
        double distance = p.distanceSquaredTo(min);

        for (Point2D q : set) {
            if (p.distanceSquaredTo(q) < distance) {
                min = q;
                distance = p.distanceSquaredTo(q);
            }
        }

        return min;
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}