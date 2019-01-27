package de.twometer.libyoutube.decoder;

import de.twometer.libyoutube.util.RegexUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JsDecrypter {

    private static final Pattern parametersRegex = Pattern.compile("\\(\\w+,(\\d+)\\)");
    private final Map<String, FunctionType> functionTypes = new HashMap<>();

    boolean isComplete() {
        return functionTypes.size() == FunctionType.values().length;
    }

    void addFunction(String js, String function) {
        String escapedFunction = Pattern.quote(function);
        FunctionType type = null;
        if (RegexUtility.isMatch(js, escapedFunction + ":\\bfunction\\b\\(\\w+\\)"))
            type = FunctionType.Reverse;
        else if (RegexUtility.isMatch(js, escapedFunction + ":\\bfunction\\b\\([a],b\\).(\\breturn\\b)?.?\\w+\\."))
            type = FunctionType.Slice;
        else if (RegexUtility.isMatch(js, escapedFunction + ":\\bfunction\\b\\(\\w+\\,\\w\\).\\bvar\\b.\\bc=a\\b"))
            type = FunctionType.Swap;

        if (type != null)
            functionTypes.put(function, type);
    }

    String executeFunction(String signature, String line, String function) {
        FunctionType type = functionTypes.get(function);
        if (type == null)
            return signature;

        if (type == FunctionType.Reverse)
            return reverse(signature);
        else {
            Matcher matcher = parametersRegex.matcher(line);
            matcher.find();
            int index = Integer.parseInt(matcher.group(1));
            if (type == FunctionType.Slice)
                return slice(signature, index);
            else
                return swap(signature, index);
        }
    }

    private String reverse(String signature) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = signature.length() - 1; index >= 0; index--)
            stringBuilder.append(signature.charAt(index));
        return stringBuilder.toString();
    }

    private String slice(String signature, int index) {
        return signature.substring(index);
    }

    private String swap(String signature, int index) {
        StringBuilder builder = new StringBuilder();
        builder.append(signature);
        builder.setCharAt(0, signature.charAt(index));
        builder.setCharAt(index, signature.charAt(0));
        return builder.toString();
    }

    private enum FunctionType {
        Reverse,
        Slice,
        Swap
    }


}
