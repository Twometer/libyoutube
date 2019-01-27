package de.twometer.libyoutube.tests;

import de.twometer.libyoutube.YouTube;
import de.twometer.libyoutube.model.Video;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SearchTest {

    @Test
    public void testSearch() throws IOException {
        YouTube youTube = new YouTube();
        List<Video> videos = youTube.search("test");
        System.out.println("Found " + videos.size() + " videos");
        for (Video video : videos) {
            Utils.logVideo(video);
            System.out.println();
        }
    }

}
