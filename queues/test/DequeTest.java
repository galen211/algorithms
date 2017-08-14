import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DequeTest {

    @Test
    public void testAddFirstRemoveLast() {

        final int n = 100;
        int[] expected = new int[n];
        int[] actual = new int[n];

        Deque<Integer> deque = new Deque<>();

        for (int i = 0; i < n; i++) {
            deque.addFirst(i);
            expected[i] = i;
        }

        assertTrue(n == deque.size()); // deque has 100 elements

        for (int i = 0; i < n; i++) {
            actual[i] = deque.removeLast();
        }

        assertTrue(Arrays.equals(expected, actual));

        assertTrue(0 == deque.size()); // deque is now empty
    }

    @Test
    public void testAddLastRemoveFirst() {

        final int n = 100;
        int[] expected = new int[n];
        int[] actual = new int[n];

        Deque<Integer> deque = new Deque<>();

        for (int i = 0; i < n; i++) {
            deque.addLast(i);
            expected[i] = i;
        }

        assertTrue(n == deque.size()); // deque has 100 elements

        for (int i = 0; i < n; i++) {
            actual[i] = deque.removeFirst();
        }

        assertTrue(Arrays.equals(expected, actual));

        assertTrue(0 == deque.size()); // deque is now empty
    }

    @Test
    public void testIterator(){

        final int n = 25;

        Deque<Integer> d1 = new Deque<>();

        for (int i = 0; i < n; i++) {
            d1.addLast(i);
        }

        while (!d1.isEmpty()) {
            Iterator itr = d1.iterator();
            while (itr.hasNext()) {
                StdOut.print(itr.next() + " ");
            }
            d1.removeLast();
            StdOut.println();
        }

        assertTrue(d1.isEmpty());

        Deque<Integer> d2 = new Deque<>();

        for (int i = 0; i < n; i++) {
            d2.addLast(i);
        }

        while (!d2.isEmpty()) {
            Iterator itr = d2.iterator();
            while (itr.hasNext()) {
                StdOut.print(itr.next() + " ");
            }
            d2.removeFirst();
            StdOut.println();
        }

        assertTrue(d2.isEmpty());

    }

    @Test
    public void testRandomCalls(){

        int n = 100000;

        Deque deque = new Deque();
        double random;
        for (int i = 0; i < n; i++) {
             random = StdRandom.uniform();
             deque.addFirst(random);
        }

        int method;

        for (int i = 0; i < n; i++) {
            method = StdRandom.uniform(5);
            double r;
            switch (method) {
                case 0:
                    r = StdRandom.uniform();
                    deque.addFirst(r);
                    break;
                case 1:
                    r = StdRandom.uniform();
                    deque.addLast(r);
                    break;
                case 3:
                    if (deque.isEmpty())
                        break;
                    deque.removeFirst();
                    break;
                case 4:
                    if (deque.isEmpty())
                        break;
                    deque.removeLast();
                    break;
            }
        }
    }

    @Test
    public void testRandomCalls_2() {
        int n = 1000;

        Deque deque = new Deque();
        double random;
        for (int i = 0; i < n; i++) {
            random = StdRandom.uniform();
            deque.addFirst(random);
        }

        double[] p = new double[]{ 0.4, 0.4, 0.0, 0.0, 0.0, 0.2};
        for (int i = 0; i < n; i++){
            for (int j = 0; j < p.length; j++) {
                boolean b = StdRandom.bernoulli(p[j]);
                if (b){
                    method(j, deque);
                }

            }
        }
    }

    @Test
    public void testRandomCalls_3() {
        int n = 1000;

        Deque deque = new Deque();
        double random;
        for (int i = 0; i < n; i++) {
            random = StdRandom.uniform();
            deque.addFirst(random);
        }

        double[] p = new double[]{ 0.8, 0.0, 0.1, 0.0, 0.1, 0.0};
        for (int i = 0; i < n; i++){
            for (int j = 0; j < p.length; j++) {
                boolean b = StdRandom.bernoulli(p[j]);
                if (b){
                    method(j, deque);
                }

            }
        }
    }

    @Test
    public void testRandomCalls_4() {
        int n = 1000;

        Deque deque = new Deque();
        double random;
        for (int i = 0; i < n; i++) {
            random = StdRandom.uniform();
            deque.addFirst(random);
        }

        double[] p = new double[]{ 0.1, 0.0, 0.8, 0.0, 0.1, 0.0};
        for (int i = 0; i < n; i++){
            for (int j = 0; j < p.length; j++) {
                boolean b = StdRandom.bernoulli(p[j]);
                if (b){
                    method(j, deque);
                }

            }
        }
    }

    private void method(int j, Deque deque) {
        switch (j) {
            case 0:
                deque.addFirst(StdRandom.uniform());
                break;
            case 1:
                deque.addLast(StdRandom.uniform());
                break;
            case 2:
                deque.removeFirst();
                break;
            case 3:
                deque.removeLast();
                break;
            case 4:
                deque.size();
                break;
            case 5:
                deque.isEmpty();
                break;
        }

    }

    @Test
    public void testFiles() {

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

    private void runFile(File file) throws Exception {
        Deque dq = new Deque();
        int tokenCount = 0;

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                dq.addFirst(st.nextToken());
                tokenCount++;
            }
        }
        reader.close();
        fileReader.close();

        for (int i = 0; i < tokenCount; i++) {
            StdOut.print(dq.removeLast() + " ");
        }
        StdOut.println();
    }

    @Test
    public void failedTest() {

        Deque deque = new Deque();

        assertTrue(deque.isEmpty());
        deque.addLast(1);
        assertFalse(deque.isEmpty());
        assertFalse(deque.isEmpty());
        int one = (int) deque.removeFirst();
        assertTrue(one == 1);
        assertTrue(deque.isEmpty());
        deque.addLast(6);
        assertFalse(deque.isEmpty());
        int six = (int) deque.removeFirst();
        assertTrue(six == 6);
    }

}