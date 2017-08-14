import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {

    @Test
    public void testSlope() {
    }

    @Test
    public void testAllInputs() {
        File[] files = FileTester.getAllTests();
        for (File file : files) {
            Point[] points = FileTester.readFile(file);

            BruteCollinearPoints brute = null;
            try {
                brute = new BruteCollinearPoints(points);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            LineSegment[] lineSegments = brute.segments();

            StdOut.println("Line Segments: " + lineSegments.length);
            for (int i = 0; i < lineSegments.length; i++) {
                StdOut.println(lineSegments[i]);
            }
        }
        StdOut.println("-------------------------");
    }

    public void bruteCollinearTest(Point[] points) {
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] segments = collinear.segments();

        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

    @Test
    public void testImmutable() {

        Point[] points = FileTester.getTest("input8.txt");

        BruteCollinearPoints baseCollinear = new BruteCollinearPoints(points);
        LineSegment[] baseSegments = baseCollinear.segments();
        int baseNumSegments = baseCollinear.numberOfSegments();

        BruteCollinearPoints collinear;
        LineSegment[] segments;
        int numSegments;

        int trials = 100;
        for (int i = 0; i < trials; i++) {
            StdRandom.shuffle(points);
            collinear = new BruteCollinearPoints(points);
            segments = collinear.segments();
            numSegments = collinear.numberOfSegments();

            assertArrayEquals(baseSegments,segments);
            assertEquals(baseNumSegments, numSegments);
        }
    }

    @Test
    public void testInput8() {

        Point[] points = FileTester.getTest("input8.txt");

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] segments = collinear.segments();

        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

    @Test
    public void testInput40() {

        File file = new File("files/input40.txt");
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
                int x = 0; int y = 0;
                while (st.hasMoreTokens()){
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                }
                deque.addLast(new Point(x,y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert numPoints == deque.size();

        Point[] points = new Point[numPoints];
        for(int i = 0; i < points.length; i++) {
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

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] segments = collinear.segments();
        for (LineSegment segment : segments) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        for (Point point : deque) {
            StdOut.println(point.toString());
        }
    }

    @Test
    public void testEquidistant() {

        File file = new File("files/equidistant.txt");
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int numPoints = 0;

        StdOut.println("In File: " + file.toString());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            line = reader.readLine();
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

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        LineSegment[] segments = collinear.segments();
        for (LineSegment segment : segments) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        for (Point point : deque) {
            StdOut.println(point.toString());
        }
    }
}