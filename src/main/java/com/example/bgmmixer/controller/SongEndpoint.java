package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.SongDto;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/songs")
public class SongEndpoint {

    @Autowired
    private SongService songService;

    /**
     * Get a single Song with corresponding Id in Dtoformat
     * @param songId Id to match a Song to
     * @return Song in Dtoformat
     */
    @GetMapping("/{songId}")
    public SongDto getSong(@PathVariable long songId){
        return songService.findSongById(songId);
    }

    /**
     * Get all Songs in the database
     * @return a List of all Songs in Dtoformat
     */
    @GetMapping()
    public Iterable<SongDto> getSongs(){
        return songService.findAllSongs();
    }

    /**
     * Post a new Song to the database
     * @param newSong body of the request containing the values of the new Song
     * @return new Song
     */
    @PostMapping()
    public Song newSong(@RequestBody Song newSong){
        return songService.saveSong(newSong);
    }

    /**
     * Post a new Song to the database with an existing File in the database
     * @param newSong body of the request containing the values of the new Song
     * @param fileId Id of the File to attach to the Song
     * @return ResponseEntity containing the newly posted Song in Dtoformat
     */
    @PostMapping("/{fileID}")
    public ResponseEntity<SongDto> newSong(@RequestBody Song newSong, @PathVariable long fileId){
        return songService.saveSong(newSong, fileId);
    }

    /**
     * Update the Song with corresponding Id to include the values included in the body
     * @param songDto body containing the new values of the Song
     * @param songId body of the request containing the new values of the Song
     * @return ResponseEntity containing the newly updated Song in Dtoformat
     */
    @PutMapping("/{songID}")
    public ResponseEntity<SongDto> updateSong(@RequestBody SongDto songDto, @PathVariable long songId){
        return songService.updateSong(songDto, songId);
    }

    /**
     * Delete the Song with the corresponding Id
     * @param songId Id of the song to delete
     */
    @DeleteMapping("/{songID}")
    public void deleteSong(@PathVariable long songId){
        songService.deleteSong(songId);
    }


}
