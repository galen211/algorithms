import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class TopologicalOrder {

    public static void main(String[] args) {

        Digraph digraph = new Digraph(9);
        digraph.addEdge(0,3);
        digraph.addEdge(0,4);
        digraph.addEdge(1,3);
        digraph.addEdge(1,4);
        digraph.addEdge(1,5);
        digraph.addEdge(2,4);
        digraph.addEdge(2,5);
        digraph.addEdge(3,6);
        digraph.addEdge(3,7);
        digraph.addEdge(4,6);
        digraph.addEdge(4,7);
        digraph.addEdge(4,8);
        digraph.addEdge(5,7);
        digraph.addEdge(5,8);

        DepthFirstOrder dfs = new DepthFirstOrder(digraph);
        Iterable<Integer> order = dfs.pre();

        for (Integer integer : order) {
            StdOut.println(integer);
        }
    }
}
