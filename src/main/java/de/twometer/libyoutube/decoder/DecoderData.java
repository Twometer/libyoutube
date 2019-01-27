package de.twometer.libyoutube.decoder;

public class DecoderData {

    private String url;

    private String jsPlayer;

    private boolean isEncrypted;

    private int formatCode;

    public DecoderData(String url, String jsPlayer, boolean isEncrypted, int formatCode) {
        this.url = url;
        this.jsPlayer = jsPlayer;
        this.isEncrypted = isEncrypted;
        this.formatCode = formatCode;
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
}
