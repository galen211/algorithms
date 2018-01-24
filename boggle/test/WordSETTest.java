import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordSETTest {

    WordSET dict;

    @Before
    public void setupBoggleSolverTest() {
        String[] words = readDictionary("dictionary-algs4.txt");

        dict = new WordSET();
        for (String word : words) {
            dict.add(word);
        }
    }

    private String[] readDictionary(String file) {
        In in = new In(file);
        String[] words;
        words = in.readAllStrings();
        return words;
    }

    @Test
    public void testContains() {

        String[] wordsInDict = new String[]{
                "APPLE", "BIN", "REDIRECTED", "WRITES"
        };

        for (String s : wordsInDict) {
            Assert.assertTrue(dict.contains(s));
        }

        String[] wordsNotInDict = new String[] {
                "DOCTOR", "CHINESE"
        };

        for (String s : wordsNotInDict) {
            Assert.assertFalse(dict.contains(s));
        }

    }

    /**
     * Tests that every word (and prefix of the word) in a dictionary is contained in the WordSET dict
     */
    @Test
    public void testAllInDictionary() {

        String file = "dictionary-algs4.txt";
        In in = new In(file);

        String line;
        while (in.hasNextLine()) {
            line = in.readLine();
            char c;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < line.length(); i++) {
                 c = line.charAt(i);
                 sb.append(c);
                 Assert.assertTrue(dict.hasWordsWithPrefix(sb.toString()));
            }
            Assert.assertTrue(dict.contains(line));
        }
    }

}