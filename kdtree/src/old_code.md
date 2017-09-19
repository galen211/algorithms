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
