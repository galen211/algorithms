import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int R = 256;

    /**
     * apply move-to-front encoding, reading from standard input and writing to standard output
     */
    public static void encode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; chars[i] = i++);

        char count, ch, tmpIn, tmpOut;

        while (!BinaryStdIn.isEmpty()) {
            ch = BinaryStdIn.readChar();
            for (count = 0, tmpOut = chars[0]; ch != chars[count]; count++) {
                tmpIn = chars[count];
                chars[count] = tmpOut;
                tmpOut = tmpIn;
            }
            chars[count] = tmpOut;
            BinaryStdOut.write(count);
            chars[0] = ch;
        }
        BinaryStdOut.close();
    }


    /**
     * apply move-to-front decoding, reading from standard input and writing to standard output
     */
    public static void decode() {
        char[] chars = new char[R];
        for (char i = 0; i < R; chars[i] = i++);
        char count, ch;
        while (!BinaryStdIn.isEmpty()) {
            count = BinaryStdIn.readChar();
            for (ch = chars[count]; count > 0; chars[count] = chars[--count]);
            chars[count] = ch; // assert count == 0;
            BinaryStdOut.write(ch);
        }
        BinaryStdOut.close();
    }


    /**
     * if args[0] is '-', apply move-to-front encoding if args[0] is '+', apply move-to-front decoding
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