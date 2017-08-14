import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ReadAllStringsTester {

    public static void main(String[] args) {
        String[] in = StdIn.readAllStrings();

        for (String s : in) {
            StdOut.println(s);
        }
    }
}

