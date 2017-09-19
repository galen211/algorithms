import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void testBoardConstruction() {

        File[] files = TestReader.getAllFiles();
        Board[] boards = new Board[files.length];

        int[][] tiles;
        for (int i = 0; i < files.length; i++) {
            tiles = TestReader.readFile(files[i]);
            boards[i] = new Board(tiles);
            StdOut.println("Board Dimension: " + boards[i].dimension());

            assertEquals(boards[i].dimension(), tiles.length);
        }
    }

    @Test
    public void testBoardToString() {
        File[] files = TestReader.getAllFiles();
        Board[] boards = new Board[files.length];

        int[][] tiles;
        for (int i = 0; i < files.length; i++) {
            tiles = TestReader.readFile(files[i]);
            boards[i] = new Board(tiles);
            StdOut.print(boards[i].toString());
        }
    }

    @Test
    public void simpleTestManhattan() {
        int[][] tiles = new int[][]{
                {8,1,3},
                {4,0,2},
                {7,6,5}
        };

        Board board = new Board(tiles);

        assertEquals(10, board.manhattan());
    }

    @Test
    public void testManhattan() {

        int[][] tiles_1 = new int[][]{
                {0,1,3},
                {4,2,5},
                {7,8,6}
        };

        int[][] tiles_2 = new int[][]{
                {1,0,3},
                {4,2,5},
                {7,8,6}
        };

        int[][] tiles_3 = new int[][]{
                {4,1,3},
                {0,2,5},
                {7,8,6}
        };

        int[][] tiles_4 = new int[][]{
                {1,2,3},
                {4,0,5},
                {7,8,6}
        };

        int[][] tiles_5 = new int[][]{
                {1,3,0},
                {4,2,5},
                {7,8,6}
        };

        Board board_1 = new Board(tiles_1);
        Board board_2 = new Board(tiles_2);
        Board board_3 = new Board(tiles_3);
        Board board_4 = new Board(tiles_4);
        Board board_5 = new Board(tiles_5);

        assertEquals(4, board_1.manhattan());
        assertEquals(3, board_2.manhattan());
        assertEquals(5, board_3.manhattan());
        assertEquals(2, board_4.manhattan());
        assertEquals(4, board_5.manhattan());
    }

    @Test
    public void testTwin() {
        // in no case should the zero move from the original position

        int[][] tiles_1 = new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board_1 = new Board(tiles_1);
        StdOut.println(board_1.toString());
        printTwin(board_1, 3);
        StdOut.println("---------------------------");

        int[][] tiles_2 = new int[][]{
                {1,0,3},
                {4,2,5},
                {7,8,6}
        };
        Board board_2 = new Board(tiles_2);
        StdOut.println(board_2.toString());
        printTwin(board_2, 3);
        StdOut.println("---------------------------");

        int[][] tiles_3 = new int[][]{
                {4,1,3},
                {0,2,5},
                {7,8,6}
        };
        Board board_3 = new Board(tiles_3);
        StdOut.println(board_3.toString());
        printTwin(board_3, 3);
        StdOut.println("---------------------------");

        int[][] tiles_4 = new int[][]{
                {1,2,3},
                {4,0,5},
                {7,8,6}
        };
        Board board_4 = new Board(tiles_4);
        StdOut.println(board_4.toString());
        printTwin(board_4, 3);
        StdOut.println("---------------------------");

        int[][] tiles_5 = new int[][]{
                {1,3,0},
                {4,2,5},
                {7,8,6}
        };
        Board board_5 = new Board(tiles_5);
        StdOut.println(board_5.toString());
        printTwin(board_5, 3);
        StdOut.println("---------------------------");
    }

    @Test
    public void testNeighbors() {

        int[][] tiles_1 = new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board_1 = new Board(tiles_1);
        StdOut.println(board_1.toString());
        printNeighbors(board_1);
        StdOut.println("---------------------------");

        int[][] tiles_2 = new int[][]{
                {1,0,3},
                {4,2,5},
                {7,8,6}
        };
        Board board_2 = new Board(tiles_2);
        StdOut.println(board_2.toString());
        printNeighbors(board_2);
        StdOut.println("---------------------------");

        int[][] tiles_3 = new int[][]{
                {4,1,3},
                {0,2,5},
                {7,8,6}
        };
        Board board_3 = new Board(tiles_3);
        StdOut.println(board_3.toString());
        printNeighbors(board_3);
        StdOut.println("---------------------------");

        int[][] tiles_4 = new int[][]{
                {1,2,3},
                {4,0,5},
                {7,8,6}
        };
        Board board_4 = new Board(tiles_4);
        StdOut.println(board_4.toString());
        printNeighbors(board_4);
        StdOut.println("---------------------------");

        int[][] tiles_5 = new int[][]{
                {1,3,0},
                {4,2,5},
                {7,8,6}
        };
        Board board_5 = new Board(tiles_5);
        StdOut.println(board_5.toString());
        printNeighbors(board_5);
        StdOut.println("---------------------------");
    }

    public static void printTwin(Board board, int times) {

        for (int i = 0; i < times; i++) {
            StdOut.println("Twin #" + (i+1));
            StdOut.print(board.twin());
        }
    }

    public static void printNeighbors(Board board) {
        Iterable<Board> neighbors = board.neighbors();
        Iterator<Board> itr = neighbors.iterator();

        int counter = 0;
        while (itr.hasNext()) {
            StdOut.println("Neighbor #" + ++counter);
            board = itr.next();
            StdOut.print(board.toString());
        }
    }

}