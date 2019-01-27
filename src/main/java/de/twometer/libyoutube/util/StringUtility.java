package de.twometer.libyoutube.util;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtility {

    public static String[] split(String input, String separator, boolean skipEmpty) {
        String[] result = input.split(Pattern.quote(separator));
        if (skipEmpty)
            result = Arrays.stream(result)
                    .filter(s -> !StringUtility.isEmpty(s))
                    .toArray(String[]::new);
        return result;
    }

    public static boolean isEmpty(String val) {
        return val == null || val.isEmpty();
    }

}
