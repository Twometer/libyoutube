package de.twometer.libyoutube;

import de.twometer.libyoutube.core.MetadataParser;
import de.twometer.libyoutube.core.SearchResultParser;
import de.twometer.libyoutube.core.StreamParser;
import de.twometer.libyoutube.model.Video;
import de.twometer.libyoutube.model.VideoStream;
import de.twometer.libyoutube.web.YouTubeRequest;
import okhttp3.OkHttpClient;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class YouTube {

    private static final String CHARSET = "UTF-8";

    private OkHttpClient client = new OkHttpClient();

    public List<Video> search(String query) throws IOException {
        String requestUrl = String.format("https://www.youtube.com/results?search_query=%s&pbj=1", URLEncoder.encode(query, CHARSET));
        JSONArray response = loadJsonArray(requestUrl);
        return SearchResultParser.fromResponse(response)
                .parse();
    }

    public Video getMetadata(String videoId) throws IOException {
        String requestUrl = String.format("https://www.youtube.com/watch?v=%s&pbj=1", URLEncoder.encode(videoId, CHARSET));
        JSONArray response = loadJsonArray(requestUrl);
        return MetadataParser.fromResponse(response)
                .parse();
    }

    public List<VideoStream> getStreams(String videoId) throws IOException {
        String requestUrl = String.format("https://www.youtube.com/watch?v=%s&pbj=1", URLEncoder.encode(videoId, CHARSET));
        JSONArray response = loadJsonArray(requestUrl);

        Video video = MetadataParser.fromResponse(response)
                .parse();

        return StreamParser.fromResponse(video, response)
                .parse();
    }

    private JSONArray loadJsonArray(String url) throws IOException {
        return YouTubeRequest.forUrl(url)
                .execute(client)
                .jsonArray();
    }

}
