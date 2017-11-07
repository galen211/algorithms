import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet net;

    /**
     * constructor takes a WordNet object
     * @param wordnet
     */
    public Outcast(WordNet wordnet){
        net = wordnet;
    }

    /**
     * given an array of WordNet nounToVertex, return an outcast
     * @param nouns
     * @return
     */
    public String outcast(String[] nouns) {

        ST<Integer, String> distance = new ST<>();

        for (String v : nouns) {
            int dist = 0;
            for (String w : nouns) {
                if (v == w) continue;
                int tmp = net.distance(v ,w);
                assert tmp != -1;
                dist += tmp;
            }
            distance.put(dist, v);
        }

        assert distance.size() > 0;

        return distance.get(distance.min());
    }

    /**
     * main method for demonstration
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
    }}