import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class BaseballElimination {

    private int numTeams;
    private ST<String, Integer> index;

    private int wins[];
    private int losses[];
    private int remaining[];

    private int against[][];

    /**
     * create a baseball division from given filename in format specified below
     *
     * @param filename
     */
    public BaseballElimination(String filename) {

        In in = new In(filename);

        numTeams = in.readInt();
        wins = new int[numTeams];
        losses = new int[numTeams];
        remaining = new int[numTeams];

        against = new int[numTeams][numTeams];

        // symbol table for lookup of team index
        index = new ST<>();

        // read in the standings
        for (int i = 0; i < numTeams; i++) {
            String team = in.readString();
            index.put(team, i);
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < numTeams; j++) {
                against[i][j] = in.readInt();
            }
        }
    }

    /**
     * number of teams
     *
     * @return
     */
    public int numberOfTeams() {
        return numTeams;
    }

    /**
     * all teams
     *
     * @return
     */
    public Iterable<String> teams() {
        return index.keys();
    }

    /**
     * number of wins for given team
     *
     * @param team
     * @return
     */
    public int wins(String team) {
        if (!index.contains(team)) throw new IllegalArgumentException("Invalid team");

        int idx = index.get(team);

        return wins[idx];
    }

    /**
     * number of losses for given team
     *
     * @param team
     * @return
     */
    public int losses(String team) {
        if (!index.contains(team)) throw new IllegalArgumentException("Invalid team");

        int idx = index.get(team);

        return losses[idx];
    }

    /**
     * number of remaining games for given team
     *
     * @param team
     * @return
     */
    public int remaining(String team) {
        if (!index.contains(team)) throw new IllegalArgumentException("Invalid team");

        int idx = index.get(team);

        return remaining[idx];
    }

    /**
     * number of remaining games between team1 and team2
     *
     * @param team1
     * @param team2
     * @return
     */
    public int against(String team1, String team2) {
        if (!index.contains(team1) || !index.contains(team2)) throw new IllegalArgumentException("Invalid team");

        int idx1 = index.get(team1);
        int idx2 = index.get(team2);

        return against[idx1][idx2];
    }

    /**
     * is given team eliminated?
     *
     * @param team
     * @return
     */
    public boolean isEliminated(String team) {
        // TODO: figure out maxflow problem here
        return false;
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }
}
