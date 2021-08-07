package com.example.musicstream;

public class Song {
    private String id;
    private String title;
    private String artist;
    private String fileLink;
    private double songLength;
    private int drawable;
    private String chordsAndLyrics;

    public Song(String id, String title, String artist, String fileLink, double songLength, int drawable,String chordsAndLyrics) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;
        this.chordsAndLyrics = chordsAndLyrics;
    }

//This is to return the ID value
    public String getId() {
        return id;
    }
//This is to assign a new value to the class variable
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public double getSongLength() {
        return songLength;
    }

    public void setSongLength(double songLength) {
        this.songLength = songLength;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getChordsAndLyrics() {return chordsAndLyrics;}

    public void setChordsAndLyrics(String chordsAndLyrics) {this.chordsAndLyrics = chordsAndLyrics;}
}
