import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

    private final int dimension;
    private final int[][] tiles;
    private final int manhattan;
    private final int hamming;

    /**
     * construct a board from an dimension-by-dimension array of blocks (where blocks[i][j] = block in row i, column j)
     *
     * @param blocks
     */
    public Board(int[][] blocks) {

        tiles = blocks;
        dimension = tiles.length;

        hamming = computeHamming();
        manhattan = computeManhattan();
    }

    /**
     * board dimension dimension
     *
     * @return
     */
    public int dimension() {
        return dimension;
    }

    /**
     * number of blocks out of place
     *
     * @return
     */
    public int hamming() {
        return hamming;
    }

    private int computeHamming() {
        int count = 0;
        int aux = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                aux++;
                if (tiles[i][j] != aux) count++;
            }
        }
        count--; // decrement once because of 0
        return count;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     *
     * @return
     */
    public int manhattan() {
        return manhattan;
    }

    /**
     * internal method for computing manhattan distance
     *
     * @return the sum of the manhattan distances of the blocks to their respective goals
     */
    private int computeManhattan() {
        // Recall that the blank square is not considered a block.

        int[][] solution = getSolution();

        int distance = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] == solution[i][j] || tiles[i][j] == 0) {
                    continue;
                } else {
                    distance += distance(tiles[i][j], i, j);
                }
            }
        }
        return distance;
    }

    private int distance(int number, int i, int j) {

        int row = (number - 1) / dimension;
        int col = number - (dimension * row) - 1;

        return Math.abs(row - i) + Math.abs(col - j);
    }

    /**
     * is this board the goal board?
     *
     * @return
     */
    public boolean isGoal() {

        Board solution = new Board(getSolution());

        return this.equals(solution);
    }

    private int[][] getSolution() {
        int[][] solution = new int[dimension][dimension];
        int count = 1;
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                solution[i][j] = count++;
            }
        }
        solution[dimension - 1][dimension - 1] = 0;
        return solution;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     *
     * @return
     */
    public Board twin() {

        int[][] blocks = new int[dimension][dimension];
        for (int m = 0; m < blocks.length; m++) {
            for (int n = 0; n < blocks.length; n++) {
                blocks[m][n] = this.tiles[m][n];
            }
        } // deep copy (could also use Arrays.copyOf

        // swap random values, must not swap with 0
        int i = StdRandom.uniform(blocks.length);
        int j = StdRandom.uniform(blocks.length);
        while (blocks[i][j] == 0) {
            i = StdRandom.uniform(blocks.length);
            j = StdRandom.uniform(blocks.length);
        }

        int k = StdRandom.uniform(blocks.length);
        int m = StdRandom.uniform(blocks.length);
        while (blocks[k][m] == 0 || (k == i && m == j)) {
            k = StdRandom.uniform(blocks.length);
            m = StdRandom.uniform(blocks.length);
        }

        int hold = blocks[i][j];
        blocks[i][j] = blocks[k][m];
        blocks[k][m] = hold;

        return new Board(blocks);
    }

    private Board exch(int i, int j, int p, int q) {

        int[][] blocks = new int[dimension][dimension];
        for (int m = 0; m < blocks.length; m++) {
            for (int n = 0; n < blocks.length; n++) {
                blocks[m][n] = this.tiles[m][n];
            }
        } // deep copy (could also use Arrays.copyOf

        assert blocks[i][j] == 0;  // should always be exchanging with the 0 element

        blocks[i][j] = blocks[p][q];
        blocks[p][q] = 0;

        Board board = new Board(blocks);

        return board;
    }

    /**
     * does this board equal y?
     *
     * @param y
     * @return
     */
    public boolean equals(Object y) { //uses object

        if (y == null || this.getClass() != y.getClass()) return false;

        Board board = (Board) y;
        if (board.dimension() != this.dimension()) return false;

        if (this.isBoardEqual(board)) return true;

        return false;
    }

    /**
     * Compares the tiles of the invoking board to another board given as argument
     *
     * @param board the board to compare
     * @return true if the boards have the same tile configuration
     */
    private boolean isBoardEqual(Board board) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (!(tiles[i][j] == board.tiles[i][j])) return false;
            }
        }
        return true;
    }

    /**
     * all neighboring boards
     *
     * @return
     */
    public Iterable<Board> neighbors() {

        Stack<Board> stack = new Stack<>();

        int i = 0;
        int j = 0;
        for (int p = 0; p < dimension; p++) {
            for (int q = 0; q < dimension; q++) {
                if (tiles[p][q] == 0) {
                    i = p;
                    j = q;
                    break;
                }
            }
            if (tiles[i][j] == 0) break;
        }  // found location of zero

        assert tiles[i][j] == 0;

        if (i > 0 && i < dimension - 1) {
            stack.push(exch(i, j, i + 1, j));
            stack.push(exch(i, j, i - 1, j));
        }

        if (j > 0 && j < dimension - 1) {
            stack.push(exch(i, j, i, j + 1));
            stack.push(exch(i, j, i, j - 1));
        }

        if (i == 0) {
            stack.push(exch(i, j, i + 1, j));
        }

        if (j == 0) {
            stack.push(exch(i, j, i, j + 1));
        }

        if (i == dimension - 1) {
            stack.push(exch(i, j, i - 1, j));
        }

        if (j == dimension - 1) {
            stack.push(exch(i, j, i, j - 1));
        }
        return stack;
    }

    /**
     * string representation of this board (in the output format specified below)
     *
     * @return
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}