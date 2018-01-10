import edu.princeton.cs.algs4.StdOut;

public class Test {

    public static void main(String[] args) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int R = 26;

        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c);
        }
        StdOut.println(sb.toString());
    }
}

