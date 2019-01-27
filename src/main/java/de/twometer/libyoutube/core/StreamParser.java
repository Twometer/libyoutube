package de.twometer.libyoutube.core;

import de.twometer.libyoutube.decoder.DecoderData;
import de.twometer.libyoutube.decoder.Query;
import de.twometer.libyoutube.model.StreamFormat;
import de.twometer.libyoutube.model.Video;
import de.twometer.libyoutube.model.VideoStream;
import de.twometer.libyoutube.util.StringUtility;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class StreamParser {

    private static final String BASE_URL = "https://www.youtube.com";
    private static final String ENCODING = "UTF-8";

    private Video video;

    private JSONArray response;
    private JSONObject player;

    private StreamParser(Video video, JSONArray response, JSONObject player) {
        this.video = video;
        this.response = response;
        this.player = player;
    }

    public static StreamParser fromResponse(Video video, JSONArray response) {
        return new StreamParser(video, response, response
                .getJSONObject(2)
                .getJSONObject("player"));
    }

    public List<VideoStream> parse() throws UnsupportedEncodingException {
        JSONObject args = player.getJSONObject("args");

        String jsPlayer = getJsPlayerUrl();
        String streamMap = args.getString("url_encoded_fmt_stream_map");
        String adaptiveMap = args.getString("adaptive_fmts");

        List<DecoderData> decoderDataList = new ArrayList<>();
        decoderDataList.addAll(decodeMap(streamMap, jsPlayer));
        decoderDataList.addAll(decodeMap(adaptiveMap, jsPlayer));

        List<VideoStream> streams = new ArrayList<>();
        for (DecoderData decoderData : decoderDataList)
            streams.add(new VideoStream(video, new StreamFormat(decoderData.getFormatCode()), new StreamUrl(decoderData)));

        return streams;
    }

    private List<DecoderData> decodeMap(String map, String jsPlayer) throws UnsupportedEncodingException {
        List<DecoderData> results = new ArrayList<>();
        String[] parts = StringUtility.split(map, ",", true);
        for (String part : parts)
            results.add(prepareDecoder(part, jsPlayer));
        return results;
    }

    private DecoderData prepareDecoder(String queryString, String jsPlayer) throws UnsupportedEncodingException {
        Query query = Query.parse(queryString);

        String url = query.get("url");
        boolean encrypted = false;

        if (query.has("s")) {
            encrypted = true;
            url += buildSignatureString(query.get("s"), query);
        } else if (query.has("sig"))
            url += buildSignatureString(query.get("sig"), query);

        url = decodeUrl(url);
        Query urlQuery = Query.parse(url);
        if (!urlQuery.has("ratebypass"))
            url += "&ratebypass=yes";

        return new DecoderData(url, jsPlayer, encrypted, urlQuery.getInt("itag"));
    }

    private String decodeUrl(String url) throws UnsupportedEncodingException {
        for (int i = 0; i < 2; i++)
            url = URLDecoder.decode(url, ENCODING);
        return url;
    }

    private String buildSignatureString(String signature, Query query) {
        String result = "&signature=" + signature;
        String host = query.get("fallback_host");
        if (host != null)
            result += "&fallback_host=" + host;
        return result;
    }

    private String getJsPlayerUrl() {
        String playerUrl = response
                .getJSONObject(2)
                .getJSONObject("player")
                .getJSONObject("assets")
                .getString("js");
        return BASE_URL + playerUrl;
    }

}
