package com.example.bgmmixer.service;

import com.example.bgmmixer.exceptions.MyFileNotFoundException;
import com.example.bgmmixer.exceptions.SongNotFoundException;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.repositories.FileRepository;
import com.example.bgmmixer.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private FileRepository fileRepository;

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

    public Song saveSong(Song song){
        return songRepository.save(song);
    }

    public Song saveSong(Song song, long fileId){
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id: " + fileId));
        return songRepository.save(new Song(song.getName(), file));
    }

}