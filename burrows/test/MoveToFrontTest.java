import edu.princeton.cs.algs4.Huffman;
import org.junit.Test;

import java.io.IOException;

public class MoveToFrontTest {


    @Test
    public void testHexDumpExample() {

        try {
            TestFileReader.runFile("testfiles/abra.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MoveToFront.main(new String[]{"-"});
    }


    @Test
    public void testEncodeDecode() {

        try {
            TestFileReader.runFile("testfiles/abra.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MoveToFront.encode();
        MoveToFront.decode();
    }


    @Test
    public void testHuffmanEncode() {
        try {
            TestFileReader.runFile("testfiles/abra.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Huffman.main(new String[]{"-"});

        TestFileReader.closeInputStream();
    }

}