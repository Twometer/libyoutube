package de.twometer.libyoutube.tests;

import de.twometer.libyoutube.model.Thumbnail;
import de.twometer.libyoutube.model.Video;

class Utils {

    static void logVideo(Video video) {
        System.out.println("Video title: " + video.getTitle());
        System.out.println("Video ID: " + video.getVideoId());
        System.out.println("Thumbnails:");
        for (Thumbnail thumbnail : video.getThumbnails())
            System.out.println("- " + thumbnail.getWidth() + "x" + thumbnail.getHeight() + " " + thumbnail.getUrl());
        System.out.println("Author: " + video.getAuthor());
        System.out.println("Duration: " + video.getDuration().toFormattedString());
    }

}
