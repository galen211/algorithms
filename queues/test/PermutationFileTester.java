import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static junit.framework.TestCase.assertTrue;

public class PermutationFileTester {

/*

    duplicates.txt
    mediumTale.txt
    permutation10.txt
    permutation4.txt
    permutation5.txt
    permutation6.txt
    permutation8.txt
    tinyTale.txt
*/


    @Test
    public void testDistinct() {

        int trials = 100;

        ArrayDeque deque = new ArrayDeque();

        File file = new File("files/distinct.txt");

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()){
                    deque.addLast(st.nextToken());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < trials; i++) {
            StringBuilder sb = new StringBuilder();
            RandomizedQueue rq = new RandomizedQueue();
            for (Object o : deque) {
                rq.enqueue(o);
            }

            for (int k = 0; k < 3; k++) {
                sb.append(rq.dequeue() + " ");
            }
            String pattern = sb.toString();
            StdOut.println(pattern);
        }
    }

    @Test
    public void testPermutation_4_iter() {

        int trials = 100;

        ArrayDeque deque = new ArrayDeque();

        File file = new File("files/permutation4.txt");

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()){
                    deque.addLast(st.nextToken());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < trials; i++) {
            StringBuilder sb = new StringBuilder();
            RandomizedQueue rq = new RandomizedQueue();
            for (Object o : deque) {
                rq.enqueue(o);
            }

            int n = rq.size();
            for (int k = 0; k < 2; k++) {
                sb.append(rq.sample() + " ");
                assertTrue(n == rq.size());
            }
            String pattern = sb.toString();
            StdOut.println(pattern);
        }
    }

}
