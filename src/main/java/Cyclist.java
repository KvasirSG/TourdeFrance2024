public class Cyclist {
    private String name; // Rytter
    private String team; // Hold

    // Constructor
    public Cyclist(String name, String team) {
        this.name = name;
        this.team = team;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    // ToString method for easy representation
    @Override
    public String toString() {
        return "Cyclist{name='" + name + "', team='" + team + "'}";
    }
}
