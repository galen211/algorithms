import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class FastCollinearPointsTest {

    @Test
    public void testAllInput() {
        File file = new File("files/horizontal100.txt");
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int numPoints = 0;

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            line = reader.readLine();
            numPoints = Integer.parseInt(line);
            for (int i = 0; i < numPoints; i++) {
                line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int x = 0;
                int y = 0;
                while (st.hasMoreTokens()) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                }
                deque.addLast(new Point(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert numPoints == deque.size();

        Point[] points = new Point[numPoints];
        for (int i = 0; i < points.length; i++) {
            points[i] = deque.removeFirst();
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        collinear.segments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        StdDraw.pause(1000 * 5);

        for (Point point : deque) {
            StdOut.println(point.toString());
        }
    }

    @Test
    public void testInput8() {

        Point[] points = FileTester.getTest("input8.txt");

        FastCollinearPoints fc = new FastCollinearPoints(points);

        LineSegment[] lineSegments = fc.segments();

        assert lineSegments.length == fc.numberOfSegments();
    }


    @Test
    public void testInput_400() {

        File file = new File("files/input400.txt");
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int numPoints = 0;

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            line = reader.readLine();
            numPoints = Integer.parseInt(line);
            for (int i = 0; i < numPoints; i++) {
                line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int x = 0;
                int y = 0;
                while (st.hasMoreTokens()) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                }
                deque.addLast(new Point(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert numPoints == deque.size();

        Point[] points = new Point[numPoints];
        for (int i = 0; i < points.length; i++) {
            points[i] = deque.removeFirst();
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        collinear.segments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        for (Point point : deque) {
            StdOut.println(point.toString());
        }
    }

    @Test
    public void testInput_horizontal_100() {

        File file = new File("files/horizontal100.txt");
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int numPoints = 0;

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            line = reader.readLine();
            numPoints = Integer.parseInt(line);
            for (int i = 0; i < numPoints; i++) {
                line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int x = 0;
                int y = 0;
                while (st.hasMoreTokens()) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                }
                deque.addLast(new Point(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert numPoints == deque.size();

        Point[] points = new Point[numPoints];
        for (int i = 0; i < points.length; i++) {
            points[i] = deque.removeFirst();
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        collinear.segments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        StdDraw.pause(1000 * 5);

        for (Point point : deque) {
            StdOut.println(point.toString());
        }
    }
}
