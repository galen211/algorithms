import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {

    @Test
    public void initialize(){

        Percolation perc = new Percolation(10);

        perc.open(1,1);
        perc.open(2,1);
        perc.open(3,1);
        perc.open(4,1);
        perc.open(5,1);
        perc.open(6,1);
        perc.open(7,1);
        perc.open(8,1);
        perc.open(9,1);
        perc.open(10,1);

        assertTrue(perc.percolates());
    }
}