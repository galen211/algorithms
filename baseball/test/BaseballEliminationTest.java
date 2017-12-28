import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class BaseballEliminationTest {

    ST<String, BaseballElimination> baseballST;

    @Before
    public void setupBaseballElimination() {

        Stack<File> files = TestFileReader.getFilesBeginWith("teams");

        baseballST = new ST<>();
        while (!files.isEmpty()) {
            File f = files.pop();
            String name = f.getName();
            String[] key = name.split("[.]");
            BaseballElimination be = new BaseballElimination(name);
            baseballST.put(key[0], be);
        }
    }

    @Test
    public void correctlyPrintTeam5() {

        BaseballElimination be;
        int numTeams;

        be = baseballST.get("teams5");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(5, numTeams);

        be.isEliminated("Detroit");
    }

    @Test
    public void checkNumberOfTeams() {

        BaseballElimination be;
        int numTeams;

        be = baseballST.get("teams4");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(4, numTeams);

        be = baseballST.get("teams5");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(5, numTeams);

        be = baseballST.get("teams8");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(8, numTeams);

        be = baseballST.get("teams10");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(10, numTeams);

        be = baseballST.get("teams29");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(29, numTeams);

        be = baseballST.get("teams48");
        numTeams = be.numberOfTeams();
        Assert.assertEquals(48, numTeams);
    }

    @Test
    public void checkTeams() {
        BaseballElimination be;
        int numTeams;
        boolean b;
        Iterable<String> teams;

        be = baseballST.get("teams4");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Philadelphia")) b = true;
        }
        Assert.assertTrue(b);

        be = baseballST.get("teams5");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Toronto")) b = true;
        }
        Assert.assertTrue(b);

        be = baseballST.get("teams8");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Yale")) b = true;
        }
        Assert.assertTrue(b);

        be = baseballST.get("teams10");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Indiana")) b = true;
        }
        Assert.assertTrue(b);

        be = baseballST.get("teams29");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Washington")) b = true;
        }
        Assert.assertTrue(b);

        be = baseballST.get("teams48");
        teams = be.teams();
        b = false;
        for (String team : teams) {
            if (team.equals("Team9")) b = true;
        }
        Assert.assertTrue(b);
    }

    @Test
    public void checkWins() {
        BaseballElimination be;
        String team;
        int wins;

        be = baseballST.get("teams4");
        team = "Atlanta";
        wins = be.wins(team);
        Assert.assertEquals(83, wins);

        be = baseballST.get("teams5");
        team = "New_York";
        wins = be.wins(team);
        Assert.assertEquals(75, wins);

        be = baseballST.get("teams8");
        team = "Brown";
        wins = be.wins(team);
        Assert.assertEquals(44, wins);

        be = baseballST.get("teams10");
        team = "Atlanta";
        wins = be.wins(team);
        Assert.assertEquals(0, wins);

        be = baseballST.get("teams29");
        team = "Atlanta";
        wins = be.wins(team);
        Assert.assertEquals(123, wins);

        be = baseballST.get("teams48");
        team = "Team0";
        wins = be.wins(team);
        Assert.assertEquals(29, wins);
    }

    @Test
    public void checkLosses() {
        BaseballElimination be;
        String team;
        int losses;

        be = baseballST.get("teams4");
        team = "Atlanta";
        losses = be.losses(team);
        Assert.assertEquals(71, losses);

        be = baseballST.get("teams5");
        team = "New_York";
        losses = be.losses(team);
        Assert.assertEquals(59, losses);

        be = baseballST.get("teams8");
        team = "Brown";
        losses = be.losses(team);
        Assert.assertEquals(51, losses);

        be = baseballST.get("teams10");
        team = "Atlanta";
        losses = be.losses(team);
        Assert.assertEquals(0, losses);

        be = baseballST.get("teams29");
        team = "Atlanta";
        losses = be.losses(team);
        Assert.assertEquals(76, losses);

        be = baseballST.get("teams48");
        team = "Team0";
        losses = be.losses(team);
        Assert.assertEquals(22, losses);
    }

    @Test
    public void checkRemaining() {
        BaseballElimination be;
        String team;
        int remaining;

        be = baseballST.get("teams4");
        team = "Atlanta";
        remaining = be.remaining(team);
        Assert.assertEquals(8, remaining);

        be = baseballST.get("teams5");
        team = "New_York";
        remaining = be.remaining(team);
        Assert.assertEquals(28, remaining);

        be = baseballST.get("teams8");
        team = "Brown";
        remaining = be.remaining(team);
        Assert.assertEquals(9, remaining);

        be = baseballST.get("teams10");
        team = "Atlanta";
        remaining = be.remaining(team);
        Assert.assertEquals(63, remaining);

        be = baseballST.get("teams29");
        team = "Atlanta";
        remaining = be.remaining(team);
        Assert.assertEquals(53, remaining);

        be = baseballST.get("teams48");
        team = "Team0";
        remaining = be.remaining(team);
        Assert.assertEquals(15, remaining);
    }

    @Test
    public void checkAgainst() {
        BaseballElimination be;
        String teamA;
        String teamB;
        int remaining;

        be = baseballST.get("teams4");
        teamA = "Atlanta";
        teamB = "Atlanta";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);

        be = baseballST.get("teams5");
        teamA = "New_York";
        teamB = "New_York";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);

        be = baseballST.get("teams8");
        teamA = "Brown";
        teamB = "Brown";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);

        be = baseballST.get("teams10");
        teamA = "Atlanta";
        teamB = "Atlanta";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);

        be = baseballST.get("teams29");
        teamA = "Atlanta";
        teamB = "Atlanta";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);

        be = baseballST.get("teams48");
        teamA = "Team0";
        teamB = "Team0";
        remaining = be.against(teamA, teamB);
        Assert.assertEquals(0, remaining);
    }

    @Test
    public void checkIsEliminated() {
        BaseballElimination be;
        String team;
        boolean eliminated;

        be = baseballST.get("teams4");
        team = "Philadelphia";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams4a");
        team = "Ghaddafi";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams4b");
        team = "Hufflepuff";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams5");
        team = "Detroit";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams5a");
        team = "Detroit";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams5b");
        team = "Detroit";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams5c");
        team = "Philadelphia";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams7");
        team = "Ireland";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams8");
        team = "Harvard";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams10");
        team = "Houston";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams12");
        team = "Japan";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams24");
        team = "Team4";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams29");
        team = "Detroit";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams30");
        team = "Team0";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams32");
        team = "Team2";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams36");
        team = "Team17";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams42");
        team = "Team6";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams48");
        team = "Team4";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);

        be = baseballST.get("teams12-allgames");
        team = "Team0";
        eliminated = be.isEliminated(team);
        Assert.assertEquals(true, eliminated);
    }

    @Test
    public void checkCertificateOfElimination() {
        BaseballElimination be;
        String team;
        Iterable<String> subset;
        SET<String> set;

        // teams4
        be = baseballST.get("teams4");
        team = "Philadelphia";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("Atlanta"));
        Assert.assertTrue(set.contains("New_York"));
        Assert.assertTrue(set.size() == 2);

        // teams4a
        be = baseballST.get("teams4a");
        team = "Ghaddafi";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("CIA"));
        Assert.assertTrue(set.contains("Obama"));
        Assert.assertTrue(set.size() == 2);

        // teams4b
        be = baseballST.get("teams4b");
        team = "Hufflepuff";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("Gryffindor"));
        Assert.assertTrue(set.size() == 1);

        // teams7
        be = baseballST.get("teams7");
        team = "China";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("France"));
        Assert.assertTrue(set.size() == 1);

        // teams8
        be = baseballST.get("teams8");
        team = "Harvard";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("Brown"));
        Assert.assertTrue(set.contains("Columbia"));
        Assert.assertTrue(set.contains("Cornell"));
        Assert.assertTrue(set.contains("Dartmouth"));
        Assert.assertTrue(set.contains("Penn"));
        Assert.assertTrue(set.contains("Princeton"));
        Assert.assertTrue(set.size() == 6);

        // teams12
        be = baseballST.get("teams12");
        team = "Japan";
        subset = be.certificateOfElimination(team);
        set = new SET<>();
        for (String s : subset) {
            set.add(s);
        }
        Assert.assertTrue(set.contains("Poland"));
        Assert.assertTrue(set.contains("Russia"));
        Assert.assertTrue(set.contains("Brazil"));
        Assert.assertTrue(set.contains("Iran"));
        Assert.assertTrue(set.size() == 4);

    }

    // TODO: Implement checkCertificateOfEliminationReturnsNull test
    @Test
    public void checkCertificateOfEliminationReturnsNull() {

        BaseballElimination be = baseballST.get("teams4");
        Iterable<String> eliminated;
    }

    @Test
    public void checkOneTeamInDivisoin() {

        BaseballElimination be = baseballST.get("teams1");
        int teams;
        int wins;
        int losses;
        int remaining;
        int against;

        teams = be.numberOfTeams();
        Assert.assertEquals(1, teams);

        wins = be.wins("Turing");
        Assert.assertEquals(100, wins);

        losses = be.losses("Turing");
        Assert.assertEquals(55, losses);

        remaining = be.remaining("Turing");
        Assert.assertEquals(0, remaining);

        against = be.against("Turing", "Turing");
        Assert.assertEquals(0, against);
    }

    // TODO: Implement checkImmutabilityOfCertificateOfElimination test
    @Test
    public void checkImmutabilityOfCertificateOfElimination() {

    }

    @Test
    public void checkInvalidArguments() {
        BaseballElimination be = baseballST.get("teams4");

        try {
            be.wins("Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.losses("Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.remaining("Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.isEliminated("Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.certificateOfElimination("Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.against("Princeton", "Tigers");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.against("Princeton", "New_York");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }

        try {
            be.against("Princeton", "Princeton");
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) Assert.fail();
        }
    }

    @Test
    public void checkTwoBaseballEliminationObjects(){

    }
}