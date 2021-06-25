package com.epam.brest.io;

import com.epam.brest.entity.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SongsReader {

    private String path;
    private List<Song> songList;

    public SongsReader() {
        this.path = "java-collections-task/resources/Songs.txt";
        this.songList = songListReader();
    }

    public SongsReader(String path) {
        this.path = path;
    }

    private List<Song> songListReader() {
        List<Song> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            int id = 1;
            while(line != null) {
                String[] songInfo = line.split("-");
                if(checkSongInfo(songInfo)) {
                    list.add(new Song(id, songInfo[0], songInfo[1], songInfo[2], new Integer(songInfo[3])));
                    id++;
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean checkSongInfo(String[] arr) {
        if(arr.length == 4) {
            try {
                int check = new Integer(arr[3]);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

}
