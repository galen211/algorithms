import org.junit.Test;

import java.io.IOException;

public class SuffixArrayTest {

    TestFileReader tfs = new TestFileReader();

    @Test
    public void testMainClass() {

        String fileName = "testfiles/abra.txt";

        try {
            tfs.runFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SuffixArray.main(null);

        tfs.closeInputStream();
    }
}