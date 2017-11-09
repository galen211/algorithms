import edu.princeton.cs.algs4.*;

import java.io.File;
import java.io.FilenameFilter;

public class TestFileReader {

    public static Digraph readDigraph(File file) {

        In in = new In(file);
        Digraph G = new Digraph(in);
        return G;
    }

    public static Stack<File> getFiles(String filter) {

        File dir = new File("testfiles");
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.startsWith(filter)) return true;
                else return false;
            }
        };

        Stack<File> fileStack = new Stack<>();
        for (File file : dir.listFiles()) {
            if (filenameFilter.accept(dir, file.getName())) {
                fileStack.push(file);
            }
        }

        return fileStack;
    }

    public static void main(String[] args) {
        Stack<File> fileStack = getFiles("digraph");
        while (!fileStack.isEmpty()) {
            StdOut.println(fileStack.pop().getName());
        }

        fileStack = getFiles("outcast");
        while (!fileStack.isEmpty()) {
            StdOut.println(fileStack.pop().getName());
        }
    }
}
