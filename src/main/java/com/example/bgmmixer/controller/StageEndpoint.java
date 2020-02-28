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

    /**
     * Get all Stages of a Song with corresponding Id in Dtoformat
     * @param songId Id of Song to get Stages of
     * @return Iterable of Stages in Dtoformat
     */
    @GetMapping("/song/{songId}")
    public Iterable<StageDto> getStages(@PathVariable long songId){
        System.out.println("Get Stages for " + songId);
        return songService.findAllStagesBySongId(songId);
    }

    /**
     * Get a single Stage with corresponding Id in Dtoformat
     * @param stageId Id to match a Stage to
     * @return Stage in Dtoformat
     */
    @GetMapping("/{stageId}")
    public StageDto getStage(@PathVariable long stageId){
        System.out.println("Get Stage for " + stageId);
        return songService.findStageById(stageId);
    }

    /**
     * Post a new Stage to the Song with corresponding Id
     * @param songId Id of Song to post new Stage to
     * @param stage body containing values of new Stage
     * @return ResponseEntity containing new Stage
     */
    @PostMapping("/song/{songId}")
    public ResponseEntity<StageDto> postStageToSong(@PathVariable long songId, @RequestBody Stage stage){
        System.out.println("Post new Stage to Song " + songId);
        return songService.saveStage(stage, songId);
    }

    /**
     * Updates the Stage with corresponding Id
     * @param stageId Id of the Stage to be changed
     * @param stageDto body containing the new values of the Stage
     * @return ResponseEntity containing new Stage values
     */
    @PutMapping("/{stageId}")
    public ResponseEntity<StageDto> updateStage(@PathVariable long stageId, @RequestBody StageDto stageDto){
        System.out.println("Put Stage " + stageId);
        return songService.updateStage(stageDto, stageId);
    }

    /**
     * Delete the Stage with the corresponding Id
     * @param stageId Id of the Stage to delete
     * @return ResponseEntity containing the deleted Stage in DtoFormat
     */
    @DeleteMapping("/{stageId}")
    public ResponseEntity<StageDto> deleteStage(@PathVariable long stageId){
        System.out.println("Delete Stage " + stageId);
        return songService.deleteStage(stageId);
    }

    /**
     * Delete all Stages from the Song with corresponding Id
     * @param songId Id of Song to delete Stages from
     * @return ResponseEntity containing an Iterable of all deleted Stages in Dtoformat
     */
    @DeleteMapping("/song/{songId}")
    public ResponseEntity<Iterable<StageDto>> deleteAllStagesFromSong(@PathVariable long songId){
        System.out.println("Delete all Stages from " + songId);
        return songService.deleteAllFromSong(songId);
    }
}
