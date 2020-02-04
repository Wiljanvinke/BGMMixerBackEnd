package com.example.bgmmixer.controller;

import com.example.bgmmixer.dtos.FileDto;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.payload.UploadFileResponse;
import com.example.bgmmixer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
public class FileEndpoint {

    @Autowired
    private FileService fileService;

    @GetMapping("/files")
    public Iterable<FileDto> getFiles(){
        return fileService.getAllFiles();
    }

    @GetMapping("/files/{fileId}")
    public FileDto getFileDto(@PathVariable long fileId){
        return fileService.getFileDto(fileId);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId) {
        // Load file from database
        File file = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Received");
        File dbFile = fileService.storeFile(file);
        String fileId = Long.toString(dbFile.getId());
        System.out.println("dbFile.getId(): " + dbFile.getId());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileId)
                .toUriString();
        System.out.println("fileDownloadUri: " + fileDownloadUri);
        return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
