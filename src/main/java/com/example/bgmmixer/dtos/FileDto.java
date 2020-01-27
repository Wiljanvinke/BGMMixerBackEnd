package com.example.bgmmixer.dtos;

public class FileDto {
    private String fileName;
    private String fileType;
    private long fileSize;

    public FileDto(){

    }

    public FileDto(String fileName, String fileType, long fileSize){
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}
