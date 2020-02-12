package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.PlaylistDto;
import com.example.bgmmixer.dtos.SongDto;
import com.example.bgmmixer.model.Playlist;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/playlists")
public class PlaylistEndpoint {

    @Autowired
    private SongService songService;

    @GetMapping
    private List<PlaylistDto> getPlaylists(){
        System.out.println("GET all Playlists");
        return songService.findAllPlaylists();
    }

    @GetMapping("/{playlistId}")
    private PlaylistDto getPlaylist(@PathVariable long playlistId){
        System.out.println("GET Playlist " + playlistId);
        return songService.findPlaylistById(playlistId);
    }

    @GetMapping("/default")
    private ResponseEntity<PlaylistDto> getDefaultPlaylist(){
        return songService.findDefaultPlaylist();
    }

    @GetMapping("/{playlistId}/songs")
    private List<SongDto> getSongsFromPlaylist(@PathVariable long playlistId){
        System.out.println("GET Songs from Playlist " + playlistId);
        return songService.findSongsFromPlaylist(playlistId);
    }

    @PostMapping
    private ResponseEntity<PlaylistDto> newPlaylist(@RequestBody PlaylistDto playlistDto){
        System.out.println("POST new Playlist");
        return songService.savePlaylist(playlistDto);
    }

    @PutMapping("/{playlistId}")
    private ResponseEntity<PlaylistDto> updatePlaylist(
            @PathVariable long playlistId, @RequestBody PlaylistDto playlistDto){
        System.out.println("PUT Playlist " + playlistId);
        return songService.updatePlaylist(playlistId, playlistDto);
    }

    @PutMapping("/{playlistId}/{songId}")
    private ResponseEntity<PlaylistDto> addSongToPlaylist(@PathVariable long playlistId, @PathVariable long songId) {
        System.out.println("PUT Song : " + songId + " to Playlist: " + playlistId);
        return songService.addSongToPlaylist(playlistId, songId);
    }

    @PutMapping("/{playlistId}/default")
    private ResponseEntity<PlaylistDto> updatePlaylistDefault(@PathVariable long playlistId){
        System.out.println("PUT default Playlist " + playlistId);
        return songService.setDefaultplaylist(playlistId);
    }

    @DeleteMapping("/{playlistId}")
    private ResponseEntity<PlaylistDto> deletePlaylist(@PathVariable long playlistId){
        System.out.println("DELETE Playlist " + playlistId);
        return songService.deletePlaylist(playlistId);
    }
}
