import edu.princeton.cs.algs4.SET;

import java.util.Iterator;

public class BoggleSolver {

    private WordSET dict;

    private static boolean[][] marked;

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

        SET<String> validWords = new SET<>();

        Iterator<String> itr = dict.iterator();
        String word;
        while (itr.hasNext()) {
            word = itr.next();
            if (wordInBoard(board, word)) {
                validWords.add(word);
            }
        }

        return validWords;
    }

    private boolean wordInBoard(BoggleBoard board, String word) {

        int c = 0;
        while (c <= word.length()) {
            char x = word.charAt(c++);

        }
        return false;
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
}