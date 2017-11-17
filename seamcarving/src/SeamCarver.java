import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture p;
    private double[][] energy;

    int[][] red;
    int[][] green;
    int[][] blue;

    int width;
    int height;

    private double[][] distTo;
    private int[][] edgeTo;

    /**
     * create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        p = picture;

        width = p.width();
        height = p.height();

        getImgColorValues();
        energy = calculateEnergyArray();
    }

    private void getImgColorValues() {

        // col, row orientation
        red = new int[width][height]; // note orientation
        green = new int[width][height];
        blue = new int[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                red[j][i] = p.get(j, i).getRed();
                green[j][i] = p.get(j, i).getGreen();
                blue[j][i] = p.get(j, i).getBlue();
            }
        }
    }

    private double[][] calculateEnergyArray() {

        double[][] e = new double[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                e[j][i] = energy(j, i);
            }
        }
        return e;
    }

    /**
     * current picture
     *
     * @return
     */
    public Picture picture() {
        return p;
    }



    private boolean left(int col) {
        if (col == 0) return false;
        return true;
    }

    private boolean right(int col) {
        if (col == width() - 1) return false;
        return true;
    }



    /**
     * width of current picture
     *
     * @return
     */
    public int width() {
        return width;
    }

    /**
     * height of current picture
     *
     * @return
     */
    public int height() {
        return height;
    }

    /**
     * energy of pixel at column x and row y
     *
     * @param row
     * @param col
     * @return
     */
    public double energy(int col, int row) {
        validateCoords(col, row);

        if ((row == 0 || row == height - 1) || (col == 0 || col == width - 1)) return 1000.0;

        double dX = chgX(col, row);
        double dY = chgY(col, row);

        return Math.sqrt(dX + dY);
    }

    /**
     * Calculating Δx2 and Δy2 are very similar. Using two private methods will keep your code simple. To test that your
     * code works, use the client PrintEnergy described in the testing section above.
     *
     * @return
     */
    private double chgX(int col, int row) {

        double dX = 0.0;
        dX += (red[col - 1][row] - red[col + 1][row]) * (red[col - 1][row] - red[col + 1][row]);
        dX += (green[col - 1][row] - green[col + 1][row]) * (green[col - 1][row] - green[col + 1][row]);
        dX += (blue[col - 1][row] - blue[col + 1][row]) * (blue[col - 1][row] - blue[col + 1][row]);

        return dX;
    }

    private double chgY(int col, int row) {

        double dY = 0.0;
        dY += (red[col][row - 1] - red[col][row + 1]) * (red[col][row - 1] - red[col][row + 1]);
        dY += (green[col][row - 1] - green[col][row + 1]) * (green[col][row - 1] - green[col][row + 1]);
        dY += (blue[col][row - 1] - blue[col][row + 1]) * (blue[col][row - 1] - blue[col][row + 1]);

        return dY;
    }

    /**
     * sequence of indices for horizontal seam
     *
     * @return
     */
    public int[] findHorizontalSeam() {
        /*
        To write findHorizontalSeam(), transpose the image, call findVerticalSeam(), and transpose it back.
         */

        return null;
    }

    /**
     * sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {

        distTo = new double[width][height];
        edgeTo = new int[width][height];

        int row = 0;
        for (int col = 0; col < width - 1; col++) {
            row++;
            if (left(col)) relax(col, col-1, row);
            if (right(col)) relax(col, col+1, row);
            relax(col, col, row);
        }

        return null;
    }

    // recusive for each column?
    private void relax(int v, int w, int row) {

        if (row == height()) return;

        double cmp = distTo[v][row-1] + energy[w][row];
        if (cmp > distTo[w][row]) {
            distTo[w][row] = cmp;
            edgeTo[w][row] = v;
        }
    }

    /**
     * remove horizontal seam from current picture
     *
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {

        // use Arrays.copyOf

    }

    /**
     * remove vertical seam from current picture
     *
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

            }
        }
    }

    private void validateCoords(int col, int row) {
        if ((col < 0 || col >= width()) || (row < 0 || row >= height())) {
            throw new IllegalArgumentException("Invalid coordinates: picture size is " + width() + "-x-" + height());
        }
    }

}