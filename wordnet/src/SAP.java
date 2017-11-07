import edu.princeton.cs.algs4.*;

/**
 * SAP is used to calculate the shortest ancestral path between two vertices v and w in a digraph
 */
public class SAP {

    private static boolean[] marked;
    private static int[] distTo;

    private Digraph G;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     * @param G
     */
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        int a = ancestor(v,w);
        if (a == -1) return -1;

        int l1 = lengthTo(v, a);
        int l2 = lengthTo(w, a);

        assert l1 != -1 && l2 != -1;

        return (l1 + l2);
    }



    // private method for validating arguments
    private void validateVertex(int v) {
        int V = G.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        marked = new boolean[G.V()];

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        queue.enqueue(w);

        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            int x = queue.dequeue();
            if (marked[x]) {
                stack.push(x);
            } else {
                marked[x] = true;
                for (Integer y : G.adj(x)) {
                    queue.enqueue(y);
                }
            }
        }

        if (stack.isEmpty()) return -1;

        int minDist = Integer.MAX_VALUE;
        int minAncestor = -1;
        int distV;
        int distW;
        int dist;
        while (!stack.isEmpty()) {
            int a = stack.pop(); // the ancestor
            distV = lengthTo(v, a);
            distW = lengthTo(w, a);
            if (distV == -1 || distW == -1) continue;
            dist = distV + distW;
            if (dist < minDist) {
                minDist = dist;
                minAncestor = a;
            }
        }
        return minAncestor;
    }

    // length from v to ancestor
    private int lengthTo(int v, int w) {

        distTo = new int[G.V()];
        marked = new boolean[G.V()];

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            int x = queue.dequeue();
            if (x == w) return distTo[x];
            else if (marked[x]) continue;
            else {
                marked[x] = true;
                for (Integer y : G.adj(x)) {
                    distTo[y] = distTo[x] + 1;
                    queue.enqueue(y);
                }
            }
        }

        assert queue.isEmpty();

        return -1; // w is not an ancestor of v
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        Stack<Integer> lengths = new Stack<>();

        int len;
        for (Integer x : v) {
            for (Integer y : w) {
                len = length(x,y);
                if (len == -1) continue;
                else lengths.push(len);
            }
        }
        if (lengths.isEmpty()) return -1;

        int min = Integer.MAX_VALUE;
        while (!lengths.isEmpty()) {
            int x = lengths.pop();
            if (x < min) min = x;
        }
        return min;
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        ST<Integer, Integer> st = new ST<>();

        int len;
        int anc;

        for (Integer x : v) {
            for (Integer y : w) {
                if ((anc = ancestor(x,y)) == -1) {
                    continue;
                } else {
                    len = length(x, y);
                    st.put(len, anc);
                }
            }
        }

        if (st.isEmpty()) return -1;

        return st.get(st.min());
    }

    /**
     * do unit testing of this class
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}