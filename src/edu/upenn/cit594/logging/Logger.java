package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance;
    private PrintWriter writer;

    private Logger(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(String filename) {
        if (instance == null) {
            instance = new Logger(filename);
        }
        return instance;
    }

    public void log(String message) {
        writer.println(message);
        writer.flush();
    }

    public void logFluTweet(String state, String tweet) {
        writer.println(state + "\t" + tweet);
        writer.flush();
    }
}
