public class BoggleBoard {

    /**
     * Initializes a random 4-by-4 Boggle board (by rolling the Hasbro dice)
     */
    public BoggleBoard() {

    }


    /**
     * Initializes a random m-by-n Boggle board (using the frequency of letters in the English language)
     *
     * @param m
     * @param n
     */
    public BoggleBoard(int m, int n) {

    }


    /**
     * Initializes a Boggle board from the specified filename.
     *
     * @param filename
     */
    public BoggleBoard(String filename) {

    }


    /**
     * Initializes a Boggle board from the 2d char array (with 'Q' representing the two-letter sequence "Qu")
     *
     * @param a
     */
    public BoggleBoard(char[][] a) {

    }


    /**
     * Returns the number of rows.
     *
     * @return
     */
    public int rows() {
        return -1;
    }

    /**
     * Returns the number of columns.
     *
     * @return
     */
    public int cols() {
        return -1;
    }


    /**
     * Returns the letter in row i and column j (with 'Q' representing the two-letter sequence "Qu")
     *
     * @param i
     * @param j
     * @return
     */
    public char getLetter(int i, int j) {
        return 'a';
    }


    /**
     * Returns a string representation of the board.
     *
     * @return
     */
    public String toString() {
        return null;
    }
}