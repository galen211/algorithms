import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    @Test
    public void enqueue() throws Exception {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        int limit = 100;

        for (int i = 0; i < limit; i++) {
             rq.enqueue(i);
        }

        assertTrue(rq.size() == limit);
    }

    @Test
    public void dequeue() throws Exception {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        int limit = 100;

        for (int i = 0; i < limit; i++) {
            StdOut.print(i + " ");
            rq.enqueue(i);
        }
        StdOut.println();

        for (int i = 0; i < limit; i++) {
             StdOut.print(rq.dequeue() + " ");

        }
        StdOut.println();

        assertTrue(rq.size() == 0);
    }

    @Test
    public void sample() throws Exception{
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        int limit = 100;

        for (int i = 0; i < limit; i++) {
            rq.enqueue(i);
        }
        while (!rq.isEmpty()){
            int rs = rq.sample();
            StdOut.print(rs + " ");
            rq.dequeue();
        }

        assertTrue(rq.isEmpty());
    }

    @Test
    public void randomCalls() {
        int n = 1000;

        RandomizedQueue rq = new RandomizedQueue();

        int limit = 100;

        for (int i = 0; i < limit; i++) {
            StdOut.print(i + " ");
            rq.enqueue(i);
        }
        StdOut.println();

        double[] p = new double[]{ 0.7, 0.1, 0.0, 0.1, 0.1};
        for (int i = 0; i < n; i++){
            for (int j = 0; j < p.length; j++) {
                boolean b = StdRandom.bernoulli(p[j]);
                if (b){
                    method(j, rq);
                }

            }
        }
    }

    @Test
    public void randomCalls_2(){
        int n = 1000;

        RandomizedQueue rq = new RandomizedQueue();

        int limit = 100;

        for (int i = 0; i < limit; i++) {
            StdOut.print(i + " ");
            rq.enqueue(i);
        }
        StdOut.println();

        double[] p = new double[]{ 0.1, 0.1, 0.6, 0.1, 0.1};
        int i = 0;
        while (i < n) {
            for (int j = 0; j < p.length; j++) {
                boolean b = StdRandom.bernoulli(p[j]);
                if (b){
                    try {
                        method(j, rq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
        }

    }

    /**
     * Calls a RandomizedQueue method by number [0,4]
     * @param j index number
     * @param rq the RandomizedQueue
     */
    private void method(int j, RandomizedQueue rq){
        switch (j) {
            case 0:
                rq.enqueue(StdRandom.uniform());
                break;
            case 1:
                rq.dequeue();
                break;
            case 2:
                rq.sample();
                break;
            case 3:
                rq.isEmpty();
                break;
            case 4:
                rq.size();
                break;
        }

    }

}