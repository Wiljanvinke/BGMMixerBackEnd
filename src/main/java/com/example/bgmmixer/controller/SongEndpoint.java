package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.SongDto;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.payload.UploadFileResponse;
import com.example.bgmmixer.service.FileService;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/songs")
public class SongEndpoint {

    @Autowired
    private SongService songService;

    @GetMapping("/{songId}")
    public SongDto getSong(@PathVariable long songId){
        return songService.findSongById(songId);
    }

    @GetMapping()
    public Iterable<SongDto> getSongs(){
        return songService.findAllSongs();
    }

    @PostMapping()
    public Song newSong(@RequestBody Song newSong){
        return songService.saveSong(newSong);
    }

    @PostMapping("/{fileID}")
    public ResponseEntity<SongDto> newSong(@RequestBody Song newSong, @PathVariable long fileID){
        return songService.saveSong(newSong, fileID);
    }

    @PutMapping("/{songID}")
    public ResponseEntity<SongDto> updateSong(@RequestBody SongDto songDto, @PathVariable long songID){
        return songService.updateSong(songDto, songID);
    }

    @DeleteMapping("/{songID}")
    public void deleteSong(@PathVariable long songID){
        songService.deleteSong(songID);
    }


}
