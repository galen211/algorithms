import edu.princeton.cs.algs4.*;

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

        int dist;
        int maxDist = Integer.MIN_VALUE;
        String outlier = null;
        for (String v : nouns) {
            dist = 0;
            for (String w : nouns) {
                if (v.equals(w)) continue;
                int d = net.distance(v, w);
                if (d == -1) continue;
                else dist += d;
            }
            if (dist > maxDist) {
                maxDist = dist;
                outlier = v;
            }

        }
        return outlier;
    }

    private class OutcastNoun implements Comparable<OutcastNoun> {

        private int dist;
        private String noun;

        public OutcastNoun(String noun, int distance) {
            this.noun = noun;
            this.dist = distance;
        }

        public int getDist() {
            return this.dist;
        }

        public String getNoun() {
            return this.noun;
        }

        @Override
        public int compareTo(OutcastNoun that) {
            int cmp = this.dist - that.getDist();
            if (cmp > 0) return 1;
            else if (cmp == 0) return 0;
            else return -1;
        }
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