import java.io.*;
import java.util.StringTokenizer;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TestFileReader {

    public static Queue<File> getAllFiles() {
        File test = new File("testfiles");
        Queue<File> queue = new Queue<>();

        File[] files = test.listFiles();
        for (File file : files) {
            if (accept(file)) {
                queue.enqueue(file);
            }
        }
        return queue;
    }

    private static boolean accept(File file) {
        return file.getName().endsWith(".txt");
    }

    public static File getTest(String testName) {
        File f = new File("testfiles");
        File testfile = new File(f,testName);

        System.out.println("In File: " + testfile.getName());

        return testfile;
    }

    public static PointSET parsePointSET(String testName) {

        File file = getTest(testName);

        PointSET ps = new PointSET();

        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            int numTokens = 2;

            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

                assert st.countTokens() == numTokens; // must be two points per line

                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());

                //StdOut.println("Adding Point: " + "(" + x + ", " + y + ")");
                ps.insert(new Point2D(x,y));
            }
            return ps;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static KdTree parseKdTree(String testName) {

        File file = getTest(testName);

        KdTree kdTree = new KdTree();

        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            int numTokens = 2;

            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

                assert st.countTokens() == numTokens; // must be two points per line

                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());

                //StdOut.println("Adding Point: " + "(" + x + ", " + y + ")");
                kdTree.insert(new Point2D(x,y));
            }
            return kdTree;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
