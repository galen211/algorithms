import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.io.FilenameFilter;

public class TestFileReader {

    /**
     * General class for getting all files that begin with some string
     * @param filter
     * @return
     */
    public static Stack<File> getFilesBeginWith(String filter) {

        File dir = new File("testfiles");
        FilenameFilter filenameFilter = (fileDir, fileName) -> {
            if (fileName.startsWith(filter)) return true;
            else return false;
        };

        Stack<File> fileStack = new Stack<>();
        for (File file : dir.listFiles()) {
            if (filenameFilter.accept(dir, file.getName())) {
                fileStack.push(file);
            }
        }

        return fileStack;
    }

    /**
     * General class for getting all files that end with a given extension (i.e. txt)
     * @param filter
     * @return
     */
    public static Stack<File> getFilesExtension(String filter) {

        File dir = new File("testfiles");

        FilenameFilter filenameFilter = (fileDir, fileName) -> {
            if (fileName.endsWith(filter)) return true;
            else return false;
        };

        Stack<File> fileStack = new Stack<>();
        for (File file : dir.listFiles()) {
            if (filenameFilter.accept(dir, file.getName())) {
                fileStack.push(file);
            }
        }

        return fileStack;
    }

    /**
     * Test the class by inserting routines into main method
     * @param args
     */
    public static void main(String[] args) {

    }
}
