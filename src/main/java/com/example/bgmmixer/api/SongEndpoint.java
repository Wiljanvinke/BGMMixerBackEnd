package com.example.bgmmixer.api;

import com.example.bgmmixer.dtos.SongDto;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.payload.UploadFileResponse;
import com.example.bgmmixer.service.FileService;
import com.example.bgmmixer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
public class SongEndpoint {

    @Autowired
    private SongService songService;
    @Autowired
    private FileService fileService;

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId) {
        // Load file from database
        File file = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping("/songs/{songId}")
    public Song getSong(@PathVariable long songId){
        return songService.findSongById(songId);
    }

    @GetMapping("/songs")
    public Iterable<Song> getSongs(){
        return songService.findAllSongs();
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Received");
        File dbFile = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(Long.toString(dbFile.getId()))
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/songs")
    public Song newSong(@RequestBody Song newSong){
        return songService.saveSong(newSong);
    }

    @PostMapping("/songs/{fileID}")
    public Song newSong(@RequestBody Song newSong, @PathVariable long fileID){
        return songService.saveSong(newSong, fileID);
    }

    @PutMapping("/songs/{songID}")
    public SongDto updateSong(@RequestBody SongDto songDto, @PathVariable long songID){
        return songService.updateSong(songDto, songID);
    }

    @DeleteMapping("/songs/{songID}")
    public void deleteSong(@PathVariable long songID){
        songService.deleteSong(songID);
    }


}
