package com.epam.brest.io;

import com.epam.brest.entity.Song;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SongsWriter {

    private String dirPath;

    public SongsWriter() {
        this.dirPath = "java-collections-task/resources/";
    }

    public SongsWriter(String dirPath) {
        this.dirPath = dirPath;
    }

    public boolean songListWriter(String fileName, List<Song> list) {
        try (
                FileWriter writer = new FileWriter(dirPath+fileName+".txt", false)
        ) {
            for(Song s : list) {
                String songString = s.getSinger()+"-"+s.getTitle()+"-"+s.getGenre()+"-"+s.getSongDuration()+"\n";
                writer.write(songString);
            }
            writer.flush();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

}
