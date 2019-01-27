package de.twometer.libyoutube.tests;

import de.twometer.libyoutube.YouTube;
import de.twometer.libyoutube.model.StreamFormat;
import de.twometer.libyoutube.model.StreamType;
import de.twometer.libyoutube.model.VideoStream;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class StreamTest {

    @Test
    public void testStream() throws IOException {
        YouTube youTube = new YouTube();
        List<VideoStream> streams = youTube.getStreams("-GktSUYRO3M");
        for (VideoStream stream : streams) {
            StreamFormat format = stream.getStreamFormat();
            System.out.println(format.getStreamType() + " " + format.getVideoFormat() + " " + format.getAudioFormat() + " " + format.getAudioBitrate());
            if (format.getStreamType() == StreamType.AUDIO)
                System.out.println(stream.getUrl().get());
        }
    }

}
