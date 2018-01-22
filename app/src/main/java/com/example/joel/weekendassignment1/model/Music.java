package com.example.joel.weekendassignment1.model;

/**
 * Created by Joel on 1/22/2018.
 */

public class Music {
    public final String AlbumId;
    public final String AlbumName;
    public final String AlbumDesc;
    public final String AlbumThumb;
    public final String MusicFile;

    public Music(String albumDesc, String albumId, String albumName, String albumThumb,String musicFile) {
        AlbumId = this.getAlbumId();
        AlbumDesc = this.getAlbumDesc();
        AlbumName = this.getAlbumName();
        AlbumThumb = this.getAlbumThumb();
        MusicFile = this.getMusicFile();
    }

    public String getAlbumId() {
        return AlbumId;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public String getAlbumDesc() {
        return AlbumDesc;
    }

    public String getAlbumThumb() {
        return AlbumThumb;
    }

    public String getMusicFile() {
        return MusicFile;
    }
}
