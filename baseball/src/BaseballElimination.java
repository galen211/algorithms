public class BaseballElimination {

    /**
     * create a baseball division from given filename in format specified below
     *
     * @param filename
     */
    public BaseballElimination(String filename) {

    }

    /**
     * number of teams
     *
     * @return
     */
    public int numberOfTeams() {
        return -1;
    }

    /**
     * all teams
     *
     * @return
     */
    public Iterable<String> teams() {
        return null;
    }

    /**
     * number of wins for given team
     *
     * @param team
     * @return
     */
    public int wins(String team) {
        return -1;
    }

    /**
     * number of losses for given team
     *
     * @param team
     * @return
     */
    public int losses(String team) {
        return -1;
    }

    /**
     * number of remaining games for given team
     *
     * @param team
     * @return
     */
    public int remaining(String team) {
        return -1;
    }

    /**
     * number of remaining games between team1 and team2
     *
     * @param team1
     * @param team2
     * @return
     */
    public int against(String team1, String team2) {
        return -1;
    }

    /**
     * is given team eliminated?
     *
     * @param team
     * @return
     */
    public boolean isEliminated(String team) {
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
