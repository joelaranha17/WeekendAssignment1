package com.example.joel.weekendassignment1.model;

/**
 * Created by Joel on 1/22/2018.
 */

public class Video{
    public final String VideoId;
    public final String VideoName;
    public final String VideoDesc;
    public final String VideoThumb;
    public final String VideoFile;

    public Video(String videoId, String videoDesc, String videoFile, String videoThumb,String videoName) {
        VideoDesc = this.getVideoDesc();
        VideoId = this.getVideoId();
        VideoName = this.getVideoName();
        VideoThumb = this.getVideoThumb();
        VideoFile = this.getVideoFile();
    }

    public String getVideoId() {
        return VideoId;
    }

    public String getVideoName() {
        return VideoName;
    }

    public String getVideoDesc() {
        return VideoDesc;
    }

    public String getVideoThumb() {
        return VideoThumb;
    }

    public String getVideoFile() {
        return VideoFile;
    }
}
