## Coursera Algorithms, Princeton University (Part I)

### Assignment 1: Percolation
*Score*: 95%

*Example*: `> java PercolationStats 200 100`

*Comments*: One snag in implementing the `percolates()` method in `Percolation` is that if you use a virtual top and bottom, it's not true that every point connected to the virtual bottom percolates once the system percolates.  Therefore, in the `open()` method, perform the following check at the end:

```Java
public Class Percolation() {
  ...

  public void open(int row, int col) {
    ...
        // lastly, check if the cell is now full.
        if (isFull(row, col)) {
            for (int j = 1; j <= size; j++) {
                if (!isOpen(size, j)) {
                    continue;
                } else if (isFull(size, j)) { // check if any bottom row have percolated
                    sites.union(xyTo1D(size, j), bottom); // <- connect to virtual bottom
                }
            }
        }
  } // end open
} // end Percolation
```

You can use the `PercolationVisualizer` to visualize the percolation (and compare your results with the ones given)

![collinear_points](img/percolation.png)

### Assignment 2: Queues
*Score*: 95%

*Example*: `>java Permutation 8 < duplicates.txt`

*Comments*: The `RandomizedQueue` should dequeue a random object.  This can be achieved without shuffling by exchanging indexes at the front of the queue as shown below.

```java
public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        int r = StdRandom.uniform(0, n);
        exchange(r, n - 1);

        Item hold = q[--n];
        q[n] = null;

        if (n > 0 && n == (q.length / 4)) {
            resize(q.length / 2);
        }

        return hold;
}
```

### Assignment 3: Collinear
*Score*: 94%

*Example*: `> java FastCollinearPoints input8.txt`

*Comments*: Tough assignment where the trick is to figure out how to input the maximal line segment without duplicates.  The trick is figuring out how to implement a private `sort(Point[] p, Comparator<Point> c)` method in `FastCollinearPoints`.  You can use the `Sample` client code in `tests` to generate visualizations of the collinear lines.

![collinear_points](img/collinear.png)
