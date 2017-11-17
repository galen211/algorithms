import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;

/**
 * SAP is used to calculate the shortest ancestral path between two vertices v and w in a digraph
 */
public class SAP {

    private final Digraph G;

    private boolean[] marked;
    private int[] distTo;

    private ST<Integer, ST<Integer, Integer>> cache;

    private ST<Integer, Integer> iterableCache;

    private static final int MAX_CACHE_SIZE = 5000;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {
        this.G = new Digraph(G);

        marked = new boolean[G.V()];
        distTo = new int[G.V()];

        cache = new ST<>();
        iterableCache = new ST<>();
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

        // figure out overlap between symbol tables
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
    private ST<Integer, Integer> dfs(int v) {

        if (cache.contains(v)) return cache.get(v);

        assert isArrayCleared();

        ST<Integer, Integer> st = new ST<>();

        Stack<Integer> visited = new Stack<>();

        Queue<Integer> queue = new Queue<>();
        marked[v] = true;
        distTo[v] = 0;
        st.put(v, distTo[v]);
        queue.enqueue(v);
        visited.push(v);

        while (!queue.isEmpty()) {
            int x = queue.dequeue();
            for (int y : G.adj(x)) {
                if (!marked[y]) {
                    distTo[y] = distTo[x] + 1;
                    marked[y] = true;
                    st.put(y, distTo[y]);
                    queue.enqueue(y);
                    visited.push(y);
                }
            }
        }
        cache.put(v, st);

        clearArraysSoft(visited);

        return st;
    }

    private void clearArraysSoft(Stack<Integer> visited) {
        while (!visited.isEmpty()) {
            int i = visited.pop();
            marked[i] = false;
            distTo[i] = 0;
        }
    }

    private boolean isArrayCleared() {
        for (int i = 0; i < marked.length; i++) {
            if (marked[i] || distTo[i] != 0) return false;
        }
        return true;
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
            throw new IllegalArgumentException("Null argument passed to iterable length()");
        }
        validateVertex(v);
        validateVertex(w);

        ancestor(v, w);

        if (iterableCache.isEmpty()) return -1;

        return iterableCache.min();
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
            throw new IllegalArgumentException("Null argument passed to iterable ancestor()");
        }
        validateVertex(v);
        validateVertex(w);

        iterableCache = new ST<>();

        int minAncestor;
        for (int x : v) {
            for (int y : w) {
                int ancestor = ancestor(x, y);
                int length = length(x, y);
                iterableCache.put(length, ancestor);
            }
        }
        if (iterableCache.isEmpty()) return -1;

        minAncestor = iterableCache.min(); // there can be more than one, but it doesn't matter which one we pick
        return iterableCache.get(minAncestor);
    }

    // private method for validating arguments
    private void validateVertex(int v) {
        int V = G.V();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    private void validateVertex(Iterable<Integer> v) {
        for (int integer : v) {
            validateVertex(integer);
        }
    }

    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}