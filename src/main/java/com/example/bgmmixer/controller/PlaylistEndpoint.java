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

    /**
     * Get all Playlists in the database
     * @return a List of all Playlists in Dtoformat
     */
    @GetMapping
    private List<PlaylistDto> getPlaylists(){
        System.out.println("GET all Playlists");
        return songService.findAllPlaylists();
    }

    /**
     * Get a single Playlist with corresponding Id in Dtoformat
     * @param playlistId Id to match a Playlist to
     * @return Playlist in Dtoformat
     */
    @GetMapping("/{playlistId}")
    private PlaylistDto getPlaylist(@PathVariable long playlistId){
        System.out.println("GET Playlist " + playlistId);
        return songService.findPlaylistById(playlistId);
    }

    /**
     * Get the default Playlist in Dtoformat
     * @return a ResponseEntity containing the default Playlist
     */
    @GetMapping("/default")
    private ResponseEntity<PlaylistDto> getDefaultPlaylist(){
        return songService.findDefaultPlaylist();
    }

    /**
     * Get the Songs of the Playlist with the corresponding Id
     * @param playlistId Id of the Playlist to get Songs from
     * @return list of Songs in the Playlist
     */
    @GetMapping("/{playlistId}/songs")
    private List<SongDto> getSongsFromPlaylist(@PathVariable long playlistId){
        System.out.println("GET Songs from Playlist " + playlistId);
        return songService.findSongsFromPlaylist(playlistId);
    }

    /**
     * Post a new Playlist to the database
     * @param playlistDto body of the request containing the values of the new Playlist
     * @return ResponseEntity containing the newly posted Playlist in Dtoformat
     */
    @PostMapping
    private ResponseEntity<PlaylistDto> newPlaylist(@RequestBody PlaylistDto playlistDto){
        System.out.println("POST new Playlist");
        return songService.savePlaylist(playlistDto);
    }

    /**
     * Update the Playlist with corresponding Id to include the values included in the body
     * @param playlistDto body of the request containing the new values of the Playlist
     * @return ResponseEntity containing the newly updated Playlist in Dtoformat
     */
    @PutMapping("/{playlistId}")
    private ResponseEntity<PlaylistDto> updatePlaylist(
            @PathVariable long playlistId, @RequestBody PlaylistDto playlistDto){
        System.out.println("PUT Playlist " + playlistId);
        return songService.updatePlaylist(playlistId, playlistDto);
    }

    /**
     * Add a Song to an existing Playlist
     * @param playlistId Id of the Playlist to add the Song to
     * @param songId Id of the Song to add to the Playlist
     * @return ResponseEntity containing the updated Playlist in Dtoformat
     */
    @PutMapping("/{playlistId}/{songId}")
    private ResponseEntity<PlaylistDto> addSongToPlaylist(@PathVariable long playlistId, @PathVariable long songId) {
        System.out.println("PUT Song : " + songId + " to Playlist: " + playlistId);
        return songService.addSongToPlaylist(playlistId, songId);
    }

    /**
     * Set the Playlist with corresponding Id as the new default Playlist
     * @param playlistId Id of the Playlist to make the new default
     * @return ResponseEntity containing the new default Playlist in Dtoformat
     */
    @PutMapping("/{playlistId}/default")
    private ResponseEntity<PlaylistDto> updatePlaylistDefault(@PathVariable long playlistId){
        System.out.println("PUT default Playlist " + playlistId);
        return songService.setDefaultPlaylist(playlistId);
    }

    /**
     * Delete the Playlist with the corresponding Id
     * @param playlistId Id of the Playlist to delete
     * @return ResponseEntity containing the deleted Playlist in DtoFormat
     */
    @DeleteMapping("/{playlistId}")
    private ResponseEntity<PlaylistDto> deletePlaylist(@PathVariable long playlistId){
        System.out.println("DELETE Playlist " + playlistId);
        return songService.deletePlaylist(playlistId);
    }
}
