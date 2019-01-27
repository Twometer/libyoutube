# libyoutube
An unofficial YouTube API with support for decrypting streams.

## Features
1. Searching for videos
2. Getting metadata for videos
3. Downloading and decrypting the video/audio stream URLs 

## Download
You can use the library either with Gradle or Maven using
```
implementation 'de.twometer.libyoutube:libyoutube:1.0'
```
or you can download the latest JAR file from the release section of this repo.

Alternatively, you can also build the repository yourself.

## Getting Started
The API has a main class called `YouTube`. From there you can access
all the features of this library. Information about a video is provided
by the `Video` class, and accessing streams is provided using the `VideoStream`
class.

To get you started, I have some example code for you:

### Searching for videos
```java
YouTube youTube = new YouTube();
List<Video> videos = youTube.search("your query");
for (Video video : videos) {
    String title = video.getTitle();
    // ...
}
```

### Getting the stream URL
```java
YouTube youTube = new YouTube();
List<VideoStream> videoStreams = youTube.getStreams("your video id");
for (VideoStream videoStream : videoStreams) {
    String url = videoStream.getUrl().get();
    // ...
}
```

### Downloading metadata
```java
YouTube youTube = new YouTube();
Video video = youTube.getMetadata("your video id");
// ...
```

## Legal stuff
This library is based on the C# library `libvideo` made by [i3arnon](https://github.com/i3arnon)

The `libvideo` copyright notice is as folllows:
```
BSD 2-Clause License

Copyright (c) 2018, Bar Arnon
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```

Also, I am not in any way associated with YouTube or Google. This is a completely unofficial project.