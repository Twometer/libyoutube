package de.twometer.libyoutube.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class CompressedRequest {

    private Request request;

    private CompressedRequest(Request request) {
        this.request = request;
    }

    public static CompressedRequest forUrl(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Twometer")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "Keep-Alive")
                .build();
        return new CompressedRequest(request);
    }

    public String execute(OkHttpClient client) throws IOException {
        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();

        if (body == null)
            throw new IOException("Failed to get response body of the compressed stream");

        InputStream inputStream = body.byteStream();
        String inputEncoding = response.header("Content-Encoding");
        if (Objects.equals(inputEncoding, "gzip"))
            inputStream = new GZIPInputStream(inputStream);
        else if (Objects.equals(inputEncoding, "deflate"))
            inputStream = new InflaterInputStream(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8192);
        byte[] buf = new byte[8192];
        int read;
        while ((read = inputStream.read(buf)) > 0) outputStream.write(buf, 0, read);

        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

}
