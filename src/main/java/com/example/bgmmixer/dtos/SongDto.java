package com.example.bgmmixer.dtos;

import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.model.Stage;

public class SongDto {
    private long id;
    private String name;
    private double duration;
    private long fileId;
    private long[] stageIds;

    public SongDto(){}

    public SongDto(Song song){
        this.id = song.getId();
        this.name = song.getName();
        this.duration = song.getDuration();
        this.fileId = song.getFile().getId();
        stageIds = new long[song.getStages().size()];
        for(int i = 0; i < song.getStages().size(); i++){
            stageIds[i] = song.getStages().get(i).getId();
        }
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long[] getStageIds() {
        return stageIds;
    }

    public void setStageIds(long[] stageIds) {
        this.stageIds = stageIds;
    }
}
