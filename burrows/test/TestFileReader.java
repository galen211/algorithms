import edu.princeton.cs.algs4.*;
import org.junit.Test;

import java.io.*;

public class TestFileReader {

    private static FileInputStream inputStream;

    /**
     * Redirects the given file to the system standard input
     * @param fileName
     * @throws IOException
     */
    protected static void runFile(String fileName) throws IOException {
        File file = new File(fileName);
        inputStream = new FileInputStream(file);
        System.setIn(inputStream);
    }

    protected static String runFileToString(String fileName) {

        File file = new File(fileName);
        try {
            inputStream = new FileInputStream(file);
            System.setIn(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder("");
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            sb.append(c);
        }

        String s = sb.toString();

        return s;
    }

    protected static void closeInputStream() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void printInputStream(String fileName) {

        try {
            runFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();

        closeInputStream();
    }

    @Test
    public void printBinaryInputStream() {
        printInputStream("testfiles/abra.txt");
    }

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
