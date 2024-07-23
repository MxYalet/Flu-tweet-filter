package edu.upenn.cit594.studenttests.ui;

import java.util.Map;
import java.util.TreeMap;

public class Output {
    public void displayTweetCounts(Map<String, Integer> tweetCounts) {
        TreeMap<String, Integer> sortedCounts = new TreeMap<>(tweetCounts);
        for (Map.Entry<String, Integer> entry : sortedCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
