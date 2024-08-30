import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TDFFilereader {
    private Statistics statistics;
    private Map<Integer, Cyclist> cyclistMap;

    public TDFFilereader(Statistics statistics) {
        this.statistics = statistics;
        this.cyclistMap = new HashMap<>();
    }

    public void readFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length >= 5) {
                    int placement = Integer.parseInt(parts[0]);
                    String name = parts[1].replace(".", " ");
                    String team = parts[2];
                    String completionTime = parts[3];
                    String timeAfterFirst = parts[4].equals("-") ? "N/A" : parts[4];

                    Cyclist cyclist = new Cyclist(name, team);

                    if (completionTime.equals("")) { // Cyclist did not finish
                        statistics.addNonFinisher(cyclist);
                    } else { // Cyclist finished
                        cyclistMap.put(placement, cyclist);
                        statistics.addFinisher(cyclist, completionTime, timeAfterFirst);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Optional: Method to get a map of cyclist placement
    public Map<Integer, Cyclist> getCyclistMap() {
        return cyclistMap;
    }
}
