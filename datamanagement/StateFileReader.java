package edu.upenn.cit594.studenttests.datamanagement;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StateFileReader {
    private String filename;

    public StateFileReader(String filename) {
        this.filename = filename;
    }

    public Map<String, String> readStates() {
        Map<String, String> states = new HashMap<>();
        Path path = Paths.get(filename);

        try (FileReader reader = new FileReader(path.toFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                // Read state name, latitude, and longitude from the CSV file
                String name = csvRecord.get(0).trim(); // State name
                String latitude = csvRecord.get(1).trim(); // Latitude (not used in this example)
                String longitude = csvRecord.get(2).trim(); // Longitude (not used in this example)

                // Example: use name as key in the map
                states.put(name, latitude + "," + longitude);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return states;
    }
}
