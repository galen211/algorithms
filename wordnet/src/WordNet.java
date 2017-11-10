import edu.princeton.cs.algs4.*;

import java.util.StringTokenizer;

public class WordNet {

    private final Digraph digraph;
    private final ST<String, Bag<Integer>> nounToVertex; // nouns and the vertices in which they are contained
    private final ST<Integer, String> vertexToSynset; // vertices and their synsets
    private final SAP sap;

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

        // construct the hash tables
        In in = new In(synsets);
        String line;
        StringTokenizer st;
        int lineCount = 0;
        while ((line = in.readLine()) != null) {
            lineCount++;
            st = new StringTokenizer(line, ",");
            Integer value = Integer.parseInt(st.nextToken());
            String words = st.nextToken();
            vertexToSynset.put(value, words);
            Bag<Integer> bag = new Bag<>();
            for (String word : words.split(" ")) {
                if (nounToVertex.contains(word)) {
                    bag = nounToVertex.get(word);
                    bag.add(value);
                    nounToVertex.put(word, bag);
                } else {
                    bag.add(value);
                    nounToVertex.put(word, bag);
                }
            }
        }

        digraph = new Digraph(lineCount);

        // create the digraph
        in = new In(hypernyms);
        int v;
        int w;
        while ((line = in.readLine()) != null) {
            st = new StringTokenizer(line, ",");
            v = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                w = Integer.parseInt(st.nextToken());
                digraph.addEdge(v, w);
            }
        }

        // check if the graph is acyclic
        DirectedCycle cycle = new DirectedCycle(digraph);
        if (cycle.hasCycle()) throw new IllegalArgumentException("The specified graph is not a directed acyclic graph");

        sap = new SAP(digraph); // initialize shortest ancestral path instance variable

        assert vertexToSynset.size() == digraph.V();
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