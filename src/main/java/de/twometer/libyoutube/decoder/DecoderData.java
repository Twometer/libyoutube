package de.twometer.libyoutube.decoder;

public class DecoderData {

    private String url;

    private String jsPlayer;

    private boolean isEncrypted;

    private int formatCode;

    private String signatureKey;

    public DecoderData(String url, String jsPlayer, boolean isEncrypted, int formatCode, String signatureKey) {
        this.url = url;
        this.jsPlayer = jsPlayer;
        this.isEncrypted = isEncrypted;
        this.formatCode = formatCode;
        this.signatureKey = signatureKey;
    }

    public String getUrl() {
        return url;
    }

    public String getJsPlayer() {
        return jsPlayer;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public int getFormatCode() {
        return formatCode;
    }

    public String getSignatureKey() {
        return signatureKey;
    }
}
