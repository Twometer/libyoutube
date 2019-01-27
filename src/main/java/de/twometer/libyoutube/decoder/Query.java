package de.twometer.libyoutube.decoder;

import de.twometer.libyoutube.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private String baseUrl;
    private List<Entry> entries;

    private Query(String baseUrl, List<Entry> entries) {
        this.baseUrl = baseUrl;
        this.entries = entries;
    }

    public static Query parse(String queryString) {
        queryString = queryString.replace("\\u0026", "&");

        String baseUrl;
        List<Entry> entries = new ArrayList<>();

        int divide = queryString.indexOf("?");
        if (divide == -1) {
            int amp = queryString.indexOf("&");
            if (amp == -1) {
                baseUrl = queryString;
            } else {
                baseUrl = null;
            }
        } else {
            baseUrl = queryString.substring(0, divide);
            queryString = queryString.substring(divide + 1);
        }
        StringBuilder cache = new StringBuilder();
        Entry currentEntry = new Entry();
        for (int i = 0; i < queryString.length(); i++) {
            char chr = queryString.charAt(i);
            if (chr == '=') {
                currentEntry.key = cache.toString();
                cache = new StringBuilder();
            } else if (chr == '&') {
                currentEntry.value = cache.toString();
                entries.add(currentEntry);
                currentEntry = new Entry();
                cache = new StringBuilder();
            } else {
                cache.append(chr);
            }
        }
        currentEntry.value = cache.toString();
        if (!currentEntry.isEmpty())
            entries.add(currentEntry);

        return new Query(baseUrl, entries);
    }

    public boolean has(String key) {
        return get(key) != null;
    }

    public String get(String key) {
        for (Entry entry : entries)
            if (entry.key != null && entry.key.equals(key))
                return entry.value;
        return null;
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public void set(String key, String value) {
        for (Entry entry : entries)
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        entries.add(new Entry(key, value));
    }

    @Override
    public String toString() {
        if (entries.size() == 0)
            return baseUrl;
        StringBuilder builder = new StringBuilder();
        if (baseUrl != null)
            builder.append(baseUrl).append("?");

        Entry entry0 = entries.get(0);
        builder.append(entry0.key).append("=").append(entry0.value);
        for (int i = 1; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            builder.append("&").append(entry.key).append("=").append(entry.value);
        }
        return builder.toString();
    }

    private static class Entry {
        private String key;
        private String value;

        Entry() {
        }

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private boolean isEmpty() {
            return StringUtility.isEmpty(key) && StringUtility.isEmpty(value);
        }

    }
}
