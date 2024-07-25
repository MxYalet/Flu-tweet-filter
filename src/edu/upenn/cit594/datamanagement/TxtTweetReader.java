package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtTweetReader {
    private Logger logger;

    public TxtTweetReader(Logger logger) {
        this.logger = logger;
    }

    public List<Tweet> readTweets(BufferedReader reader) throws IOException {
        List<Tweet> tweets = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\t");
            if (data.length >= 4) {
                try {
                    String[] latLong = data[0].replace("[", "").replace("]", "").split(",");
                    double latitude = Double.parseDouble(latLong[0].trim());
                    double longitude = Double.parseDouble(latLong[1].trim());
                    String time = data[2].trim();
                    String text = data[3].trim();
                    double[] location = {latitude, longitude};
                    tweets.add(new Tweet(location, time, text));
                } catch (NumberFormatException e) {
                    logger.log("Error parsing tweet line: " + line);
                }
            } else {
                logger.log("Unexpected line format: " + line);
            }
        }
        return tweets;
    }
}
