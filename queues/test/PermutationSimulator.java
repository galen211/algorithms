import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class PermutationSimulator {

    public static void main(String[] args) {

        FileReader reader = null;

        File fileResources = new File("files");
        File[] listOfFiles = fileResources.listFiles();

        for (File file : listOfFiles) {
            StdOut.println("In File: " + file.toString());
            try {
                runFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StdOut.println("------------------------------------");
        }
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
            if (width > 0) { StdOut.println(); width = 0; }
        }
        StdOut.println();
    }
}
