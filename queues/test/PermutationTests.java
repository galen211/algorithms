import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class PermutationTests {


    @Test
    public void permutation4_test(){

        FileReader reader = null;

        File file = new File("files/permutation10.txt");

        assert file.exists();

        int trials = 1000;
        int[] val = new int[]{0,0,0,0};
        for (int i = 0; i < trials; i++) {

        }

        StdOut.println("In File: " + file.toString());
            try {
                runFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StdOut.println("------------------------------------");
    }


    private static void runFile(File file) throws Exception {

        RandomizedQueue rq = new RandomizedQueue();
        int tokenCount = 0;

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line," ");
            while (st.hasMoreTokens()){
                rq.enqueue(st.nextToken());
                tokenCount++;
            }
        }
        reader.close();
        fileReader.close();

        int width = 0;
        for (int i = 0; i < tokenCount; i++) {
            StdOut.print(rq.dequeue() + " ");
            width++;
            if (width > 20) { StdOut.println(); width = 0; }
        }
        StdOut.println();
    }
}
