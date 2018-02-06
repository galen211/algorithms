import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {

    //TODO: Implement this first.  Be sure not to create explicit copies of the string (e.g., via the substring() method in Java's String data type) when you sort the suffixes. That would take quadratic space. Instead for each suffix, you only need to keep an index that indicates which character is the beginning of the suffix. This way you can build the N suffixes in linear time and space. Then sort this array of indices. It's just like sorting an array of references

    private int[] index;

    private static final int CUTOFF = 15; // 15

    /**
     * circular suffix array of s
     *
     * @param s
     */
    public CircularSuffixArray(String s) {
        index = new int[s.length()];

        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        sort(s,0,s.length() - 1, 0);
    }


    /**
     *
     * @param s the string
     * @param lo lo index of the suffixes to be searched
     * @param hi hi index of the suffixes to be searched
     * @param d the position of the character being sorted
     */
    private void sort(String s, int lo, int hi, int d) {
        if (hi - lo <= CUTOFF) {
            insertion(s, lo, hi, d);
            return;
        }
        // implementation for longer suffixes

        char pivot = charAt(s,index[lo], d);
        int left = lo;
        int right = hi;
        int cursor = lo + 1;
        while (cursor <= right) {
            char c = charAt(s, index[cursor], d);
            if (c < pivot) exch(left++, cursor++);
            else if (c > pivot) exch(cursor, right--);
            else cursor++;
        }

        sort(s, lo, left - 1, d);
        if (pivot >= 0) {
            sort(s, left, right, d + 1);
        }
        sort(s, right + 1, hi, d);
    }

    // insertion sort suffixes between indexes lo to hi
    private void insertion(String s, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(s, j, j - 1, d); j--) {
                exch(j, j - 1);
            }
        }
    }

    // method for retrieving character at index i given an offset
    private char charAt(String s, int i, int offset) {
        return s.charAt((i + offset) % s.length());
    }

    // is suffix i less than suffix j?
    private boolean less(String s, int i, int j, int d) {

        int oi = index[i], oj = index[j];
        char ci, cj;

        int pos = d;
        while (pos < s.length()) {
            ci = charAt(s, oi, pos);
            cj = charAt(s, oj, pos);
            if (ci < cj) return true;
            else if (ci > cj) return false;
            pos++;
        }
        return false;
    }

    // exchange index i of suffix ordered array with index j
    private void exch(int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }

    /**
     * length of s
     *
     * @return
     */
    public int length() {
        return index.length;
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     * @return
     */
    public int index(int i) {
        return index[i]; // needs to perform sorting upon object instantiation
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {

        int SCREEN_WIDTH = 80;
        String s = BinaryStdIn.readString();
        int n = s.length();
        int digits = (int) Math.log10(n) + 1;
        String fmt = "%" + (digits == 0 ? 1 : digits) + "d ";
        StdOut.printf("String length: %d\n", n);
        CircularSuffixArray csa = new CircularSuffixArray(s);

        for (int i = 0; i < n; i++) {
            StdOut.printf(fmt, i);
            for (int j = 0; j < (SCREEN_WIDTH - digits - 1) && j < n; j++) {
                char c = s.charAt((j + csa.index(i)) % n);
                if (c == '\n')
                    c = ' ';
                StdOut.print(c);
            }
            StdOut.println();
        }
    }
}