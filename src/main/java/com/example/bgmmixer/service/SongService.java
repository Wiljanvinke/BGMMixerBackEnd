package com.example.bgmmixer.service;

import com.example.bgmmixer.dtos.PlaylistDto;
import com.example.bgmmixer.dtos.StageDto;
import com.example.bgmmixer.exceptions.PlaylistNotFoundException;
import com.example.bgmmixer.exceptions.StageNotFoundException;
import com.example.bgmmixer.model.Playlist;
import com.example.bgmmixer.model.Stage;
import com.example.bgmmixer.repositories.PlaylistRepository;
import com.example.bgmmixer.repositories.StageRepository;
import com.example.bgmmixer.utils.MyUtils;
import com.example.bgmmixer.dtos.SongDto;
import com.example.bgmmixer.exceptions.MyFileNotFoundException;
import com.example.bgmmixer.exceptions.SongNotFoundException;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.model.Song;
import com.example.bgmmixer.repositories.FileRepository;
import com.example.bgmmixer.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

    public Song addSong(Song song){
        return songRepository.save(song);
    }

    public SongDto findSongById(long id){
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + id));
        return new SongDto(song);
    }

    public Iterable<SongDto> findAllSongs(){
        List<SongDto> songDtos = new ArrayList<>();
        songRepository.findAll().forEach(song -> songDtos.add(new SongDto(song)));
        return songDtos;

    }

    public Song saveSong(Song song){
        return songRepository.save(song);
    }

    public ResponseEntity<SongDto> saveSong(Song song, long fileId){
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id: " + fileId));
        Song newSong = new Song(song.getName(), file);
        return ResponseEntity.ok(new SongDto(songRepository.save(newSong)));
    }

    public ResponseEntity<SongDto> updateSong(SongDto newSongDto, long songId){
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        System.out.println("SongId: " + newSongDto.getId());
        File file = fileRepository.findById(newSongDto.getFileId())
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id: " + newSongDto.getFileId()));
        MyUtils.copyProperties(newSongDto, song);
        song.setFile(file);
        return ResponseEntity.ok(new SongDto(songRepository.save(song)));
    }

    public void deleteSong(long songId){
        songRepository.deleteById(songId);
    }

    public Iterable<StageDto> findAllStagesBySongId(long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        List<StageDto> stageDtos = new ArrayList<>();
        for(Stage stage: song.getStages()){
            stageDtos.add(new StageDto(stage));
        }
        return stageDtos;
    }

    public StageDto findStageById(long stageId) {
        return new StageDto(stageRepository.findById(stageId)
                .orElseThrow(() -> new StageNotFoundException("Stage not found with id: " + stageId)));
    }

    public ResponseEntity<StageDto> saveStage(Stage stage, long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        if(song.addStage(stage)){
            songRepository.save(song);
            stage.setSong(song);
            return ResponseEntity.ok(new StageDto(stageRepository.save(stage)));
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<StageDto> updateStage(Stage newStage, long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new StageNotFoundException("Stage not found with id: " + stageId));
        MyUtils.copyProperties(newStage, stage);
        return ResponseEntity.ok(new StageDto(stageRepository.save(stage)));
    }

    public ResponseEntity<StageDto> deleteStage(long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new StageNotFoundException("Stage not found with id: " + stageId));
        stageRepository.deleteById(stageId);
        return ResponseEntity.ok(new StageDto(stage));
    }

    public ResponseEntity<Iterable<StageDto>> deleteAllFromSong(long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        List<StageDto> stageDtos = new ArrayList<>();
        for (Stage stage: song.getStages()) {
            stageDtos.add(new StageDto(stage));
        }
        stageRepository.deleteAll(song.getStages());
        return ResponseEntity.ok(stageDtos);
    }

    public List<PlaylistDto> findAllPlaylists(){
        List<PlaylistDto> playlists = new ArrayList<>();
        playlistRepository.findAll().forEach(playlist -> playlists.add(new PlaylistDto(playlist)));
        return playlists;
    }

    public PlaylistDto findPlaylistById(long playlistId){
        return new PlaylistDto(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist not found with id: " + playlistId)));
    }
}