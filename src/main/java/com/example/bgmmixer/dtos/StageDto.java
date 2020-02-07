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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }
}
