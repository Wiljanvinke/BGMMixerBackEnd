package com.example.bgmmixer.dtos;

import com.example.bgmmixer.model.Stage;

public class StageDto {

    private long id;
    private String name;
    private double startTime;
    private double endTime;
    private long songId;

    public StageDto(Stage stage) {
        this.id = stage.getId();
        this.name = stage.getName();
        this.startTime = stage.getStartTime();
        this.endTime = stage.getEndTime();
        this.songId = stage.getSong().getId();
    }
}
