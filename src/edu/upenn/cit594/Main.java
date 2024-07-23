package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.StateFileReader;
import edu.upenn.cit594.datamanagement.TweetFileReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.TweetProcessor;
import edu.upenn.cit594.ui.Output;
import edu.upenn.cit594.util.Tweet;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String tweetsFile = "flu_tweets.json";
        String statesFile = "states.csv";
        String logFile = "log.txt";

        // Initialize Logger
        Logger logger = Logger.getInstance(logFile);

        // Log the filenames
        logger.log("Tweets file: " + tweetsFile);
        logger.log("States file: " + statesFile);
        logger.log("Log file: " + logFile);

        // Read data
        TweetFileReader tweetReader = new TweetFileReader(tweetsFile, logger);
        List<Tweet> tweets = tweetReader.readTweets();

        System.out.println("Number of tweets read: " + tweets.size()); // Debugging statement

        StateFileReader stateReader = new StateFileReader(statesFile);
        Map<String, String> states = stateReader.readStates();

        System.out.println("Number of states read: " + states.size()); // Debugging statement

        // Process data
        TweetProcessor processor = new TweetProcessor(tweets, states);
        Map<String, Integer> tweetCounts = processor.countTweetsByState();

        System.out.println("Number of states with tweets: " + tweetCounts.size()); // Debugging statement

        // Output data
        Output output = new Output();
        output.displayTweetCounts(tweetCounts);
    }
}
