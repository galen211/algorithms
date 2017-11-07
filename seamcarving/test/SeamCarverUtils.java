import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class SeamCarverUtils {

    private static File[] loadPictureFiles() {

        File[] files;
        File dir = new File("testfiles");

        Stack<File> stack = new Stack<>();

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".png")) {
                stack.push(file);
            }
        }

        files = new File[stack.size()];
        int count = 0;
        while (!stack.isEmpty()) {
            files[count++] = stack.pop();
        }
        return files;
    }

    private static Picture loadPictureByName(String name) {
        File[] files = loadPictureFiles();

        for (File file : files) {
            if (file.getName().startsWith(name)) {
                return new Picture(file);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        File[] files = loadPictureFiles();
        for (File file : files) {
            StdOut.println(file.getName());
        }
    }
}
