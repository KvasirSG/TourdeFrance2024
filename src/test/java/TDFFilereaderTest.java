import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TDFFilereaderTest {

    private TDFFilereader tdfFilereader;
    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
        tdfFilereader = new TDFFilereader(statistics);
    }

    @Test
    void testReadFile() {
        // Assuming there's a sample file named "sample_data.txt" with appropriate data format
        String filename = "src/main/resources/tdffinishers2024.csv";
        tdfFilereader.readFile(filename);

        // Validate that the statistics object has been populated correctly
        assertFalse(statistics.getTeams().isEmpty());
        assertFalse(statistics.getTeamsWithMembers().isEmpty());
    }

    @Test
    void testGetCyclistMap() {
        // Assuming there's a sample file named "sample_data.txt" with appropriate data format
        String filename = "src/main/resources/tdffinishers2024.csv";
        tdfFilereader.readFile(filename);

        Map<Integer, Cyclist> cyclistMap = tdfFilereader.getCyclistMap();

        // Validate that the cyclist map is populated correctly
        assertFalse(cyclistMap.isEmpty());
        assertNotNull(cyclistMap.get(1)); // Assuming placement 1 exists in the sample data
    }
}
