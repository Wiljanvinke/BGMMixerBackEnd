package com.example.bgmmixer.model;

import com.example.bgmmixer.dtos.PlaylistDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private boolean isDefault;

    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    public Playlist(){
    }

    public Playlist(PlaylistDto playlistDto){
        this.name = playlistDto.getName();
        this.isDefault = playlistDto.isDefault();
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public boolean removeSong(Song song){
        return songs.remove(song);
    }
}
