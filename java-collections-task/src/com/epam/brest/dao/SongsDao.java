package com.epam.brest.dao;

import com.epam.brest.entity.Song;
import com.epam.brest.io.SongsReader;
import com.epam.brest.io.SongsWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SongsDao {

    private final SongsReader songsReader = new SongsReader();
    private final SongsWriter songsWriter = new SongsWriter();
    private List<Song> allSongs = songsReader.getSongList();
    private List<Song> selectedSongs = new ArrayList<>();

    public SongsDao() {}

    public void getAllSongs() {
        for(Song s : allSongs)
            System.out.format("ID: %-2d \tSINGER: %-35s TITLE: %-60s GENRE: %-10s DURATION: %-4d sec.\n",
                    s.getId(), s.getSinger(), s.getTitle(), s.getGenre(), s.getSongDuration());
    }

    public void getAllSongsSortedBySinger() {
        Comparator<Song> comparator = Comparator.comparing(obj -> obj.getSinger());
        Collections.sort(allSongs, comparator);
        for(Song s : allSongs)
            System.out.format("ID: %-2d \tSINGER: %-35s TITLE: %-60s GENRE: %-10s DURATION: %-4d sec.\n",
                    s.getId(), s.getSinger(), s.getTitle(), s.getGenre(), s.getSongDuration());
    }

    public void getAllSongsSortedByGenre() {
        Comparator<Song> comparator = Comparator.comparing(obj -> obj.getGenre());
        Collections.sort(allSongs, comparator.thenComparing(obj -> obj.getSinger()));
        for(Song s : allSongs)
            System.out.format("ID: %-2d \tGENRE: %-10s SINGER: %-35s TITLE: %-60s DURATION: %-4d sec.\n",
                    s.getId(), s.getGenre(), s.getSinger(), s.getTitle(), s.getSongDuration());
    }

    public void getAllSongsSortedByDuration() {
        Comparator<Song> comparator = Comparator.comparing(obj -> obj.getSongDuration());
        Collections.sort(allSongs, comparator);
        for(Song s : allSongs)
            System.out.format("ID: %-2d \tDURATION: %-4d sec. \tGENRE: %-10s SINGER: %-35s TITLE: %-60s\n",
                    s.getId(), s.getSongDuration(), s.getGenre(), s.getSinger(), s.getTitle());
    }

    public void getSongsByDuration(int minDuration, int maxDuration) {
        int count = 0;
        for(Song s : allSongs) {
            if(s.getSongDuration() >= minDuration && s.getSongDuration() <= maxDuration) {
                System.out.format("ID: %-2d \tDURATION: %-4d sec. \tGENRE: %-10s SINGER: %-35s TITLE: %-60s\n",
                        s.getId(), s.getSongDuration(), s.getGenre(), s.getSinger(), s.getTitle());
                count++;
            }
        }
        if(count == 0)
            System.out.println("Songs with duration from "+minDuration+" to "+maxDuration+" are not found");
    }

    public void addToSelectedSongs(int id) {
        boolean check = false;
        for(Song s : allSongs) {
            if(s.getId() == id) {
                selectedSongs.add(s);
                check = true;
                System.out.println("Song added: "+ s.toString());
            }
        }
        if(check == false)
            System.out.println("Song with ID="+id+" is not founded");
    }

    public void getSelectedSongs() {
        if(!selectedSongs.isEmpty()) {
            for(Song s : selectedSongs)
                System.out.format("ID: %-2d \tSINGER: %-35s TITLE: %-60s GENRE: %-10s DURATION: %-4d sec.\n",
                        s.getId(), s.getSinger(), s.getTitle(), s.getGenre(), s.getSongDuration());
        } else {
            System.out.println("You haven't added any songs yet");
        }
    }

    public void writeSelectedSongsToFile(String fileName) {
        if(!selectedSongs.isEmpty()) {
            boolean res = songsWriter.songListWriter(fileName, selectedSongs);
            if(res) {
                System.out.println("The selected songs are successfully written to the file: 'java-collections-task/resources/"+fileName+"'");
            } else {
                System.out.println("Error has occurred");
            }
        } else {
            System.out.println("You haven't added any songs yet");
        }
    }

}
