import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;

/**
 * SAP is used to calculate the shortest ancestral path between two vertices v and w in a digraph
 */
public class SAP {

    private final Digraph G;

    private Queue<Integer> visited;
    private boolean[] marked;
    private int[] distTo;

    private ST<Integer, ST<Integer, Integer>> cache;
    private final int MAX_CACHE_SIZE = 100;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {
        this.G = new Digraph(G);

        visited = new Queue<>();
        marked = new boolean[G.V()];
        distTo = new int[G.V()];

        cache = new ST<>();
    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        int a = ancestor(v, w);

        if (a == -1) return -1;

        assert cache.contains(v);
        assert cache.contains(w);

        ST<Integer, Integer> stV = cache.get(v);
        ST<Integer, Integer> stW = cache.get(w);

        int dV = stV.get(a);
        int dW = stW.get(a);

        return (dV + dW);
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        // symbol table with vertices and their associated distances
        ST<Integer, Integer> stV = dfs(v);
        ST<Integer, Integer> stW = dfs(w);

        // create a combined set of
        SET<Integer> ancestors = new SET<>();
        for (int key : stV.keys()) {
            if (stW.contains(key)) {
                ancestors.add(key);
            }
        }

        if (ancestors.isEmpty()) return -1;

        // iterate over the ancestors to find the nearest ancestor
        int ancestor = -1;
        int dist;
        int minDist = Integer.MAX_VALUE;
        for (int a : ancestors) {
            int distV = stV.get(a);
            int distW = stW.get(a);
            dist = distV + distW;
            if (dist < minDist) {
                ancestor = a;
                minDist = dist;
            }
        }
        assert ancestor != -1;

        if (cache.size() > MAX_CACHE_SIZE) {
            clearCache();
            cache.put(v, stV);
            cache.put(w, stW);
        }

        return ancestor;
    }

    // depth first search
    private ST<Integer,Integer> dfs(int v) {

        if (cache.contains(v)) return cache.get(v);

        assert visited.isEmpty();

        ST<Integer, Integer> st = new ST<>();

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        visited.enqueue(v);
        marked[v] = true;

        while (!queue.isEmpty()) {
            int x = queue.dequeue();
            st.put(x, distTo[x]);
            for (int y : G.adj(x)) {
                if (!marked[y]) {
                    distTo[y] = distTo[x] + 1;
                    marked[y] = true;
                    queue.enqueue(y);
                    visited.enqueue(y);
                }
            }
        }
        clearArraysSoft();

        cache.put(v, st);

        return st;
    }

    private void clearArraysSoft() {
        while (!visited.isEmpty()) {
            int i = visited.dequeue();
            marked[i] = false;
            distTo[i] = 0;
        }
    }

    private void clearCache() {
        cache = new ST<>();
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("Arguments to length() must not be null");
        }

        int minDist = Integer.MAX_VALUE;
        for (int i : v) {
            int dist = 0;
            for (int j : v) {
                if (i == j) continue;
                dist += length(i, j);
            }
            if (dist < minDist) {
                minDist = dist;
            }
        }

        return minDist;
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("Arguments to ancestor() must not be null");
        }

        int minDist = Integer.MAX_VALUE;
        int minAnc = -1;

        for (int i : v) {
            int dist = 0;
            int anc = -1;
            for (int j : v) {
                if (i == j) return i; // an element of v also member of w
                dist += length(i, j);
                anc = ancestor(i, j);
            }
            if (dist < minDist) {
                assert anc != -1;
                minDist = dist;
                minAnc = anc;
            }
        }
        return minAnc;
    }

    // private method for validating arguments
    private void validateVertex(int v) {
        int V = G.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}