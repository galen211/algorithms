import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture p;

    /**
     * create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        p = picture;
    }

    /**
     * current picture
     *
     * @return
     */
    public Picture picture() {
        return p;
    }

    /**
     * width of current picture
     *
     * @return
     */
    public int width() {
        return p.width();
    }

    /**
     * height of current picture
     *
     * @return
     */
    public int height() {
        return p.height();
    }

    /**
     * energy of pixel at column x and row y
     *
     * @param x
     * @param y
     * @return
     */
    public double energy(int x, int y) {
        return 0.0;
    }

    //Calculating Δx2 and Δy2 are very similar. Using two private methods will keep your code simple. To test that your code works, use the client PrintEnergy described in the testing section above.

    private double chgX() {
        return 0.0;
    }

    private double chgY() {
        return 0.0;
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

        return new int[]{};
    }

    /**
     * sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {
        /*
        To write findVerticalSeam(), you will want to first make sure you understand the topologial sort algorithm for computing a shortest path in a DAG. Do not create an EdgeWeightedDigraph. Instead construct a 2d energy array using the energy() method that you have already written. Your algorithm can traverse this matrix treating some select entries as reachable from (x, y) to calculate where the seam is located. To test that your code works, use the client PrintSeams described in the testing section above.
         */

        return new int[]{};
    }

    /**
     * remove horizontal seam from current picture
     *
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        /*
        To write removeHorizontalSeam(), transpose the image, call removeVerticalSeam(), and transpose it back.
         */
    }

    /**
     * remove vertical seam from current picture
     *
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {
        /*
        Now implement removeVerticalSeam(). Typically, this method will be called with the output of findVerticalSeam(), but be sure that they work for any seam. To test that your code words, use the client ResizeDemo described in the testing section above.
         */
    }

}