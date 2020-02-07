package com.example.bgmmixer.dtos;

import com.example.bgmmixer.utils.MimeTypes;
import com.example.bgmmixer.model.File;
import org.springframework.http.MediaType;

public class FileDto {
    private String fileName;
    private String fileType;
    private long fileSize;

    public FileDto(){

    }

    public FileDto(File file){
        this.fileName = file.getFileName();
        this.fileType = MimeTypes.getDefaultExt(MediaType.parseMediaType(file.getFileType()).toString());
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
