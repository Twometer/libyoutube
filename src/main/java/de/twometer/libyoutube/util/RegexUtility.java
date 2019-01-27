package de.twometer.libyoutube.util;

import java.util.regex.Pattern;

public class RegexUtility {

    public static boolean isMatch(String input, String regex) {
        return Pattern.compile(regex).matcher(input).find();
    }

}
