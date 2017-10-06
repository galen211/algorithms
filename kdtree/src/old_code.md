// old range() method
```java
public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("call to range() is null");

        if (isEmpty()) return new ArrayList<>();

        Node h = root;
        while ((h.lb != null) && (h.rt != null)) {
            if (!h.rt.rect.intersects(rect))
                h = h.lb;
            else if (!h.lb.rect.intersects(rect))
                h = h.rt;
            else break; // both branches intersect with the query
        }

        // node h and its children are possibly contained by rect
        ArrayList<Point2D> children = new ArrayList<>();
        ArrayList<Point2D> hits = new ArrayList<>();

        children(h, children);

        for (Point2D p : children) {
            if (rect.contains(p)) {
                hits.add(p);
            }
        }

        return hits;
}
```

Nearest
```java
public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("call to nearest() null argument");

        if (isEmpty()) return null;

        Node x = root;
        boolean orientation = X_ALIGNED;
        Comparator<Point2D> c;
        while (x.lb != null && x.rt != null) {
            if (x.lb.rect.contains(p) && x.rt.rect.contains(p)) {
                c = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
                int cmp = c.compare(p, x.p);
                if (cmp < 0) x = x.lb;
                else x = x.rt;
            } else {
                break;
            }
        }

        ArrayList<Point2D> list = new ArrayList<>();
        children(x, list);

        double min = p.distanceSquaredTo(root.p);
        Point2D nearest = root.p;

        for (Point2D q : list) {
            if (p.distanceSquaredTo(q) < min) {
                min = p.distanceSquaredTo(q);
                nearest = q;
            }
        }

        return nearest;
}
```


```java

/*Node h = root;
        Node prev = h;
        boolean orientation = X_ALIGNED;
        Comparator<Point2D> c;
        while (h != null) {
            prev = h;
            c = orientation ? Point2D.X_ORDER : Point2D.Y_ORDER;
            int cmp = c.compare(p, h.p);
            if (cmp < 0) {
                h = h.lb;
            } else {
                h = h.rt;
            }
            orientation = !orientation;
        }

        double minDist = prev.p.distanceSquaredTo(p);
        Point2D min = prev.p;
        Queue<Point2D> queue = new Queue<>();

        nearest(root, p, minDist, min, queue);

        Point2D q;
        while (!queue.isEmpty()) {
            if ((q = queue.dequeue()).distanceSquaredTo(p) < minDist) {
                minDist = q.distanceSquaredTo(p);
                min = q;
            }
        }*/

private void nearest(Node x, Point2D p, double minDist, Point2D min, Queue<Point2D> queue) {

        if (x.p.distanceSquaredTo(p) < minDist) {
            queue.enqueue(x.p);
        }

        if (x.lb != null && x.lb.rect.distanceSquaredTo(p) < minDist) nearest(x.lb, p, minDist, min, queue);
        if (x.rt != null && x.rt.rect.distanceSquaredTo(p) < minDist) nearest(x.rt, p, minDist, min, queue);
    }

    /**
     * if the closest point discovered so far is closer than the distance between the query point
     * and the rectangle corresponding to a node, there is no need to explore that node (or its subtrees).
     * That is, search a node only only if it might contain a point that is closer than the best one found so far.
     * The effectiveness of the pruning rule depends on quickly finding a nearby point. To do this,
     * organize the recursive method so that when there are two possible subtrees to go down, you always
     * choose the subtree that is on the same side of the splitting line as the query point as the first
     * subtree to explore—the closest point found while exploring the first subtree may enable pruning of
     * the second subtree.
     */
```
