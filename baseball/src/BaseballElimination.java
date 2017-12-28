import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {

    private final int numTeams;

    private ST<String, Integer> index;
    private String[] teams;
    private int topTeam;

    private final int wins[];
    private final int losses[];
    private final int remaining[];
    private final int against[][];

    private int numVertices;
    private SET<String> subset;

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

        teams = new String[numTeams];

        // symbol table for lookup of team index
        index = new ST<>();

        // read in the standings
        topTeam = -1;
        int maxWins = -1;
        for (int i = 0; i < numTeams; i++) {
            String team = in.readString();
            teams[i] = team;
            index.put(team, i);
            wins[i] = in.readInt();
            if (wins[i] > maxWins) {
                maxWins = wins[i];
                topTeam = i;
            }
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
        isValidTeam(team);

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
        isValidTeam(team);

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
        isValidTeam(team);

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
        isValidTeam(team1);
        isValidTeam(team2);

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
        isValidTeam(team);

        int idx = index.get(team);
        if (isTriviallyEliminated(idx)) {
            subset = new SET<>();
            subset.add(teams[topTeam]);
            return true;
        }

        FlowNetwork network = createFlowNetwork(idx);

        for (FlowEdge edge : network.adj(0)) {
            if (edge.residualCapacityTo(edge.to()) != 0) {
                return true;
            }
        }

        return false;
    }

    // create FlowNetwork for the team with index idx
    private FlowNetwork createFlowNetwork(int idx) {

        // n choose 2 (excluding the team with index idx)
        int numGames = 0;
        for (int i = 0; i < numTeams; i++) {
            if (i == idx) continue;
            for (int j = (i + 1); j < numTeams; j++) {
                if (j == idx) continue;
                numGames++;
            }
        }

        // set source vertex and sink vertex
        numVertices = numGames + (numTeams - 1) + 2;
        int source = 0;
        int sink = numVertices - 1;

        // set team vertices by index
        ST<Integer, Integer> vertex = new ST<>(); // ST<Team Number, Vertex>
        int c = 1 + numGames; // beginning index of team vertices
        for (int i = 0; i < numTeams; i++) {
            if (i == idx) continue;
            vertex.put(i, c++);
        }

        // initialize flow network with vertices
        FlowNetwork network = new FlowNetwork(numVertices);

        FlowEdge gameEdge;  // source -> game edges
        FlowEdge r1;        // result 1 -> team
        FlowEdge r2;        // result 2 -> team
        FlowEdge teamEdge;  // team vertices -> sink edge

        c = 1;              // current index of the game vertices
        for (int i = 0; i < numTeams; i++) {
            if (i == idx) continue;
            for (int j = i + 1; j < numTeams; j++) {
                if (j == idx) continue;

                // gameEdge between teams i and j
                gameEdge = new FlowEdge(source, c, against[i][j]);
                network.addEdge(gameEdge);

                // assign possible results to network
                int w = vertex.get(i); // index of team i vertex
                int y = vertex.get(j); // index of team j vertex

                r1 = new FlowEdge(c, w, Double.POSITIVE_INFINITY);
                network.addEdge(r1);

                r2 = new FlowEdge(c, y, Double.POSITIVE_INFINITY);
                network.addEdge(r2);

                c++; // increment game pair counter
            }
        }

        for (Integer j : vertex.keys()) {
            int w = vertex.get(j);
            teamEdge = new FlowEdge(w, sink, (wins[idx] + remaining[idx] - wins[j]));
            network.addEdge(teamEdge);
        }

        FordFulkerson ff = new FordFulkerson(network, 0, numVertices - 1);

        subset = new SET<>();
        for (Integer t : vertex.keys()) {
            int v = vertex.get(t);
            if (ff.inCut(v)) {
                subset.add(teams[t]);
            }
        }
        return network;
    }

    private boolean isTriviallyEliminated(int idx) {
        return (wins[idx] + remaining[idx]) < wins[topTeam];
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team) {
        if (!isEliminated(team)) return null;

        return subset;
    }

    private void isValidTeam(String team) {
        if (!index.contains(team)) throw new IllegalArgumentException("Invalid team");
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);

        for (int i = 0; i < division.numberOfTeams(); i++) {
            if (division.isTriviallyEliminated(i)) continue;
            StdOut.println("Team " + division.teams[i]);
            FlowNetwork network = division.createFlowNetwork(i);
            StdOut.println(network.toString());
            StdOut.println("-------------");
        }

        StdOut.println("---- Certificate of Elimination ----");

        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
