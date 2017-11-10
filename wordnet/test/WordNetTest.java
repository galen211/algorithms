import edu.princeton.cs.algs4.SET;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordNetTest {

    WordNet net;

    @Before
    public void setUp() {
        this.net = new WordNet("testfiles/synsets.txt", "testfiles/hypernyms.txt");
    }

    @Test
    public void assertAcyclic() {

        /*Digraph digraph = new Digraph(new In("testfiles/hypernyms.txt"));

        DirectedCycle dc = new DirectedCycle(digraph);

        Assert.assertFalse(dc.hasCycle());*/
    }

    @Test
    public void testMembership() {
        Assert.assertTrue(net.isNoun("giraffe"));
    }

    @Test
    public void testNounIndexing() {
        SET<Integer> expected = new SET<>();
        expected.add(62);
        expected.add(63);
        expected.add(64);
        expected.add(65);

        /*Bag<Integer> bag = net.nounToVertex.get("Aberdeen");
        Stack<Integer> stack = new Stack<>();
        for (Integer integer : bag) {
            stack.push(integer);
        }

        Assert.assertTrue(stack.size() == expected.size());

        while (!stack.isEmpty()) {
            int val = stack.pop();
            Assert.assertTrue(expected.contains(val));
        }*/
    }

    @Test
    public void checkRoot() {
        // check that root is 38003
    }

    @Test
    public void testNounsCount() {
        int count = 0;
        Iterable<String> itr = net.nouns();
        for (String s : itr) {
            count++;
        }
        Assert.assertEquals(119188, count);
    }

    @Test
    public void testDistance() {

        int dist;
        String wordV;
        String wordW;

        wordV = "white_marlin";
        wordW = "mileage";
        dist = net.distance(wordV, wordW);
        Assert.assertEquals(dist, 23);

        wordV = "Black_Plague";
        wordW = "black_marlin";
        dist = net.distance(wordV, wordW);
        Assert.assertEquals(dist, 33);

        wordV = "American_water_spaniel";
        wordW = "histology";
        dist = net.distance(wordV, wordW);
        Assert.assertEquals(dist, 27);


        wordV = "Brown_Swiss";
        wordW = "barrel_roll";
        dist = net.distance(wordV, wordW);
        Assert.assertEquals(dist, 29);
    }

    @Test
    public void testNounId() {
        // Bag<Integer> bag = net.nounToVertex.get("bird");

        SET<Integer> set = new SET<>();

        /*for (Integer integer : bag) {
            set.add(integer);
        }*/

        SET<Integer> expected = new SET<>();
        expected.add(24306);
        expected.add(24307);
        expected.add(25293);
        expected.add(33764);
        expected.add(70067);

        // need to finish
    }

    @Test
    public void failedTest1() {

        String nounA = "typeface";
        String nounB = "know-it-all";

        int dist = net.distance(nounA, nounB);

        Assert.assertEquals(15, dist);
    }

    @Test
    public void failedTest3() {

        String nounA = "vena_trachealis";
        String nounB = "pathway";

        int dist = net.distance(nounA, nounB);

        Assert.assertEquals(11, dist);
    }

    @Test
    public void failedTest4() {

        String nounA = "Procnias";
        String nounB = "family_Piperaceae";

        String sap = net.sap(nounA, nounB);

        Assert.assertEquals("taxonomic_group taxonomic_category taxon", sap);
    }

    @Test
    public void failedTest6() {

        String nounA;
        String nounB;
        String sap;

        nounA = "Ig";
        nounB = "immunoglobulin_G";

        sap = net.sap(nounA, nounB);

        Assert.assertEquals("immunoglobulin Ig immune_serum_globulin immune_gamma_globulin immune_globulin", sap);

        nounA = "bayberry_tallow";
        nounB = "myoglobin";

        sap = net.sap(nounA, nounB);

        Assert.assertEquals("macromolecule supermolecule", sap);

        nounA = "beta-lipoprotein";
        nounB = "palm_oil";

        sap = net.sap(nounA, nounB);

        Assert.assertEquals("macromolecule supermolecule", sap);
    }
}