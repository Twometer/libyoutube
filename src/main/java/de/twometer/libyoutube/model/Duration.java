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

    public double getTotalSeconds() {
        return totalSeconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String toFormattedString() {
        if (getHours() > 0)
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
        else
            return String.format(Locale.getDefault(), "%02d:%02d", getMinutes(), getSeconds());
    }

}
