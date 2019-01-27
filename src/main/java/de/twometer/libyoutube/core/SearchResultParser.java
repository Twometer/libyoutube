package de.twometer.libyoutube.core;

import de.twometer.libyoutube.model.Duration;
import de.twometer.libyoutube.model.Thumbnail;
import de.twometer.libyoutube.model.Video;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultParser {

    private JSONArray contents;

    private SearchResultParser(JSONArray contents) {
        this.contents = contents;
    }

    public static SearchResultParser fromResponse(JSONArray response) {
        // YouTube JSON structure <3
        JSONArray contentsArray = response.getJSONObject(1)
                .getJSONObject("response")
                .getJSONObject("contents")
                .getJSONObject("twoColumnSearchResultsRenderer")
                .getJSONObject("primaryContents")
                .getJSONObject("sectionListRenderer")
                .getJSONArray("contents")
                .getJSONObject(0)
                .getJSONObject("itemSectionRenderer")
                .getJSONArray("contents");
        return new SearchResultParser(contentsArray);
    }

    public List<Video> parse() {
        List<Video> results = new ArrayList<>();
        for (int i = 0; i < contents.length(); i++) {
            JSONObject obj = contents.getJSONObject(i);
            if (!obj.has("videoRenderer")) continue;
            obj = obj.getJSONObject("videoRenderer");

            if (!obj.has("lengthText")) continue;

            String videoId = obj.getString("videoId");
            List<Thumbnail> thumbnails = ThumbnailParser.parseThumbnails(obj.getJSONObject("thumbnail").getJSONArray("thumbnails"));
            String title = obj.getJSONObject("title").getString("simpleText");
            Duration duration = Duration.fromString(obj.getJSONObject("lengthText").getString("simpleText"));
            String author = obj.getJSONObject("longBylineText").getJSONArray("runs").getJSONObject(0).getString("text");
            results.add(new Video(videoId, title, author, thumbnails, duration));
        }
        return results;
    }

}
