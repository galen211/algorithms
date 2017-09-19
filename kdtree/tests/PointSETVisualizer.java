import edu.princeton.cs.algs4.StdDraw;
import org.junit.Test;

public class PointSETVisualizer {


    public static void main(String[] args) {
        drawInput("input10.txt");
    }

    public static void drawInput(String testName) {

        PointSET pointSET = TestFileReader.parsePointSET(testName);

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        pointSET.draw();
        StdDraw.show();
    }
}
