package edu.upenn.cit594.datamanagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;

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
                readJsonTweets(reader, tweets);
            } else if (filename.endsWith(".txt")) {
                TxtTweetReader txtReader = new TxtTweetReader(logger);
                tweets = txtReader.readTweets(reader);            } else {
                logger.log("Unsupported file format: " + filename);
            }
        } catch (IOException e) {
            logger.log("Error reading file: " + e.getMessage());
        }
        return tweets;
    }

    private void readJsonTweets(BufferedReader reader, List<Tweet> tweets) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        Type listType = new TypeToken<List<Tweet>>() {}.getType();
        List<Tweet> jsonTweets = new Gson().fromJson(jsonContent.toString(), listType);
        if (jsonTweets != null) {
            tweets.addAll(jsonTweets);
        }
    }

    private void readTxtTweets(BufferedReader reader, List<Tweet> tweets) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\t");
            if (data.length >= 4) {
                try {
                    double latitude = Double.parseDouble(data[0].trim());
                    double longitude = Double.parseDouble(data[1].trim());
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
    }}
