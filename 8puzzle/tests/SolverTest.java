import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SolverTest {

    @Test
    public void testSolver() {

        int[][] tiles = new int[][]{
                {0,1,3},
                {4,2,5},
                {7,8,6}
        };

        Board board = new Board(tiles);
        Solver solver = new Solver(board);
    }

    @Test
    public void testSolverMoves_00() {

        int[][] tiles = TestReader.getTest("puzzle00.txt");
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        assertEquals(0, solver.moves());
    }

    @Test
    public void testSolverMoves_06() {

        int[][] tiles = TestReader.getTest("puzzle06.txt");
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        assertEquals(6, solver.moves());
    }



    @Test
    public void testSolverMoves_10() {

        int[][] tiles = TestReader.getTest("puzzle10.txt");
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        assertEquals(10, solver.moves());
    }

    @Test
    public void testSolverMoves_11() {

        int[][] tiles = TestReader.getTest("puzzle11.txt");
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        assertEquals(11, solver.moves());
    }

    @Test
    public void testSolverMoves_24() {

        int[][] tiles = TestReader.getTest("puzzle24.txt");
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        assertEquals(24, solver.moves());
    }


    @Test
    public void testUnsolvable_3() {
        int[][] tiles = new int[][]{
                {1,2,3},
                {4,5,6},
                {8,7,0}
        };

        Board board = new Board(tiles);

        Solver solver = new Solver(board);

        assertFalse(solver.isSolvable());
    }

    @Test
    public void testUnsolvable_4() {
        int[][] tiles = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,15,14,0}
        };

        Board board = new Board(tiles);

        Solver solver = new Solver(board);

        assertFalse(solver.isSolvable());
    }

    @Test
    public void testAll() throws ExecutionException, InterruptedException {
        File[] files = TestReader.getAllFiles();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (File file : files) {
            Future<String> future = executorService.submit(new Task(file));
            try {
                StdOut.println(future.get(3, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                future.cancel(true);
                StdOut.println(file.getName() +  " timed out!");
            }
            StdOut.println("--------------------------");
        }

        executorService.shutdownNow();
    }

    public class Task implements Callable<String> {

        File test;

        public Task(File file) {
            test = file;
        }

        @Override
        public String call() throws Exception {
            runTimedTest(test);
            return "Complete!";
        }
    }

    public void runTimedTest(File testFile) {
        int[][] tiles = TestReader.readFile(testFile);
        Board board = new Board(tiles);

        long start = System.currentTimeMillis();
        long timeToComplete = 0;

        Solver solver = new Solver(board);
        timeToComplete = System.currentTimeMillis() - start;
        String outputTime;

        if (timeToComplete > 1000) {
            outputTime = new String(timeToComplete / 1000.0 + " seconds");
        } else {
            outputTime = new String(timeToComplete + " milliseconds");
        }

        StdOut.println("Is solvable = " + solver.isSolvable());
        StdOut.println("Solution required " + outputTime);
    }
}