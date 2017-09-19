import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Comparator;

public class KdTree {

    private static final boolean X_ALIGNED = true;
    private Node root;
    private int size;

    /**
     * Private inner node class
     */
    private static class Node {
        private final Point2D p;      // the point
        private final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;              // the left/bottom subtree
        private Node rt;              // the right/top subtree

        private Node(Point2D point, RectHV rectHV, Node left, Node right) {
            p = point;
            rect = rectHV;
            lb = left;
            rt = right;
        }

        private void draw(boolean orientation) {

            if (orientation == X_ALIGNED) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(p.x(), rect.ymin(), p.x(), rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(rect.xmin(), p.y(), rect.xmax(), p.y());
            }
        }
    }

    /**
     * construct an empty set of points
     */
    public KdTree() {
        size = 0;
        root = null;
    }

    /**
     * is the set empty?
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * number of points in the set
     *
     * @return the number of points in the set
     */
    public int size() {
        return size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p the point to add
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument to put() is null");
        }

        if (!contains(p)) {
            Point2D lower = new Point2D(0, 0);
            Point2D upper = new Point2D(1, 1);
            root = insert(root, p, X_ALIGNED, lower, upper);
        }
    }

    private Node insert(Node h, Point2D p, boolean orientation, Point2D lower, Point2D upper) {

        if (h == null) {
            size++;
            return new Node(p, new RectHV(lower.x(), lower.y(), upper.x(), upper.y()), null, null);
        }

        Comparator<Point2D> c = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        Point2D split; // point on which split occurs

        int cmp = c.compare(p, h.p);
        if (cmp < 0) {
            if (orientation) { // X-Aligned
                split = new Point2D(h.p.x(), upper.y());
            } else {
                split = new Point2D(upper.x(), h.p.y());
            }
            h.lb = insert(h.lb, p, !orientation, lower, split);
        } else {
            if (orientation) { // X-Aligned
                split = new Point2D(h.p.x(), lower.y());
            } else {
                split = new Point2D(lower.x(), h.p.y());
            }
            h.rt = insert(h.rt, p, !orientation, split, upper);
        }

        return h;
    }

    /**
     * does the set contain point p?
     *
     * @param p the point
     * @return true if the invoking object contains point p
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null argument passed to contains()");

        if (isEmpty()) return false;

        Node h;
        h = get(root, p, X_ALIGNED);

        return !(h == null); // if h is null, then the entry does not exist yet
    }

    private Node get(Node h, Point2D p, boolean orientation) {

        if (h == null) return null;

        Comparator<Point2D> comparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
        int cmp = comparator.compare(p, h.p);
        if (cmp < 0) {
            return get(h.lb, p, !orientation);
        } else if (cmp > 0) {
            return get(h.rt, p, !orientation);
        } else {
            if (h.p.equals(p)) return h;
            else return get(h.rt, p, !orientation);
        }
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        drawPoints(root);
        drawBox(root, X_ALIGNED);
    }

    private void drawBox(Node node, boolean orientation) {
        if (node == null) return;

        node.draw(orientation);

        drawBox(node.lb, !orientation);
        drawBox(node.rt, !orientation);
    }

    private void drawPoints(Node node) {
        if (node == null) return;

        // draw the points in black
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();

        drawPoints(node.lb);
        drawPoints(node.rt);
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     *
     * @param rect the given rectangle
     * @return the points inside the given rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("call to range() is null");

        ArrayList<Point2D> list = new ArrayList<>();

        if (isEmpty()) return list;

        range(root, rect, list);

        return list;
    }

    private void range(Node x, RectHV rect, ArrayList<Point2D> list) {
        if (rect.contains(x.p)) list.add(x.p);

        if (x.lb != null && rect.intersects(x.lb.rect)) range(x.lb, rect, list);
        if (x.rt != null && rect.intersects(x.rt.rect)) range(x.rt, rect, list);
    }

    private static Point2D min; // static variables for storing progress towards finding nearest point to a query
    private static double minDist;

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p the given point
     * @return the nearest neighbor of the given point
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("call to nearest() null argument");

        if (isEmpty()) return null;

        min = root.p;
        minDist = min.distanceSquaredTo(p);

        nearest(root, p, X_ALIGNED);

        return min;
    }

    private void nearest(Node x, Point2D p, boolean orientation) {

        if (x == null) return;

        if (minDist < x.rect.distanceSquaredTo(p)) {
            return;
        } else {
            // search the node
            if (x.p.distanceSquaredTo(p) < minDist) {
                min = x.p;
                minDist = min.distanceSquaredTo(p);
            }
            // choose which subtree to search first
            if (x.lb != null && x.rt != null) {
                Comparator<Point2D> comparator = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
                int cmp = comparator.compare(p, x.p);
                if (cmp < 0) {
                    nearest(x.lb, p, !orientation);
                    nearest(x.rt, p, !orientation);
                } else {
                    nearest(x.rt, p, !orientation);
                    nearest(x.lb, p, !orientation);
                }
            } else if (x.lb == null) {
                nearest(x.rt, p, !orientation);
            } else {
                nearest(x.lb, p, !orientation);
            }
        }
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}