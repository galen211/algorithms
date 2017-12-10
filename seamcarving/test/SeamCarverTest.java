import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SeamCarverTest {

    ST<String, Picture> img;

    @Before
    public void setupSeamCarver() {
        Stack<File> files = TestFileReader.getFilesExtension("png");

        img = new ST<>();
        while (!files.isEmpty()) {
            File f = files.pop();
            Picture pict = new Picture(f);

            String fname = f.getName();
            String[] key = fname.split("[.]");

            img.put(key[0], pict);
        }
    }

    @Test
    public void checkEnergyWithFileInputs() {

        Picture p;
        SeamCarver sc;
        double energy;

        // prelminary energy test from assignment
        p = img.get("3x4");
        sc = new SeamCarver(p);
        energy = sc.energy(1,2);
        Assert.assertEquals(Math.sqrt(52024), energy, 1e-6);
        energy = sc.energy(1,1);
        Assert.assertEquals(Math.sqrt(52225), energy, 1e-6);

        // test energy from section "Finding a vertical seam"
        p = img.get("6x5");
        sc = new SeamCarver(p);
        energy = sc.energy(3, 0);
        Assert.assertEquals(1000.0, energy, 0.01);
        energy = sc.energy(4, 1);
        Assert.assertEquals(107.89, energy, 0.01);
        energy = sc.energy(3, 2);
        Assert.assertEquals(133.07, energy, 0.01);
        energy = sc.energy(2, 3);
        Assert.assertEquals(174.01, energy, 0.01);
        energy = sc.energy(2, 4);
        Assert.assertEquals(1000.0, energy, 0.01);

        // 1a
        p = img.get("6x5");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("4x6");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("10x12");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("3x7");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("5x6");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("7x3");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("7x10");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("12x10");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("stripes");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("diagonals");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("chameleon");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("HJocean");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("1x8");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("8x1");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);

        p = img.get("1x1");
        sc = new SeamCarver(p);
        energy = sc.energy(0, 0);
        Assert.assertEquals(1000.0, energy, 1e-6);
    }

    @Test
    public void checkEnergyWithRandomWxHPictures() {
        // must generate random pictures?
    }

    @Test
    public void checkFindVerticalSeamWithFileInputs() {
        Picture p;
        SeamCarver sc;
        int[] verticalSeam;

        // 4a
        p = img.get("6x5");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(5, verticalSeam.length);

        p = img.get("4x6");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(6, verticalSeam.length);

        p = img.get("10x12");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(12, verticalSeam.length);

        p = img.get("3x7");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(7, verticalSeam.length);

        p = img.get("5x6");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(6, verticalSeam.length);

        p = img.get("7x3");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(3, verticalSeam.length);

        p = img.get("7x10");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(10, verticalSeam.length);

        p = img.get("12x10");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(10, verticalSeam.length);

        p = img.get("stripes");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(12, verticalSeam.length);

        p = img.get("diagonals");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(12, verticalSeam.length);

        p = img.get("chameleon");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(300, verticalSeam.length);

        p = img.get("HJocean");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(432, verticalSeam.length);

        p = img.get("1x8");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(8, verticalSeam.length);

        p = img.get("8x1");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(1, verticalSeam.length);

        p = img.get("1x1");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        Assert.assertEquals(1, verticalSeam.length);
    }

    @Test
    public void checkFindHorizontalSeamWithFileInputs() {
        Picture p;
        SeamCarver sc;
        int[] horizontalSeam;

        // 4a
        p = img.get("6x5");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(6, horizontalSeam.length);

        p = img.get("4x6");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(4, horizontalSeam.length);

        p = img.get("10x12");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(10, horizontalSeam.length);

        p = img.get("3x7");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(3, horizontalSeam.length);

        p = img.get("5x6");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(5, horizontalSeam.length);

        p = img.get("7x3");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(7, horizontalSeam.length);

        p = img.get("7x10");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(7, horizontalSeam.length);

        p = img.get("12x10");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(12, horizontalSeam.length);

        p = img.get("stripes");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(9, horizontalSeam.length);

        p = img.get("diagonals");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(9, horizontalSeam.length);

        p = img.get("chameleon");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(600, horizontalSeam.length);

        p = img.get("1x8");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(1, horizontalSeam.length);

        p = img.get("8x1");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(8, horizontalSeam.length);

        p = img.get("1x1");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        Assert.assertEquals(1, horizontalSeam.length);
    }

    @Test
    public void checkRemoveVerticalSeamWithFileInputs() {
        Picture p;
        SeamCarver sc;
        int[] verticalSeam;
        int w;

        // 6a
        p = img.get("6x5");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(5, w);

        p = img.get("10x12");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(9, w);

        p = img.get("3x7");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(2, w);

        p = img.get("5x6");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(4, w);

        p = img.get("7x3");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(6, w);

        p = img.get("7x10");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(6, w);

        p = img.get("12x10");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);;
        w = sc.width();
        Assert.assertEquals(11, w);

        p = img.get("stripes");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(8, w);

        p = img.get("diagonals");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(8, w);

        p = img.get("chameleon");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(599, w);

        /*p = img.get("HJocean");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(506, w);*/

        p = img.get("8x1");
        sc = new SeamCarver(p);
        verticalSeam = sc.findVerticalSeam();
        sc.removeVerticalSeam(verticalSeam);
        w = sc.width();
        Assert.assertEquals(7, w);
    }

    @Test
    public void checkRemoveHorizonalSeamWithFileInputs() {
        Picture p;
        SeamCarver sc;
        int[] horizontalSeam;
        int h;

        // 6a
        p = img.get("6x5");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(4, h);

        p = img.get("10x12");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(11, h);

        p = img.get("3x7");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(6, h);

        p = img.get("5x6");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(5, h);

        p = img.get("7x3");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(2, h);

        p = img.get("7x10");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(9, h);

        p = img.get("12x10");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(9, h);

        p = img.get("stripes");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(11, h);

        p = img.get("diagonals");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(11, h);

        p = img.get("chameleon");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(299, h);

        p = img.get("1x8");
        sc = new SeamCarver(p);
        horizontalSeam = sc.findHorizontalSeam();
        sc.removeHorizontalSeam(horizontalSeam);
        h = sc.height();
        Assert.assertEquals(7, h);
    }

    // expected errors
    @Test(expected = IllegalArgumentException.class)
    public void energyThrowsExceptionWhenOutOfBounds() {

        Picture p;
        SeamCarver sc;

        p = img.get("6x5");
        sc = new SeamCarver(p);

        sc.energy(-1, 4);
        sc.energy(6, 4);
        sc.energy(5, 5);
        sc.energy(4, -1);
        sc.energy(4, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeVerticalSeamInvalidThrowsException_1() {
        Picture p;
        SeamCarver sc;
        int[] seam;

        p = img.get("10x10");
        seam = new int[]{ 5, 7, 6, 7, 9, 8, 6, 6, 8, 7};
        sc = new SeamCarver(p);
        sc.removeVerticalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeVerticalSeamInvalidThrowsException_2() {
        Picture p;
        SeamCarver sc;
        int[] seam;

        p = img.get("3x7");
        seam = new int[]{ 0, 0, 0, 2, 2, 2, 1 };
        sc = new SeamCarver(p);
        sc.removeVerticalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeHorizontalSeamInvalidThrowsException_1() {
        Picture p;
        SeamCarver sc;
        int[] seam;

        p = img.get("10x10");
        seam = new int[]{ 8, 7, 6, 5, 4, 5, 5, 3, 3, 2};
        sc = new SeamCarver(p);
        sc.removeHorizontalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeHorizontalSeamInvalidThrowsException_2() {
        Picture p;
        SeamCarver sc;
        int[] seam;

        p = img.get("10x12");
        seam = new int[]{ 11, 11, 11, 9, 9, 10, 9, 10, 10, 9 };  // should be length = 12
        sc = new SeamCarver(p);
        sc.removeHorizontalSeam(seam);
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeVerticalSeamNullArgument() {
        Picture p;
        SeamCarver sc;

        p = img.get("6x5");
        sc = new SeamCarver(p);

        sc.removeVerticalSeam(new int[4]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeHorizontalSeamNullArgument() {
        Picture p;
        SeamCarver sc;

        p = img.get("6x5");
        sc = new SeamCarver(p);

        sc.removeHorizontalSeam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructor() {

        SeamCarver sc;

        sc = new SeamCarver(null);
    }

    // TODO: rethink this
    @Test
    public void clientCanMutatePicture() {
    }

}