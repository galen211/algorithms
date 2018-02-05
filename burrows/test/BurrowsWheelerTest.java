import org.junit.Test;

public class BurrowsWheelerTest {


    @Test
    public void testEncode() {

        try {
            TestFileReader.runFile("testfiles/aesop.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BurrowsWheeler.main(new String[]{"-"});
    }
}