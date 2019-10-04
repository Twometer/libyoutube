package de.twometer.libyoutube.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isMatch(String input, String regex) {
        return Pattern.compile(regex).matcher(input).find();
    }

    public static Matcher match(String input, String regex) {
        return match(input, regex, 0);
    }

    public static Matcher match(String input, String regex, int flag) {
        Matcher matcher = Pattern.compile(regex, flag).matcher(input);
        if (!matcher.find())
            throw new RuntimeException("No match found for " + regex);
        return matcher;
    }

}
