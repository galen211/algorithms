import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class WordSET {

    private static final int R = 26;
    private static final int O = 65; // offset to get to 'A'

    private Node root;
    private int n;

    private class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    /**
     * Public constructor for WordSET
     */
    public WordSET() {

    }

    /**
     * Does set contain the given word?
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node x = get(root, word, 0);
        if (x == null) return false;
        return x.isString;
    }

    /**
     * Add the given word to the set
     * @param word
     */
    public void add(String word) {
        if (word == null) throw new IllegalArgumentException("argument to add() is null");
        root = add(root, word, 0);
    }

    /**
     * The number of words in the set
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * If the set is empty
     * @return
     */
    public boolean isEmpty(){
        return n==0;
    }

    public Iterator<String> iterator(){
        return wordsWithPrefix("").iterator();
    }

    /**
     * Get words starting with prefix
     * @param prefix
     * @return iterable of words in the set
     */
    public Iterable<String> wordsWithPrefix(String prefix) {
        Queue<String> results = new Queue<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    /**
     * Determines whether there are any words that have the given prefix
     * @param prefix
     * @return
     */
    public boolean hasWordsWithPrefix(String prefix) {
        Node x = get(root, prefix, 0);
        if (x == null) return false;
        return true;
    }

    // private method to compute all words in set with a given prefix
    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.isString) results.enqueue(prefix.toString());
        for (char c = 'A'; c <= 'Z'; c++) {
            prefix.append(c);
            collect(x.next[c - O], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // private get method for traversing Trie nodes
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c - O], key, d + 1);
    }

    // private add method for inserting words into set
    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (!x.isString) {
                x.isString = true;
                n++;
            }
        } else {
            char c = key.charAt(d);
            x.next[c - O] = add(x.next[c - O], key, d + 1);
        }
        return x;
    }
}
