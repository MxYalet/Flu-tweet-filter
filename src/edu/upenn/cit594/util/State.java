package edu.upenn.cit594.util;

public class State {
    private String abbreviation;
    private String name;

    public State(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }
}
