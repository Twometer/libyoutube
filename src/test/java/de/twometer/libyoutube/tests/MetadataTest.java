package de.twometer.libyoutube.tests;

import de.twometer.libyoutube.YouTube;
import de.twometer.libyoutube.model.Video;
import org.junit.Test;

import java.io.IOException;

public class MetadataTest {

    @Test
    public void testMetadataFetch() throws IOException {
        YouTube youTube = new YouTube();
        Video video = youTube.getMetadata("1Y897KnVlQ4");
        Utils.logVideo(video);
    }

}
