package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.PlaylistDto;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return songService.findAllPlaylists();
    }

    @GetMapping("/{playlistId}")
    private PlaylistDto getPlaylist(@PathVariable long playlistId){
        return songService.findPlaylistById(playlistId);
    }
}
