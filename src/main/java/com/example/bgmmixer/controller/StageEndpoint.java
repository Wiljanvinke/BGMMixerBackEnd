package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.StageDto;
import com.example.bgmmixer.model.Stage;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/song/{songId}")
    public ResponseEntity<StageDto> postStageToSong(@PathVariable long songId, @RequestBody Stage stage){
        System.out.println("Post new Stage to Song " + songId);
        return songService.saveStage(stage, songId);
    }

    @PutMapping("/{stageId}")
    public ResponseEntity<StageDto> updateStage(@PathVariable long stageId, @RequestBody StageDto stageDto){
        System.out.println("Put Stage " + stageId);
        return songService.updateStage(stageDto, stageId);
    }

    @DeleteMapping("/{stageId}")
    public ResponseEntity<StageDto> deleteStage(@PathVariable long stageId){
        System.out.println("Delete Stage " + stageId);
        return songService.deleteStage(stageId);
    }

    @DeleteMapping("/song/{songId}")
    public ResponseEntity<Iterable<StageDto>> deleteAllStagesFromSong(@PathVariable long songId){
        System.out.println("Delete all Stages from " + songId);
        return songService.deleteAllFromSong(songId);
    }
}
