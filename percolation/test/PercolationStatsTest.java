import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationStatsTest {


    @Test
    public void percolationStatsTimer(){

        int[] arraySize = new int[]{100, 200, 400, 800, 1600, 3200};
        int repetitions = 1000;

        for (int i = 0; i < arraySize.length; i++) {
                Stopwatch stopwatch = new Stopwatch();
                PercolationStats ps = new PercolationStats(arraySize[i],repetitions);
                double time = stopwatch.elapsedTime();
                StdOut.println("Repetitions = " + Integer.toString(repetitions) + " | Array Size = " + Integer.toString(arraySize[i]));
                StdOut.println("Elapsed Time = " + Double.toString(time));
                StdOut.println("---------------------------------------------");
        }
    }
}