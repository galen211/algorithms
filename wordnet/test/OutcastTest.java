import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class OutcastTest {

    WordNet wordnet;
    Outcast outcast;

    WordNet wordnetSub;
    Outcast outcastSub;

    ST<String, String[]> outcastST = new ST<>();

    @Before
    public void setupOutcast() {

        Stack<File> files = TestFileReader.getFiles("outcast");

        assert files.size() == 20;

        while (!files.isEmpty()) {
            File file = files.pop();
            String fileName = file.getName();
            String[] key = fileName.split("[.]");

            In in = new In(file);
            String[] nouns = in.readAllStrings();
            outcastST.put(key[0], nouns);
        }

        wordnet = new WordNet("testfiles/synsets.txt", "testfiles/hypernyms.txt");
        outcast = new Outcast(wordnet);

        wordnetSub = new WordNet("testfiles/synsets50000-subgraph.txt", "hypernyms50000-subgraph.txt");
        outcastSub = new Outcast(wordnetSub);
    }


    // tests on wordnet
    @Test
    public void testOutcast3() {
        String[] nouns = outcastST.get("outcast3");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("Mickey_Mouse", result);
    }

    @Test
    public void testOutcast4() {
        String[] nouns = outcastST.get("outcast4");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("probability", result);
    }

    @Test
    public void testOutcast5() {
        String[] nouns = outcastST.get("outcast5");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("table", result);
    }

    @Test
    public void testOutcast5a() {
        String[] nouns = outcastST.get("outcast5a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("heart", result);
    }

    @Test
    public void testOutcast7() {
        String[] nouns = outcastST.get("outcast7");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("India", result);
    }

    @Test
    public void testOutcast8() {
        String[] nouns = outcastST.get("outcast8");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("bed", result);
    }

    @Test
    public void testOutcast8a() {
        String[] nouns = outcastST.get("outcast8a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("playboy", result);
    }

    @Test
    public void testOutcast8b() {
        String[] nouns = outcastST.get("outcast8b");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("cabbage", result);
    }

    @Test
    public void testOutcast8c() {
        String[] nouns = outcastST.get("outcast8c");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("tree", result);
    }

    @Test
    public void testOutcast9() {
        String[] nouns = outcastST.get("outcast9");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("tree", result);
    }

    @Test
    public void testOutcast9a() {
        String[] nouns = outcastST.get("outcast9a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("eyes", result);
    }

    @Test
    public void testOutcast10() {
        String[] nouns = outcastST.get("outcast10");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("albatross", result);
    }

    @Test
    public void testOutcast10a() {
        String[] nouns = outcastST.get("outcast10a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("serendipity", result);
    }

    @Test
    public void testOutcast11() {
        String[] nouns = outcastST.get("outcast11");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("potato", result);
    }

    @Test
    public void testOutcast12() {
        String[] nouns = outcastST.get("outcast12");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("Minneapolis", result);
    }

    @Test
    public void testOutcast12a() {
        String[] nouns = outcastST.get("outcast12a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("mongoose", result);
    }

    @Test
    public void testOutcast17() {
        String[] nouns = outcastST.get("outcast17");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("particularism", result);
    }

    @Test
    public void testOutcast20() {
        String[] nouns = outcastST.get("outcast20");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("particularism", result);
    }

    @Test
    public void testOutcast29() {
        String[] nouns = outcastST.get("outcast29");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("acorn", result);
    }

    // Tests on wordnet subgraph
    @Test
    public void testOutcastSub3() {
        String[] nouns = outcastST.get("outcast3");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("Mickey_Mouse", result);
    }

    @Test
    public void testOutcastSub5() {
        String[] nouns = outcastST.get("outcast5");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("table", result);
    }

    @Test
    public void testOutcastSub5a() {
        String[] nouns = outcastST.get("outcast5a");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("heart", result);
    }

    @Test
    public void testOutcastSub7() {
        String[] nouns = outcastST.get("outcast7");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("India", result);
    }

    @Test
    public void testOutcastSub8() {
        String[] nouns = outcastST.get("outcast8");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("bed", result);
    }

    @Test
    public void testOutcastSub8b() {
        String[] nouns = outcastST.get("outcast8b");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("cabbage", result);
    }

    @Test
    public void testOutcastSub8c() {
        String[] nouns = outcastST.get("outcast8c");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("tree", result);
    }

    @Test
    public void testOutcastSub9() {
        String[] nouns = outcastST.get("outcast9");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("tree", result);
    }

    @Test
    public void testOutcastSub10() {
        String[] nouns = outcastST.get("outcast10");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("albatross", result);
    }

    @Test
    public void testOutcastSub11() {
        String[] nouns = outcastST.get("outcast11");

        String result = outcast.outcast(nouns);

        Assert.assertEquals("potato", result);
    }
}