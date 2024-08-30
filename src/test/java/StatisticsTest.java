import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
    }

    @Test
    void testAddFinisher() {
        Cyclist cyclist = new Cyclist("John Doe", "Team A");
        statistics.addFinisher(cyclist, "00:12:34", "00:01:00");

        // Verify teams
        Set<String> expectedTeams = new HashSet<>();
        expectedTeams.add("Team A");
        assertEquals(expectedTeams, statistics.getTeams());

        // Verify team members
        assertTrue(statistics.getTeamsWithMembers().containsKey("Team A"));
        assertTrue(statistics.getTeamsWithMembers().get("Team A").contains("John Doe"));

        // Verify finish times
        assertEquals("00:12:34", statistics.getFinishTime("John Doe"));
        assertEquals("00:01:00", statistics.getTimeAfterFirst("John Doe"));
    }

    @Test
    void testAddNonFinisher() {
        Cyclist cyclist = new Cyclist("Jane Smith", "Team B");
        statistics.addNonFinisher(cyclist);

        // Verify teams
        Set<String> expectedTeams = new HashSet<>();
        expectedTeams.add("Team B");
        assertEquals(expectedTeams, statistics.getTeams());

        // Verify team members
        assertTrue(statistics.getTeamsWithMembers().containsKey("Team B"));
        assertTrue(statistics.getTeamsWithMembers().get("Team B").contains("Jane Smith"));

        // Verify non-finishers
        assertTrue(statistics.getNonFinishers().contains("Jane Smith"));

        // Verify finish times for non-finishers
        assertEquals("Did not finish", statistics.getFinishTime("Jane Smith"));
        assertEquals("N/A", statistics.getTimeAfterFirst("Jane Smith"));
    }

    @Test
    void testGetTeams() {
        Cyclist cyclist1 = new Cyclist("John Doe", "Team A");
        Cyclist cyclist2 = new Cyclist("Jane Smith", "Team B");
        statistics.addFinisher(cyclist1, "00:12:34", "00:01:00");
        statistics.addNonFinisher(cyclist2);

        Set<String> expectedTeams = new HashSet<>();
        expectedTeams.add("Team A");
        expectedTeams.add("Team B");

        assertEquals(expectedTeams, statistics.getTeams());
    }

    @Test
    void testGetTeamsWithMembers() {
        Cyclist cyclist1 = new Cyclist("John Doe", "Team A");
        Cyclist cyclist2 = new Cyclist("Jane Smith", "Team A");
        statistics.addFinisher(cyclist1, "00:12:34", "00:01:00");
        statistics.addNonFinisher(cyclist2);

        Map<String, Set<String>> teamsWithMembers = statistics.getTeamsWithMembers();
        assertTrue(teamsWithMembers.containsKey("Team A"));
        assertEquals(2, teamsWithMembers.get("Team A").size());
        assertTrue(teamsWithMembers.get("Team A").contains("John Doe"));
        assertTrue(teamsWithMembers.get("Team A").contains("Jane Smith"));
    }

    @Test
    void testGetTeamMembers() {
        Cyclist cyclist1 = new Cyclist("John Doe", "Team A");
        Cyclist cyclist2 = new Cyclist("Jane Smith", "Team A");
        statistics.addFinisher(cyclist1, "00:12:34", "00:01:00");
        statistics.addNonFinisher(cyclist2);

        Set<String> teamAMembers = statistics.getTeamMembers("Team A");

        assertEquals(2, teamAMembers.size());
        assertTrue(teamAMembers.contains("John Doe"));
        assertTrue(teamAMembers.contains("Jane Smith"));

        // Test for a team that doesn't exist
        Set<String> nonExistentTeamMembers = statistics.getTeamMembers("Team C");
        assertTrue(nonExistentTeamMembers.isEmpty());
    }

    @Test
    void testGetFinishTime() {
        Cyclist cyclist = new Cyclist("John Doe", "Team A");
        statistics.addFinisher(cyclist, "00:12:34", "00:01:00");

        assertEquals("00:12:34", statistics.getFinishTime("John Doe"));
        assertEquals("Did not finish", statistics.getFinishTime("Jane Smith")); // Jane Smith does not exist
    }

    @Test
    void testGetTimeAfterFirst() {
        Cyclist cyclist = new Cyclist("John Doe", "Team A");
        statistics.addFinisher(cyclist, "00:12:34", "00:01:00");

        assertEquals("00:01:00", statistics.getTimeAfterFirst("John Doe"));
        assertEquals("N/A", statistics.getTimeAfterFirst("Jane Smith")); // Jane Smith does not exist
    }

    @Test
    void testGetNonFinishers() {
        Cyclist cyclist1 = new Cyclist("John Doe", "Team A");
        Cyclist cyclist2 = new Cyclist("Jane Smith", "Team B");
        statistics.addFinisher(cyclist1, "00:12:34", "00:01:00");
        statistics.addNonFinisher(cyclist2);

        Set<String> nonFinishers = statistics.getNonFinishers();

        assertEquals(1, nonFinishers.size());
        assertTrue(nonFinishers.contains("Jane Smith"));
    }
}
