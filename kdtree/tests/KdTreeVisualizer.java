/******************************************************************************
 *  Compilation:  javac KdTreeVisualizer.java
 *  Execution:    java KdTreeVisualizer
 *  Dependencies: KdTree.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeVisualizer {

    public static void main(String[] args) {

        KdTree kdTree = TestFileReader.parseKdTree("input10.txt");

        // draw the points
        StdDraw.clear();
        StdDraw.setScale(-.05, 1.05);
        StdDraw.setPenRadius(0.0025);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(0.5,0.5,0.5);

        kdTree.draw();
        StdDraw.show();
    }
}
