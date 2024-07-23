package edu.upenn.cit594.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetUtils {

    private static final Pattern FLU_PATTERN = Pattern.compile(
            "\\b#flu\\b(?![a-zA-Z])|\\bflu\\b(?![a-zA-Z])",
            Pattern.CASE_INSENSITIVE
    );

    public static boolean isFluTweet(String text) {
        Matcher matcher = FLU_PATTERN.matcher(text);
        return matcher.find();
    }
}
