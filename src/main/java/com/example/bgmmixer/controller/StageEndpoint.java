package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.StageDto;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/stages")
public class StageEndpoint {

    @Autowired
    private SongService songService;

    @GetMapping("/{songId}")
    public StageDto getStages(@PathVariable long songId){
        System.out.println("Get Stages for " + songId);
        return songService.findAllStagesBySongId(songId);
    }
}
