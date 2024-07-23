package edu.upenn.cit594.studenttests.datamanagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.upenn.cit594.studenttests.logging.Logger;
import edu.upenn.cit594.studenttests.util.Tweet;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TweetFileReader {
    private String filename;
    private Logger logger;

    public TweetFileReader(String filename, Logger logger) {
        this.filename = filename;
        this.logger = logger;
    }

    public List<Tweet> readTweets() {
        List<Tweet> tweets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            if (filename.endsWith(".json")) {
                // Read the entire JSON file content
                StringBuilder jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }

                // Convert JSON content to a list of Tweet objects
                Type listType = new TypeToken<List<Tweet>>() {}.getType();
                tweets = new Gson().fromJson(jsonContent.toString(), listType);
            } else if (filename.endsWith(".txt")) {
                // Read tab-separated values from the text file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length >= 4) {
                        try {
                            double latitude = Double.parseDouble(parts[0]);
                            double longitude = Double.parseDouble(parts[1]);
                            String time = parts[2];
                            String text = parts[3];
                            double[] location = {latitude, longitude};
                            tweets.add(new Tweet(location, time, text));
                        } catch (NumberFormatException e) {
                            logger.log("Error parsing tweet line: " + line);
                        }
                    } else {
                        logger.log("Unexpected line format: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweets;
    }
}
