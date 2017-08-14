import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class FileTester {

    public static Point[] readFile(File file){

        ArrayDeque<Point> deque = new ArrayDeque<>();
        int numPoints = 0;

        StdOut.println("In File: " + file.getName().toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            line = reader.readLine().trim();
            numPoints = Integer.parseInt(line);
            int i = 0;
            while (i < numPoints){
                line = reader.readLine();
                if (line.isEmpty()){
                    continue;
                }

                StringTokenizer st = new StringTokenizer(line, " ");
                int x = 0; int y = 0;
                while (st.hasMoreTokens()){
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                }
                deque.addLast(new Point(x,y));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert numPoints == deque.size();

        Point[] points = new Point[numPoints];
        for(int i = 0; i < points.length; i++) {
            points[i] = deque.removeFirst();
        }

        return points;
    }

    public static Point[] getTest(String testName) {
        File[] files = FileTester.getAllTests();
        for (File file : files) {
            if (file.getName().toString().equalsIgnoreCase(testName)) {
                return readFile(file);
            }
        }
        return null;
    }

    public static File[] getAllTests() {

        File f = new File("files");

        FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");

        return f.listFiles(textFilter);
    }


    @Test
    public void testPointInitialization() {
        File[] files = getAllTests();
        for (File file : files) {
            Point[] test = getTest(file.getName().toString());
            Assert.assertTrue(test != null);
        }

    }
}
