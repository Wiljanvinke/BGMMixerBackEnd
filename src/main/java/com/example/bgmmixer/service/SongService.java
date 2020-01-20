package com.example.bgmmixer.service;

import com.example.bgmmixer.exceptions.SongNotFoundException;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song addSong(Song song){
        return songRepository.save(song);
    }

    public Song findSongById(long id){
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + id));
        return song;
    }

    public Iterable<Song> findAllSongs(){
        return songRepository.findAll();

    }

}