package de.twometer.libyoutube.model;

public class StreamFormat {

    private int formatCode;

    public StreamFormat(int formatCode) {
        this.formatCode = formatCode;
    }

    public boolean is3d() {
        switch (formatCode) {
            case 82:
            case 83:
            case 84:
            case 85:
            case 100:
            case 101:
            case 102:
                return true;
            default:
                return false;
        }
    }

    public StreamType getStreamType() {
        switch (formatCode) {
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 160:
            case 242:
            case 243:
            case 244:
            case 247:
            case 248:
            case 264:
            case 271:
            case 272:
            case 278:
            case 313:
                return StreamType.VIDEO;
            case 139:
            case 140:
            case 141:
            case 171:
            case 172:
            case 249:
            case 250:
            case 251:
                return StreamType.AUDIO;
            default:
                return StreamType.UNKNOWN;
        }
    }

    public int getAudioBitrate() {
        switch (formatCode) {
            case 5:
            case 6:
            case 250:
                return 64;
            case 17:
                return 24;
            case 18:
            case 82:
            case 83:
                return 96;
            case 22:
            case 37:
            case 38:
            case 45:
            case 46:
            case 101:
            case 102:
            case 172:
                return 192;
            case 34:
            case 35:
            case 43:
            case 44:
            case 100:
            case 140:
            case 171:
                return 128;
            case 36:
                return 38;
            case 84:
            case 85:
                return 152;
            case 251:
                return 160;
            case 139:
            case 249:
                return 48;
            case 141:
                return 256;
            default:
                return -1;
        }
    }

    public int getResolution() {
        switch (formatCode) {
            case 5:
            case 36:
            case 83:
            case 133:
            case 242:
                return 240;
            case 6:
                return 270;
            case 17:
            case 160:
            case 278:
                return 144;
            case 18:
            case 34:
            case 43:
            case 82:
            case 100:
            case 101:
            case 134:
            case 243:
                return 360;
            case 22:
            case 45:
            case 84:
            case 102:
            case 136:
            case 247:
                return 720;
            case 35:
            case 44:
            case 135:
            case 244:
                return 480;
            case 37:
            case 46:
            case 137:
            case 248:
                return 1080;
            case 38:
                return 3072; // what
            case 85:
                return 520;
            case 138:
            case 272:
            case 313:
                return 2160;
            case 264:
            case 271:
                return 1440;
            default:
                return -1;
        }
    }

    public VideoFormat getVideoFormat() {
        switch (formatCode) {
            case 5:
            case 6:
            case 34:
            case 35:
                return VideoFormat.FLASH;
            case 13:
            case 17:
            case 36:
                return VideoFormat.MOBILE;
            case 18:
            case 22:
            case 37:
            case 38:
            case 82:
            case 83:
            case 84:
            case 85:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 160:
            case 264:
            case 139:
            case 140:
            case 141:
                return VideoFormat.MP4;
            case 43:
            case 44:
            case 45:
            case 46:
            case 100:
            case 101:
            case 102:
            case 242:
            case 243:
            case 244:
            case 247:
            case 248:
            case 271:
            case 272:
            case 278:
            case 171:
            case 172:
            case 249:
            case 250:
            case 251:
            case 313:
                return VideoFormat.WEBM;
            default:
                return VideoFormat.UNKNOWN;
        }
    }

    public AudioFormat getAudioFormat() {
        switch (formatCode) {
            case 5:
            case 6:
                return AudioFormat.MP3;
            case 13:
            case 17:
            case 18:
            case 22:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 82:
            case 83:
            case 84:
            case 85:
            case 139:
            case 140:
            case 141:
                return AudioFormat.AAC;
            case 43:
            case 44:
            case 45:
            case 46:
            case 100:
            case 101:
            case 102:
            case 171:
            case 172:
                return AudioFormat.VORBIS;
            case 249:
            case 250:
            case 251:
                return AudioFormat.OPUS;
            default:
                return AudioFormat.UNKNOWN;
        }
    }

}
