package com.example.bgmmixer.model;

import com.example.bgmmixer.dtos.StageDto;

import javax.persistence.*;

@Entity
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double startTime;
    private double endTime;

    @ManyToOne
    private Song song;

    public Stage(){}

    public Stage(StageDto stageDto){
        this.name = stageDto.getName();
        this.startTime = stageDto.getStartTime();
        this.endTime = stageDto.getEndTime();
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

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
