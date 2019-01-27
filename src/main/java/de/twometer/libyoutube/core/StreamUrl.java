package de.twometer.libyoutube.core;

import de.twometer.libyoutube.decoder.DecoderData;
import de.twometer.libyoutube.decoder.Query;
import de.twometer.libyoutube.decoder.SignatureDecoder;
import de.twometer.libyoutube.web.CompressedRequest;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class StreamUrl {

    private OkHttpClient client = new OkHttpClient();

    private DecoderData decoderData;

    private String url;

    private boolean isDecoded;

    StreamUrl(DecoderData decoderData) {
        this.decoderData = decoderData;
    }

    public String get() throws IOException {
        if (!decoderData.isEncrypted())
            return decoderData.getUrl();
        if (!isDecoded) {
            isDecoded = true;
            decode();
        }
        return url;
    }

    private void decode() throws IOException {
        Query query = Query.parse(decoderData.getUrl());
        String signature = query.get("signature");
        if (signature == null) {
            url = decoderData.getUrl();
            return;
        }

        String jsPlayer = CompressedRequest.forUrl(decoderData.getJsPlayer()).execute(client);
        SignatureDecoder signatureDecoder = new SignatureDecoder(jsPlayer, signature);
        query.set("signature", signatureDecoder.decode());

        url = query.toString();
    }

}
