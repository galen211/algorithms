import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Class for running t trials on an n x n Percolation grid
 */
public class PercolationStats {

    private final double[] threshold;
    private Percolation[] percolations;
    private final int size;
    private final int trials;
    private double mean;
    private double stddev;

    /**
     * Public constructor that initializes the percolation objects
     *
     * @param n size of the percolation grid
     * @param t number of trials to run
     */
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException();

        size = n;
        trials = t;
        threshold = new double[trials];
        Percolation[] percolations = new Percolation[t];

        int counter = 0;
        for (Percolation percolation : percolations) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, size + 1); // since uniform is [a,b)
                int col = StdRandom.uniform(1, size + 1);
                if (!percolation.isOpen(row, col))
                    percolation.open(row, col);
            }
            threshold[counter++] = (double) percolation.numberOfOpenSites() / (size * size);
            mean = StdStats.mean(threshold);
            stddev = (trials == 1) ? Double.NaN : StdStats.stddev(threshold);
        }
    }

    /**
     * Private method used to print results of the trial
     */
    private void printStats() {
        StdOut.println("mean                    = " + mean);
        StdOut.println("stddev                  = " + stddev);
        StdOut.println("95% confidence interval = [" + confidenceLo() + " " + confidenceHi() + "]");
    }

    /**
     * Computes the mean of the percolation thresholds for an experiment over t trials
     *
     * @return the mean threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * Computes the standard deviation of the percolation thresholds for an experiment over t trials
     *
     * @return the standard deviation of the experiment
     */
    public double stddev() {
        return stddev;
    }

    /**
     * Computes the lower bound of the 95% confidence interval of the experiment
     *
     * @return the lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev / Math.sqrt(trials));
    }

    /**
     * Computes the upper bound of the 95% confidence interval of the experiment
     *
     * @return the upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev / Math.sqrt(trials));
    }

    /**
     * Main method for running the experiment
     *
     * @param args should be of form \code{java PercolationStats 200 100}
     */
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);
        ps.printStats();
    }
}
