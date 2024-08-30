import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CyclistTest {

    @Test
    void testCyclistCreation() {
        Cyclist cyclist = new Cyclist("John Doe", "Team A");
        assertEquals("John Doe", cyclist.getName());
        assertEquals("Team A", cyclist.getTeam());
    }

    @Test
    void testCyclistToString() {
        Cyclist cyclist = new Cyclist("Jane Smith", "Team B");
        assertEquals("Cyclist{name='Jane Smith', team='Team B'}", cyclist.toString());
    }
}
