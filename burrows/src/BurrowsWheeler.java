import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    /**
     * apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
     */
    public static void encode() {
        StringBuilder str = new StringBuilder();
        while (!BinaryStdIn.isEmpty())
            str = str.append(BinaryStdIn.readChar());
        CircularSuffixArray csa = new CircularSuffixArray(str.toString());
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i, 32);
                // System.out.printf("%c%c%c%c",0,0,0,i);
                break;
            }
        }
        for (int i = 0; i < csa.length(); i++) {
            // System.out.print(str.charAt((csa.index(i) - 1 + csa.length()) %
            // csa.length()));
            BinaryStdOut.write(str.charAt((csa.index(i) - 1 + csa.length())
                    % csa.length()));
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }


    /**
     * apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
     */
    public static void decode() {

    }

    /**
     * If args[0] is '-', apply Burrows-Wheeler encoding. If args[0] is '+', apply Burrows-Wheeler decoding
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else if (args[0].equals("+"))
            decode();
        else
            return;
    }
}