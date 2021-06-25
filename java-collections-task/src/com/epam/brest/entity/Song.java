package com.epam.brest.entity;

import java.util.Objects;

public class Song {

    private int id;
    private String singer;
    private String title;
    private String genre;
    private int songDuration;

    public Song() {
        this.singer = "Unknown";
        this.title = "Unknown";
        this.genre = "Unknown";
        this.songDuration = 0;
    }

    public Song(int id, String singer, String title, String genre, int songDuration) {
        this.id = id;
        this.singer = singer;
        this.title = title;
        this.genre = genre;
        this.songDuration = songDuration;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id && songDuration == song.songDuration && Objects.equals(singer, song.singer) && Objects.equals(title, song.title) && Objects.equals(genre, song.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singer, title, genre, songDuration);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", songDuration=" + songDuration +
                '}';
    }

}
