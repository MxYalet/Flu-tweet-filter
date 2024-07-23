package edu.upenn.cit594.studenttests.processor;

import edu.upenn.cit594.studenttests.util.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetProcessor {
    private List<Tweet> tweets;
    private Map<String, String> states; // Map of state names to "latitude,longitude"

    public TweetProcessor(List<Tweet> tweets, Map<String, String> states) {
        this.tweets = tweets;
        this.states = states;
    }

    public Map<String, Integer> countTweetsByState() {
        Map<String, Integer> tweetCounts = new HashMap<>();

        for (Tweet tweet : tweets) {
            if (tweet.getText().toLowerCase().contains("flu")) {
                String state = findClosestState(tweet.getLocation());
                if (state != null) {
                    tweetCounts.put(state, tweetCounts.getOrDefault(state, 0) + 1);
                }
            }
        }

        return tweetCounts;
    }

    private String findClosestState(double[] tweetLocation) {
        if (tweetLocation.length != 2) {
            return null;
        }
        double tweetLatitude = tweetLocation[0];
        double tweetLongitude = tweetLocation[1];

        double minDistance = Double.MAX_VALUE;
        String closestState = null;

        for (Map.Entry<String, String> entry : states.entrySet()) {
            String[] stateParts = entry.getValue().split(",");
            if (stateParts.length != 2) {
                continue;
            }
            double stateLatitude = Double.parseDouble(stateParts[0]);
            double stateLongitude = Double.parseDouble(stateParts[1]);

            double distance = calculateCartesianDistance(tweetLatitude, tweetLongitude, stateLatitude, stateLongitude);
            if (distance < minDistance) {
                minDistance = distance;
                closestState = entry.getKey();
            }
        }
        return closestState;
    }

    private double calculateCartesianDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lon2 - lon1, 2) + Math.pow(lat2 - lat1, 2));
    }
}
