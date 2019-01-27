package de.twometer.libyoutube.core;

import de.twometer.libyoutube.model.Duration;
import de.twometer.libyoutube.model.Thumbnail;
import de.twometer.libyoutube.model.Video;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MetadataParser {

    private JSONObject videoDetails;

    private MetadataParser(JSONObject videoDetails) {
        this.videoDetails = videoDetails;
    }

    public static MetadataParser fromResponse(JSONArray response) {
        JSONObject videoDetails = response.getJSONObject(3)
                .getJSONObject("playerResponse")
                .getJSONObject("videoDetails");
        return new MetadataParser(videoDetails);
    }

    public Video parse() {
        String videoId = videoDetails.getString("videoId");
        List<Thumbnail> thumbnails = ThumbnailParser.parseThumbnails(videoDetails.getJSONObject("thumbnail").getJSONArray("thumbnails"));
        String title = videoDetails.getString("title");
        Duration duration = new Duration(videoDetails.getInt("lengthSeconds"));
        String author = videoDetails.getString("author");
        return new Video(videoId, title, author, thumbnails, duration);
    }

}
