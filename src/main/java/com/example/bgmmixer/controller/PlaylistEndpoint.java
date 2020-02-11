package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.PlaylistDto;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
