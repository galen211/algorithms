import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class BoggleSolverTest {

    ST<String, String[]> dictionaryST;
    ST<String, BoggleBoard> boardST;

    @Before
    public void setupBoggleSolverTest() {
        dictionaryST = new ST<>();
        boardST = new ST<>();

        Stack<File> dictFiles = TestFileReader.getFilesBeginWith("dictionary");
        Stack<File> boardFiles = TestFileReader.getFilesBeginWith("board");

        while (!dictFiles.isEmpty()) {
            File f = dictFiles.pop();
            String[] dict = readDictionary(f);
            String[] key = f.getName().split("[.]");
            dictionaryST.put(key[0], dict);
        }

        while (!boardFiles.isEmpty()) {
            File f = boardFiles.pop();
            BoggleBoard board = new BoggleBoard(f.getName());
            String[] key = f.getName().split("[.]");
            boardST.put(key[0], board);
        }
    }

    private String[] readDictionary(File file) {
        In in = new In(file);
        String[] words;
        words = in.readAllStrings();
        return words;
    }

    // TODO: implement checkGetAllValidWords once have chosen Iterable type.
    @Test
    public void checkGetAllValidWords() {

        String[] dict = dictionaryST.get("dictionary-algs4");
        BoggleSolver bs = new BoggleSolver(dict);
        BoggleBoard board;

        board = boardST.get("board4x4");
        Iterable<String> validWords = bs.getAllValidWords(board);
    }

    // TODO: implement checkGetAllValidWordsFixed once have chosen Iterable type.
    @Test
    public void checkGetAllValidWordsFixed() {

        String[] dict = dictionaryST.get("dictionary-yawl");
        BoggleSolver bs = new BoggleSolver(dict);
        BoggleBoard board;

        board = boardST.get("board4x4");
        Iterable<String> validWords = bs.getAllValidWords(board);
    }

    // TODO: implement checkGetAllValidWordsHighScoring once have chosen Iterable type.
    @Test
    public void checkGetAllValidWordsHighScoring() {

        String[] dict = dictionaryST.get("dictionary-yawl");
        BoggleSolver bs = new BoggleSolver(dict);
        BoggleBoard board;

        board = boardST.get("board-points4410");
        Iterable<String> validWords = bs.getAllValidWords(board);
        // expected size 1360
    }

    // TODO: implement checkGetAllValidWordsExoticWords once have chosen Iterable type.
    @Test
    public void checkGetAllValidWordsExoticWords() {

        String[] dict = dictionaryST.get("dictionary-yawl");
        BoggleSolver bs = new BoggleSolver(dict);
        BoggleBoard board;

        board = boardST.get("board-dodo");
        Iterable<String> validWords = bs.getAllValidWords(board);
        // expected size 5
    }

    @Test
    public void checkGetAllValidWordsBoardsWithQ() {
        String[] dict = dictionaryST.get("dictionary-yawl");
        BoggleSolver bs = new BoggleSolver(dict);
        BoggleBoard board;

        board = boardST.get("board-qwerty");
        Iterable<String> validWords = bs.getAllValidWords(board);
        // expected size = 22
    }

    @Test
    public void checkGetAllValidWordsBoardsWithNoValid() {

        String[] dict;
        BoggleSolver bs;
        BoggleBoard board;
        Iterable<String> validWords;

        dict = dictionaryST.get("dictionary-nursery");
        board = boardST.get("board-points0");
        bs = new BoggleSolver(dict);
        validWords = bs.getAllValidWords(board);
        // should not be any entries

        dict = dictionaryST.get("dictionary-2letters");
        board = boardST.get("board-points4410");
        bs = new BoggleSolver(dict);
        validWords = bs.getAllValidWords(board);
    }

    @Test
    public void checkMoreThanOneBoggleSolverObject() {

        String[] dict;
        BoggleSolver bs1;
        BoggleSolver bs2;
        BoggleBoard board;
        Iterable<String> validWords;
    }

    // scoreOf tests
    @Test
    public void checkScoreOfOnVariousDictionaries() {

        String[] dict;
        BoggleSolver bs;
        int score;

        dict = dictionaryST.get("dictionary-algs4");
        bs = new BoggleSolver(dict);
        score = bs.scoreOf("WINGS");
        Assert.assertEquals(0, score);

        dict = dictionaryST.get("dictionary-common");
        bs = new BoggleSolver(dict);
        score = bs.scoreOf("DESERVE");
        Assert.assertEquals(5, score);

        dict = dictionaryST.get("dictionary-shakespeare");
        bs = new BoggleSolver(dict);
        score = bs.scoreOf("DINGHY");
        Assert.assertEquals(0, score);

        dict = dictionaryST.get("dictionary-nursery");
        bs = new BoggleSolver(dict);
        score = bs.scoreOf("FA");
        Assert.assertEquals(0, score);

        dict = dictionaryST.get("dictionary-yawl");
        bs = new BoggleSolver(dict);
        score = bs.scoreOf("NEPHEW");
        Assert.assertEquals(3, score);
    }
}