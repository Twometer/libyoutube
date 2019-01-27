package de.twometer.libyoutube.model;

import java.util.Locale;

public class Duration {

    private double totalSeconds;

    private int hours;
    private int minutes;
    private int seconds;

    public Duration(double totalSeconds) {
        this.totalSeconds = totalSeconds;
        hours = (int) (totalSeconds / 3600.0D);
        minutes = (int) ((totalSeconds - hours * 3600.0D) / 60.0D);
        seconds = (int) (totalSeconds - hours * 3600.0D - minutes * 60.0D);
    }

    private Duration(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static Duration fromString(String string) {
        string = string.trim();
        String[] parts = string.split(":");
        if (parts.length == 2) return new Duration(0, Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        else if (parts.length == 3)
            return new Duration(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));

        throw new IllegalArgumentException("The given duration string is not in a correct format");
    }

    public double getTotalSeconds() {
        return totalSeconds;
    }

    private int getHours() {
        return hours;
    }

    private int getMinutes() {
        return minutes;
    }

    private int getSeconds() {
        return seconds;
    }

    public String toFormattedString() {
        if (getHours() > 0)
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
        else
            return String.format(Locale.getDefault(), "%02d:%02d", getMinutes(), getSeconds());
    }

}
