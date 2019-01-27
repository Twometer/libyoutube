package de.twometer.libyoutube.core;

import de.twometer.libyoutube.model.Thumbnail;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ThumbnailParser {

    static List<Thumbnail> parseThumbnails(JSONArray array) {
        List<Thumbnail> thumbnails = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            thumbnails.add(new Thumbnail(jsonObject.getString("url"),
                    jsonObject.getInt("width"),
                    jsonObject.getInt("height")));
        }
        return thumbnails;
    }

}
