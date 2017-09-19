import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.Iterator;

public class Solver {

    private final Board original;
    private final Board twin;

    private final BoardComparator comparator;
    private final MinPQ<SearchNode> pqOrig;
    private final MinPQ<SearchNode> pqTwin;

    private final Stack<Board> moves;
    private final int numMoves;
    private final boolean solvable;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial
     */
    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException("Nothing to solve");
        }

        original = initial;
        twin = original.twin();

        comparator = new BoardComparator();
        pqOrig = new MinPQ<>(comparator);
        pqTwin = new MinPQ<>(comparator);

        SearchNode lastMove = simultaneousSolution();
        if (lastMove.isSolved()) {
            solvable = true;

            moves = new Stack<>();
            SearchNode node = lastMove;
            while (node != null) {
                moves.push(node.board);
                node = node.prev;
            }
            numMoves = lastMove.moves;
        } else {
            moves = null;
            numMoves = -1;
            solvable = false;
        }
    }

    private SearchNode nextSearchNode(MinPQ<SearchNode> pq) {

        SearchNode current = pq.delMin();

        Iterator<Board> itr = current.board.neighbors().iterator();

        Board next;
        while (itr.hasNext()) {
            next = itr.next();
            if (current.prev != null && current.prev.board.equals(next)) continue;
            else pq.insert(new SearchNode(next, current.moves + 1, current));
        }

        return pq.min();
    }

    private SearchNode simultaneousSolution() {

        SearchNode origSearchNode = new SearchNode(original, 0, null);
        SearchNode twinSearchNode = new SearchNode(twin, 0, null);

        pqOrig.insert(origSearchNode);
        pqTwin.insert(twinSearchNode);

        while (!origSearchNode.isSolved() && !twinSearchNode.isSolved()) {
            origSearchNode = nextSearchNode(pqOrig);
            twinSearchNode = nextSearchNode(pqTwin);
        }

        return origSearchNode;
    }

    /**
     * is the initial board solvable?
     *
     * @return true if solvable
     */
    public boolean isSolvable() {
        return solvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     *
     * @return
     */
    public int moves() {
        return numMoves;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     *
     * @return
     */
    public Iterable<Board> solution() {
        return moves;
    }

    private class BoardComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {

            if (o1.priority < o2.priority) return -1;
            else if (o1.priority > o2.priority) return 1;
            else {
                if (o1.hamming() < o2.hamming()) return -1;
                else if (o1.hamming() > o2.hamming()) return 1;
                else {
                    if (o1.manhattan() < o2.manhattan()) return -1;
                    else if (o1.manhattan() > o2.manhattan()) return 1;
                    else return 0;
                }
            }
        }
    }

    private class SearchNode {
        private final Board board;
        private final int moves;
        private final int priority;
        private final SearchNode prev;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.priority = board.manhattan() + moves;
        }

        // getters for code readability
        public int manhattan() {
            return board.manhattan();
        }

        public int hamming() {
            return board.hamming();
        }

        public boolean isSolved() {
            return board.isGoal();
        }
    }
}
