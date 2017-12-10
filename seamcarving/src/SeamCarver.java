import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {

    private Color[][] colors;
    private double[][] energy;

    private int width;
    private int height;

    private double[][] distTo;
    private int[][] edgeTo;

    private static final double BORDER = 1000.0;

    private static final boolean ORIGINAL = true;
    private static final boolean TRANSPOSED = false;

    private static boolean direction;

    /**
     * create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();

        direction = ORIGINAL;

        // these should only be changed by removeSeam methods
        width = picture.width();
        height = picture.height();

        colors = new Color[height][width];
        energy = new double[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                colors[i][j] = picture.get(j, i);
            }
        }

        for (int i = 0; i < h(); i++) {
            for (int j = 0; j < w(); j++) {
                cellEnergy(j, i);
            }
        }
    }

    /**
     * current picture
     *
     * @return
     */
    public Picture picture() {

        if (direction != ORIGINAL) {
            transpose();
        }

        Picture picture = new Picture(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                picture.set(j, i, colors[i][j]);
            }
        }

        return picture;
    }

    private void transpose() {

        Color[][] transposeColors = new Color[w()][h()];
        double[][] transposeEnergy = new double[w()][h()];

        for (int i = 0; i < h(); i++) {
            for (int j = 0; j < w(); j++) {
                transposeColors[j][i] = colors[i][j];
                transposeEnergy[j][i] = energy[i][j];
            }
        }

        colors = transposeColors;
        energy = transposeEnergy;

        direction = !direction;
    }

    /**
     * width() of current picture
     *
     * @return
     */
    public int width() {
        return width;
    }

    /**
     * height() of current picture
     *
     * @return
     */
    public int height() {
        return height;
    }

    // internal representation of width (conforms to size of colors matrix)
    private int w() {
        return colors[0].length;
    }

    // internal representation of height
    private int h() {
        return colors.length;
    }

    /**
     * energy of pixel at column x and row y
     *
     * @param row
     * @param col
     * @return
     */
    public double energy(int col, int row) {

        if ((col < 0 || col >= width) || (row < 0 || row >= height)) {
            throw new IllegalArgumentException("Error: Coordinates out of bounds");
        }

        // must assume the client is calling the picture from perspective of original orientation
        if (direction == ORIGINAL) {
            return energy[row][col];
        } else {
            return energy[col][row];
        }
    }

    // recalculates the energy along the seam
    private void recalculateEnergy(int[] seam) {
        for (int i = 1; i < h() - 1; i++) {
            int slice = seam[i];
            cellEnergy(slice, i);
            cellEnergy(slice - 1, i);
            cellEnergy(slice + 1, i);
        }
    }

    private void cellEnergy(int col, int row) {
        if (col < 0 || col >= w()) return; // ignore if calling while out of bounds

        if ((row == 0 || row == h() - 1) || (col == 0 || col == w() - 1)) {
            energy[row][col] = BORDER;
            return;
        }

        double dX = chgX(col, row);
        double dY = chgY(col, row);

        energy[row][col] = Math.sqrt(dX + dY);
    }

    /**
     * Calculating Δx2 and Δy2 are very similar. Using two private methods will keep your code simple. To test that your
     * code works, use the client PrintEnergy described in the testing section above.
     *
     * @return
     */
    private double chgY(int col, int row) {


        int redP1 = colors[row + 1][col].getRed();
        int greenP1 = colors[row + 1][col].getGreen();
        int blueP1 = colors[row + 1][col].getBlue();

        int redM1 = colors[row - 1][col].getRed();
        int greenM1 = colors[row - 1][col].getGreen();
        int blueM1 = colors[row - 1][col].getBlue();

        double dY = 0.0;
        dY += (redM1 - redP1) * (redM1 - redP1);
        dY += (greenM1 - greenP1) * (greenM1 - greenP1);
        dY += (blueM1 - blueP1) * (blueM1 - blueP1);

        return dY;
    }

    private double chgX(int col, int row) {

        int redP1 = colors[row][col + 1].getRed();
        int greenP1 = colors[row][col + 1].getGreen();
        int blueP1 = colors[row][col + 1].getBlue();

        int redM1 = colors[row][col - 1].getRed();
        int greenM1 = colors[row][col - 1].getGreen();
        int blueM1 = colors[row][col - 1].getBlue();

        double dX = 0.0;
        dX += (redM1 - redP1) * (redM1 - redP1);
        dX += (greenM1 - greenP1) * (greenM1 - greenP1);
        dX += (blueM1 - blueP1) * (blueM1 - blueP1);

        return dX;
    }

    /**
     * sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {

        if (direction != ORIGINAL) {
            transpose();
        }

        return findSeam();
    }

    /**
     * sequence of indices for horizontal seam
     *
     * @return
     */
    public int[] findHorizontalSeam() {

        if (direction != TRANSPOSED) {
            transpose();
        }

        return findSeam();
    }

    private int[] findSeam() {

        int rows = h();
        int cols = w();

        if (rows <= 2 || cols < 3) { // only borders left
            int[] path = new int[rows];
            return path;
        }

        distTo = new double[rows][cols];
        edgeTo = new int[rows][cols];

        // initialize the matrices with values that will be overwritten
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distTo[i][j] = BORDER * (i + 1);
                edgeTo[i][j] = j;
            }
        }

        // relax the edges
        for (int i = 0; i < (rows - 1); i++) {
            for (int j = 1; j < (cols - 1); j++) {
                relax(j, i, j);
                relax(j, i, j - 1);
                relax(j, i, j + 1);
            }
        }

        // find the minimum energy column
        int minCol = computeMinEnergyColumn(distTo[rows - 1]);

        // find the minimum energy seam
        int[] path = new int[rows];
        int col = minCol;
        for (int i = rows - 1; i >= 0; i--) {
            path[i] = col;
            col = edgeTo[i][col];
        }

        distTo = null;
        edgeTo = null;

        return path;
    }

    // find the minimum energy column of the last row
    private int computeMinEnergyColumn(double[] lastRow) {

        double min = Double.POSITIVE_INFINITY;
        int minCol = -1;
        for (int i = 1; i < lastRow.length - 1; i++) {
            if (lastRow[i] < min) {
                min = lastRow[i];
                minCol = i;
            }
        }
        return minCol;
    }

    private void relax(int col, int row, int v) {

        double dist = distTo[row][col] + energy[row + 1][v];

        if (dist < distTo[row + 1][v]) {
            distTo[row + 1][v] = dist;
            edgeTo[row + 1][v] = col;
        }
    }

    /**
     * remove horizontal seam from current picture
     *
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {

        if (direction != TRANSPOSED) {
            transpose();
        }

        removeSeam(seam);

        height--;
    }

    /**
     * remove vertical seam from current picture
     *
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {

        if (direction != ORIGINAL) {
            transpose();
        }

        removeSeam(seam);

        width--;
    }

    private void removeSeam(int[] seam) {

        validateSeam(seam);

        Color[][] cArray = new Color[h()][w() - 1];
        double[][] eArray = new double[h()][w() - 1];

        for (int i = 0; i < h(); i++) {
            // shift color array
            int slice = seam[i];
            System.arraycopy(colors[i], 0, cArray[i], 0, slice);
            System.arraycopy(colors[i], slice + 1, cArray[i], slice, cArray[i].length - slice);
            // shift energy array
            System.arraycopy(energy[i], 0, eArray[i], 0, slice);
            System.arraycopy(energy[i], slice + 1, eArray[i], slice, eArray[i].length - slice);
        }

        colors = cArray;
        energy = eArray;

        recalculateEnergy(seam);
    }

    private void validateSeam(int[] seam) {

        if (w() <= 1) throw new IllegalArgumentException("Error: Image too small for seam removal");

        if (seam == null) throw new IllegalArgumentException("Error: Null seam");

        if (seam.length != h()) throw new IllegalArgumentException("Error: Incorrect seam length");

        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= w()) throw new IllegalArgumentException("Error: Seam out of bounds");
        }

        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException("Error: Seam not valid");
        }
    }
}