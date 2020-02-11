package com.example.bgmmixer.dtos;

import com.example.bgmmixer.model.Playlist;
import com.example.bgmmixer.model.Song;

public class PlaylistDto {
    private long id;
    private String name;
    private boolean isDefault;
    private long[] songIds;

    public PlaylistDto(){

    }

    public PlaylistDto(Playlist playlist){
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.isDefault = playlist.isDefault();
        songIds = new long[playlist.getSongs().size()];
        for(int i = 0; i < playlist.getSongs().size(); i++){
            songIds[i] = playlist.getSongs().get(i).getId();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public long[] getSongIds() {
        return songIds;
    }

    public void setSongIds(long[] songIds) {
        this.songIds = songIds;
    }
}
