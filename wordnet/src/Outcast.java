import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet net;

    /**
     * constructor takes a WordNet object
     *
     * @param wordnet
     */
    public Outcast(WordNet wordnet) {
        net = wordnet;
    }

    /**
     * given an array of WordNet nounToVertex, return an outcast
     *
     * @param nouns
     * @return
     */
    public String outcast(String[] nouns) {

        ST<Integer, String> distNoun = new ST<>();

        for (String n : nouns) {
            int dist = 0;
            for (String c : nouns) {
                if (n.equals(c)) continue;
                int d = net.distance(n, c);
                dist += d;
            }
            distNoun.put(dist, n);
        }

        int maxKey = distNoun.max();

        return distNoun.get(maxKey);
    }

    /**
     * main method for demonstration
     *
     * @param args arg0 and arg1 are inputs to WordNet, rest are nounToVertex for Outcast
     */
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}