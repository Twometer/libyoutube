package de.twometer.libyoutube.model;

import de.twometer.libyoutube.core.StreamUrl;

public class VideoStream {

    private Video video;

    private StreamFormat streamFormat;

    private StreamUrl url;

    public VideoStream(Video video, StreamFormat streamFormat, StreamUrl url) {
        this.video = video;
        this.streamFormat = streamFormat;
        this.url = url;
    }

    public Video getVideo() {
        return video;
    }

    public StreamFormat getStreamFormat() {
        return streamFormat;
    }

    public StreamUrl getUrl() {
        return url;
    }
}
