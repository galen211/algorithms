import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final WeightedQuickUnionUF sites;
    private boolean[] open;
    private boolean[] full;
    private int numOpenSites; // number of open sites
    private final int top;
    private final int bottom;

    /**
     * creates an n-by-n grid, with all sites blocked
     *
     * @param n the size of the grid
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = n;
        open = new boolean[size * size];
        full = new boolean[size * size];
        sites = new WeightedQuickUnionUF(size * size + 2); // include virtual top and bottom
        top = size * size;
        bottom = size * size + 1;
    }

    /**
     * The open method validates the given row and column indices, marks the state of the site as open and then performs operations on the site to link it to its open neighbors
     *
     * @param row the given row
     * @param col the given column
     */
    public void open(int row, int col) {
        if ((row <= 0 || col <= 0) || (row > size || col > size))
            throw new java.lang.IllegalArgumentException();

        int p = xyTo1D(row, col); // cell to open, p

        if (open[p]) {
            return;
        } else {
            open[p] = true;
            numOpenSites++;
        }

        // if not top row, open cell above
        if (row != 1) {
            int q = xyTo1D(row - 1, col);
            if (open[q] && !sites.connected(p, q))
                sites.union(p, q);
        } else if (row == 1) {
            sites.union(p, top); // connect to virtual top
        }

        // if not bottom row, open cell below
        if (row != size) {
            int q = xyTo1D(row + 1, col);
            if (open[q] && !sites.connected(p, q))
                sites.union(p, q);
        }

        // if not on left border, open left cell
        if (col > 1 && isOpen(row, col - 1)) {
            int q = xyTo1D(row, col - 1);
            if (open[q] && !sites.connected(p, q))
                sites.union(p, q);
        }

        // if not on right border, open right cell
        if (col < size && isOpen(row, col + 1)) {
            int q = xyTo1D(row, col + 1);
            if (open[q] && !sites.connected(p, q))
                sites.union(p, q);
        }

        // lastly, check if the cell is now full.  If it's full it may have changed full state of a bottom cell.  Check the bottom.
        if (isFull(row, col)) {
            for (int j = 1; j <= size; j++) {
                if (!isOpen(size, j)) {
                    continue;
                } else if (isFull(size, j)) {
                    sites.union(xyTo1D(size, j), bottom);
                }
            }
        }
    }

    /**
     * determines whether a given site is open
     *
     * @param row the given row
     * @param col the given column
     * @return true if the site is open
     */
    public boolean isOpen(int row, int col) {
        if ((row <= 0 || col <= 0) || (row > size || col > size))
            throw new java.lang.IllegalArgumentException();

        int idx = xyTo1D(row, col);
        return open[idx];
    }

    /**
     * Determines if the site is full (i.e. has percolated)
     *
     * @param row the given row
     * @param col the given column
     * @return
     */
    public boolean isFull(int row, int col) {
        if ((row <= 0 || col <= 0) || (row > size || col > size))
            throw new java.lang.IllegalArgumentException();

        return sites.connected(top, xyTo1D(row, col));
    }

    /**
     * Computes the number of open sites
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    /**
     * Determines if the grid percolates (i.e. there is a full path from top to bottom)
     *
     * @return true if the grid percolates
     */
    public boolean percolates() {
        return sites.connected(top, bottom);
    }

    /**
     * maps row and column coordinates (in standard form) to 1D
     *
     * @param row the given row
     * @param col the given column
     * @return the 1D integer index of the coordinates
     */
    private int xyTo1D(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

}
