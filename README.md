## Coursera Algorithms, Princeton University (Part I)

### Assignment 1: Percolation
*Score*: 95%
*Example*: `java PercolationStats 200 100`
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
                } else if (isFull(size, j)) {
                    sites.union(xyTo1D(size, j), bottom); // <- connect to virtual bottom
                }
            }
        }
  } // end open
} // end Percolation
```

### Assignment 2: Queues
*Score*: 95%
*Example*: `java PercolationStats 200 100`
*Comments*: One snag in implementing the `percolates()` method in `Percolation` is that if you use a virtual top and bottom, it's not true that every point connected to the virtual bottom percolates once the system percolates.  Therefore, in the `open()` method, perform the following check at the end:
