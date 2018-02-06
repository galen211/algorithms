import edu.princeton.cs.algs4.BinaryStdIn;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CircularSuffixArrayTest {

    @Test
    public void testLength() {
        String text = "ABRACADABRA!";
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        assertEquals(12, circularSuffixArray.length());
    }

    @Test
    public void testIndex() {
        String text = "ABRACADABRA!";
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        assertEquals(2, circularSuffixArray.index(11));
        assertEquals(10, circularSuffixArray.index(1));
    }

    @Test
    public void testIndexHello() {
        String text = "hello";
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        assertEquals(0, circularSuffixArray.index(1));
        assertEquals(1, circularSuffixArray.index(0));
    }

    @Test
    public void testAbra() {

        try {
            TestFileReader.runFile("testfiles/abra.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CircularSuffixArray.main(null);
    }

    @Test
    public void testAesop() {

        try {
            TestFileReader.runFile("testfiles/aesop.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CircularSuffixArray.main(null);

    }

    @Test
    public void testSpecification() {

        try {
            TestFileReader.runFile("testfiles/specification.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CircularSuffixArray.main(null);

    }

}