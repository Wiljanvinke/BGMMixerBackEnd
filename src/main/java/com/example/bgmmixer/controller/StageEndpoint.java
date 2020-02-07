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

    @GetMapping("/song/{songId}")
    public Iterable<StageDto> getStages(@PathVariable long songId){
        System.out.println("Get Stages for " + songId);
        return songService.findAllStagesBySongId(songId);
    }

    @GetMapping("/{stageId}")
    public StageDto getStage(@PathVariable long stageId){
        System.out.println("Get Stage for " + stageId);
        return songService.findStageById(stageId);
    }
}
