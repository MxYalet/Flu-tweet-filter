package edu.upenn.cit594.studenttests.util;

import java.util.Arrays;

public class Tweet {
    private double[] location; // Array for latitude and longitude
    private String time;
    private String text;

    // Default constructor for deserialization
    public Tweet() {}

    // Constructor to initialize Tweet object
    public Tweet(double[] location, String time, String text) {
        this.location = location;
        this.time = time;
        this.text = text;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "location=" + Arrays.toString(location) +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
