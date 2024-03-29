package de.twometer.libyoutube.web;

import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class YouTubeResponse {

    private String response;

    YouTubeResponse(ResponseBody responseBody) throws IOException {
        this.response = responseBody.string();
        responseBody.close();
    }

    public JSONObject jsonObject() {
        return new JSONObject(response);
    }

    public JSONArray jsonArray() {
        return new JSONArray(response);
    }

}
