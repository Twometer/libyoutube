package de.twometer.libyoutube.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class YouTubeRequest {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.0.0 Safari/537.36";
    private static final String REFERER = "https://www.youtube.com/";
    private static final String CLIENT_NAME = "1";
    private static final String CLIENT_VERSION = "2.20190119";

    private Request request;

    private YouTubeRequest(Request request) {
        this.request = request;
    }

    public static YouTubeRequest forUrl(String url) {
        return new YouTubeRequest(
                new Request.Builder()
                        .url(url)
                        .addHeader("User-Agent", USER_AGENT)
                        .addHeader("Referer", REFERER)
                        .addHeader("x-spf-previous", REFERER)
                        .addHeader("x-spf-referer", REFERER)
                        .addHeader("x-youtube-client-name", CLIENT_NAME)
                        .addHeader("x-youtube-client-version", CLIENT_VERSION)
                        .build()
        );
    }

    public YouTubeResponse execute(OkHttpClient client) throws IOException {
        ResponseBody responseBody = client.newCall(request).execute().body();
        if (responseBody == null)
            return null;
        return new YouTubeResponse(responseBody);
    }

}
