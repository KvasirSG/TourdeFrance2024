import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private final Set<String> teams; // Set to store unique teams
    private final Map<String, Set<String>> teamMembers; // Map to store team and their members
    private final Map<String, String> finishTimes; // Map to store cyclist names and their finish times
    private final Map<String, String> timeAfterFirst; // Map to store time after the first finisher
    private final Set<String> nonFinishers; // Set to store names of cyclists who did not finish

    // Constructor
    public Statistics() {
        teams = new HashSet<>();
        teamMembers = new HashMap<>();
        finishTimes = new HashMap<>();
        timeAfterFirst = new HashMap<>();
        nonFinishers = new HashSet<>();
    }

    // Method to add a cyclist who finished the race
    public void addFinisher(Cyclist cyclist, String completionTime, String timeAfterFirst) {
        String team = cyclist.getTeam();
        String name = cyclist.getName();

        // Add the team to the set of teams
        teams.add(team);

        // Add the cyclist's name to the corresponding team
        teamMembers.putIfAbsent(team, new HashSet<>()); // Initialize set if absent
        teamMembers.get(team).add(name);

        // Record the finish time and time after first
        finishTimes.put(name, completionTime);
        this.timeAfterFirst.put(name, timeAfterFirst);
    }

    // Method to add a cyclist who did not finish the race
    public void addNonFinisher(Cyclist cyclist) {
        String team = cyclist.getTeam();
        String name = cyclist.getName();

        // Add the team to the set of teams
        teams.add(team);

        // Add the cyclist's name to the corresponding team
        teamMembers.putIfAbsent(team, new HashSet<>()); // Initialize set if absent
        teamMembers.get(team).add(name);

        // Record the cyclist as a non-finisher
        nonFinishers.add(name);
    }

    // Method to get a set of all teams
    public Set<String> getTeams() {
        return teams; // Returns the set of teams
    }

    // Method to get a map of all teams with their members
    public Map<String, Set<String>> getTeamsWithMembers() {
        return teamMembers; // Returns the map of teams with their members
    }

    // Method to get the members of a specific team
    public Set<String> getTeamMembers(String team) {
        return teamMembers.getOrDefault(team, new HashSet<>()); // Returns members or empty set
    }

    // Method to get finish time of a specific cyclist
    public String getFinishTime(String name) {
        return finishTimes.getOrDefault(name, "Did not finish");
    }

    // Method to get time after the first finisher of a specific cyclist
    public String getTimeAfterFirst(String name) {
        return timeAfterFirst.getOrDefault(name, "N/A");
    }

    // Method to get all cyclists who did not finish
    public Set<String> getNonFinishers() {
        return nonFinishers;
    }
}
