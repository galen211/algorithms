import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.StringTokenizer;

public class WordNet {

    private final Digraph digraph;
    private final ST<String, Bag<Integer>> nounToVertex; // nouns and the vertices in which they are contained
    private final ST<Integer, String> vertexToSynset; // vertices and their synsets
    private final SAP sap;
    private int size;

    /**
     * constructor takes the name of the two input files
     *
     * @param synsets   the synsets file
     * @param hypernyms the corresponding hypernyms file
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Constructor arguments must not be null");
        }

        nounToVertex = new ST<>();
        vertexToSynset = new ST<>();

        readSynsetFile(synsets);

        digraph = new Digraph(size);

        readHypernymFile(hypernyms);

        if (!isDAG()) throw new IllegalArgumentException("The specified graph is not a directed acyclic graph");

        sap = new SAP(digraph); // initialize shortest ancestral path instance variable

        assert vertexToSynset.size() == digraph.V();
    }

    private boolean isDAG() {
        DirectedCycle cycle = new DirectedCycle(digraph);
        if (cycle.hasCycle()) return false;

        int v = StdRandom.uniform(0, digraph.V()); // choose random vertex to start searching for root

        int last = -1;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            last = queue.dequeue();
            for (int w : digraph.adj(last)) {
                queue.enqueue(w);
            }
        }

        assert last != -1;

        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(digraph.reverse(), last); // reverse graph (root should be able to reach every vertex)

        for (int i = 0; i < digraph.V(); i++) {
            if (!bfs.hasPathTo(i)) return false;
        }
        return true;
    }

    private void readHypernymFile(String hypernyms) {
        // create the digraph
        In in = new In(hypernyms);
        String line;
        int v;
        int w;
        StringTokenizer st;
        while ((line = in.readLine()) != null) {
            st = new StringTokenizer(line, ",");
            v = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                w = Integer.parseInt(st.nextToken());
                digraph.addEdge(v, w);
            }
        }
    }

    private void readSynsetFile(String synsets) {

        // construct the hash tables
        In in = new In(synsets);
        String line;
        int lineCount = 0;
        while ((line = in.readLine()) != null) {
            lineCount++;
            String[] str = line.split(",");
            int value = Integer.parseInt(str[0]);
            vertexToSynset.put(value, str[1]);
            Bag<Integer> bag;
            for (String word : str[1].split(" ")) {
                if (nounToVertex.contains(word)) {
                    nounToVertex.get(word).add(value);
                } else {
                    bag = new Bag<>();
                    bag.add(value);
                    nounToVertex.put(word, bag);
                }
            }
        }

        this.size = lineCount;
    }

    /**
     * returns all WordNet nounToVertex
     *
     * @return
     */
    public Iterable<String> nouns() {
        return nounToVertex.keys();
    }

    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument to isNoun() is null");
        }

        return nounToVertex.contains(word);
    }

    /**
     * distance between nounA and nounB (defined below)
     *
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Argument to distance() is not valid noun");
        }

        Bag<Integer> v = nounToVertex.get(nounA);
        Bag<Integer> w = nounToVertex.get(nounB);

        return sap.length(v, w);
    }

    /**
     * a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB in a shortest ancestral
     * path (defined below)
     *
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Argument to sap() not valid noun");
        }

        Bag<Integer> v = nounToVertex.get(nounA);
        Bag<Integer> w = nounToVertex.get(nounB);

        int ancestor = sap.ancestor(v, w);

        return vertexToSynset.get(ancestor);
    }

    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}