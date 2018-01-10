import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class BoggleSolver {

    private WordSET dict;
    private SET<String> wordsInBoard;

    /**
     * Initializes the data structure using the given array of strings as the dictionary. (You can assume each word in
     * the dictionary contains only the uppercase letters A through Z.)
     *
     * @param dictionary
     */
    public BoggleSolver(String[] dictionary) {

        dict = new WordSET();
        for (int i = 0; i < dictionary.length; i++) {
            dict.add(dictionary[i]);
        }
    }


    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     *
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {

        wordsInBoard(board);

        return wordsInBoard;
    }

    private void wordsInBoard(BoggleBoard board) {

        wordsInBoard = new SET<>();

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                dfs(board, i, j, marked, new StringBuilder(""));
            }
        }
    }

    private void dfs(BoggleBoard board, int row, int col, boolean[][] marked, StringBuilder sb) {
        if ((row < 0 || row >= board.rows()) || (col < 0 || col >= board.cols())) return; // out of bounds
        else if (marked[row][col]) return; // already visited

        char c = board.getLetter(row, col);
        if (c == 'Q') sb.append("QU");
        else sb.append(c);

        String word = sb.toString();
        if (!dict.hasWordsWithPrefix(word)) return;
        if (word.length() >= 3 && dict.contains(word)) {
            wordsInBoard.add(word);
        }

        for (int i = -1; i <= 1; i++) { // up to down
            if ((i + row <= 0) || (i + row) >= board.rows()) continue;
            for (int j = -1; j <= 1; j++) { // left to right
                if ((j + col <= 0) || (j + col >= board.cols())) continue;
                if (marked[i + row][j + col]) continue;
                dfs(board, i + row, j + col, marked.clone(), new StringBuilder(word));
            }
        }
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise. (You can assume the word contains
     * only the uppercase letters A through Z.)
     *
     * @param word
     * @return
     */
    public int scoreOf(String word) {
        if (dict.contains(word)) {
            switch (word.length()) {
                case 0:
                case 1:
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                case 6:
                    return 3;
                case 7:
                    return 5;
                default:
                    return 11;
            }
        } else {
            return 0;
        }
    }

    // main method for testing
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("------------------");
        StdOut.println("Score =" + score);
    }
}