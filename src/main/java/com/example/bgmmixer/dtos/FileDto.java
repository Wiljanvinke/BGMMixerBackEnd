package com.example.bgmmixer.dtos;

import com.example.bgmmixer.model.File;

public class FileDto {
    private String fileName;
    private String fileType;
    private long fileSize;

    public FileDto(){

    }

    public FileDto(File file){
        this.fileName = file.getFileName();
        this.fileType = file.getFileType();
        this.fileSize = file.getFileSize();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
