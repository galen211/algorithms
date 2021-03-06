import edu.princeton.cs.algs4.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SAPTest {

    ST<String, Digraph> digraphs;
    ST<String, SAP> saps;

    @Before
    public void setUpDigraph() {

        digraphs = new ST<>();
        saps = new ST<>();

        Stack<File> files = TestFileReader.getFiles("digraph");

        while (!files.isEmpty()){
            File file = files.pop();
            String fileName = file.getName();
            String[] key = fileName.split("[.]");
            Digraph dg = TestFileReader.readDigraph(file);
            SAP sap = new SAP(dg);
            digraphs.put(key[0], dg);
            saps.put(key[0], sap);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAncestorIterableNullArgument() {

        Digraph digraph = digraphs.get("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        v.add(5);
        v.add(7);
        v.add(8);

        SAP sap = new SAP(digraph);

        sap.ancestor(v, null);
    }

    @Test
    public void digraph_functions() {

        Digraph dg = digraphs.get("digraph1");

        DirectedDFS dfs = new DirectedDFS(dg, 11);

        for (int v = 0; v < dg.V(); v++) {
            if (dfs.marked(v))
                StdOut.print(v + " ");
        }

    }

    @Test
    public void testAncestor() {

        SAP sap = saps.get("digraph1");

        int ancestor;

        ancestor = sap.ancestor(3, 11);
        Assert.assertEquals(ancestor, 1);

        ancestor = sap.ancestor(7, 8);
        Assert.assertEquals(ancestor, 3);

        ancestor = sap.ancestor(4, 11);
        Assert.assertEquals(ancestor, 1);

        ancestor = sap.ancestor(2, 9);
        Assert.assertEquals(ancestor, 0);

        ancestor = sap.ancestor(0, 1);
        Assert.assertEquals(ancestor, 0);
    }

    @Test
    public void testAncestorLength() {

        SAP sap = saps.get("digraph1");

        int length = sap.length(12,5);
        int ancestor = sap.ancestor(12, 5);

    }

    @Test
    public void testSAPImmutable() {

        // check if SAP is immutable
    }

    @Test
    public void testLength() {

        SAP sap = saps.get("digraph1");

        int length;

        length = sap.length(3, 11);
        Assert.assertEquals(length, 4);
    }

    @Test
    public void testDigraph1() {

        SAP sap = saps.get("digraph1");

        int length;
        int ancestor;

        // 3, 11, length = 4, ancestor = 1
        length = sap.length(3,11);
        ancestor = sap.ancestor(3, 11);

        Assert.assertEquals(length, 4);
        Assert.assertEquals(ancestor, 1);

        // 9, 12, length = 3, ancestor = 5
        length = sap.length(9, 12);
        ancestor = sap.ancestor(9, 12);

        Assert.assertEquals(length, 3);
        Assert.assertEquals(ancestor, 5);

        // 7, 2, length = 4, ancestor = 0
        length = sap.length(7, 2);
        ancestor = sap.ancestor(7, 2);

        Assert.assertEquals(length, 4);
        Assert.assertEquals(ancestor, 0);

        // 1, 6, length = -1, ancestor = -1
        length = sap.length(1, 6);
        ancestor = sap.ancestor(1, 6);

        Assert.assertEquals(length, -1);
        Assert.assertEquals(ancestor, -1);
    }

    @Test
    public void testDigraph2() {

        SAP sap = saps.get("digraph2");

        int length = sap.length(1,3);
        Assert.assertTrue(length == 2);

        int ancestor = sap.ancestor(1, 3);
        Assert.assertTrue(ancestor == 3);

        length = sap.length(1,5);
        Assert.assertTrue(length == 2);

        ancestor = sap.ancestor(1, 5);
        Assert.assertTrue(ancestor == 0);

        length = sap.length(1,4);
        Assert.assertTrue(length == 3);

        ancestor = sap.ancestor(1, 4);
        Assert.assertTrue(ancestor == 0 || ancestor == 4);
    }

    @Test
    public void testDigraph3() {

        SAP sap = saps.get("digraph3");

        int actualLength;
        int actualAncestor;

        // 8, 0
        actualLength = sap.length(8,0);
        Assert.assertEquals(3, actualLength);

        actualAncestor = sap.ancestor(8, 0);
        Assert.assertEquals(8, actualAncestor);

        // 1, 8
        actualLength = sap.length(1,8);
        Assert.assertEquals(-1, actualLength);

        actualAncestor = sap.ancestor(1, 8);
        Assert.assertEquals(-1, actualAncestor);

        // 13, 12
        actualLength = sap.length(13,12);
        Assert.assertEquals(4, actualLength);

        actualAncestor = sap.ancestor(13, 12);
        Assert.assertEquals(12, actualAncestor);
    }

    @Test
    public void failedTest_1() {

        SAP sap = setupSAP("digraph-wordnet");

        int length = sap.length(64451, 25327);

        Assert.assertEquals(15, length);

    }

    @Test
    public void failedTest_2() {

        SAP sap = setupSAP("digraph-wordnet");

        int length = sap.length(7093, 21191);

        Assert.assertEquals(13, length);
    }

    @Test
    public void testIterableLength_1() {

        SAP sap = setupSAP("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        v.add(38687);
        v.add(50710);

        Bag<Integer> w = new Bag<>();
        w.add(27570);

        int length = sap.length(v, w);

        Assert.assertEquals(13, length);
    }

    @Test
    public void testIterableLength_2() {

        SAP sap = setupSAP("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        v.add(18497);
        v.add(42500);

        Bag<Integer> w = new Bag<>();
        w.add(28886);
        w.add(48978);

        int length = sap.length(v, w);

        Assert.assertEquals(8, length);
    }

    @Test
    public void testIterableLength_3() {

        SAP sap = setupSAP("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        v.add(6);
        v.add(10);
        v.add(16);
        v.add(19);

        Bag<Integer> w = new Bag<>();
        w.add(1);
        w.add(6);
        w.add(16);
        w.add(17);

        int ancestor = sap.ancestor(v, w);
        Assert.assertEquals(6, ancestor);

        int length = sap.length(v, w);
        Assert.assertEquals(0, length);
    }

    @Test
    public void testIterableLength_4() {

        SAP sap = setupSAP("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();

        v.add(7093);
        w.add(21191);

        int length = sap.length(v, w);
        Assert.assertEquals(13, length);
    }

    @Test
    public void testIterableLength_5() {
        SAP sap = setupSAP("digraph-wordnet");

        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();

        v.add(6202);
        w.add(60136);
        w.add(77834);


        int length = sap.length(v, w);
        Assert.assertEquals(14, length);
    }

    private SAP setupSAP(String digraphName) {
        Digraph dg = digraphs.get(digraphName);

        return new SAP(dg);
    }

}