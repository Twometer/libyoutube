package de.twometer.libyoutube.model;

import java.util.List;

public class Video {

    private String videoId;

    private String title;

    private String author;

    private List<Thumbnail> thumbnails;

    private Duration duration;

    public Video(String videoId, String title, String author, List<Thumbnail> thumbnails, Duration duration) {
        this.videoId = videoId;
        this.title = title;
        this.author = author;
        this.thumbnails = thumbnails;
        this.duration = duration;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public Duration getDuration() {
        return duration;
    }
}
