import java.io.*;
import java.util.StringTokenizer;

public class TestReader {

    /**
     * Client for testing that all files can be properly read
     * @param args
     */
    public static void main(String[] args) {

        File test = new File("testfiles");

        File[] files = test.listFiles();
        for (File file : files) {
            if (accept(file)) {
                readFile(file);
            }
        }
    }

    private static boolean accept(File file) {
        return file.getName().endsWith(".txt");
    }

    public static int[][] readFile(File file) {

        int[][] tiles;

        System.out.println("In File: " + file.getName());

        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            int n;

            line = bufferedReader.readLine();
            n = Integer.parseInt(line);
            tiles = new int[n][n];

            int i = 0;
            while (i < n) {
                line = bufferedReader.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");

                assert st.countTokens() == n;

                for (int j = 0; j < n; j++) {
                    tiles[i][j] = Integer.parseInt(st.nextToken());
                }
                i++;
            }
            return tiles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[][] getTest(String name) {

        File f = new File("testfiles");
        File testfile = new File(f,name);

        try {
            return readFile(testfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File[] getAllFiles() {

        File testDirectory = new File("testfiles");

        FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");

        File[] files = testDirectory.listFiles(textFilter);

        return files;
    }
}
