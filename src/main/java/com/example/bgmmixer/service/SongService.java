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

    public ResponseEntity<StageDto> updateStage(StageDto newStage, long stageId) {
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
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId)));
    }

    public ResponseEntity<PlaylistDto> savePlaylist(PlaylistDto playlistDto) {
        Playlist playlist = new Playlist(playlistDto);
        for(int i = 0; i < playlistDto.getSongIds().length; i++) {
            playlist.addSong(songRepository.findById(playlistDto.getSongIds()[i])
                    .orElseThrow(() -> new PlaylistNotFoundException("Playlist not found with id")));
        }
        return ResponseEntity.ok(new PlaylistDto(playlistRepository.save(playlist)));
    }

    public ResponseEntity<PlaylistDto> updatePlaylist(long playlistId, PlaylistDto playlistDto) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        if(playlistDto.getName() != null) {
            playlist.setName(playlistDto.getName());
        }
        List<Song> songs = playlist.getSongs();
        List<Long> songIds = new ArrayList<>();
        if (playlistDto.getSongIds() != null) {
            for (Song song : songs) {
                songIds.add(song.getId());
            }
            List<Long> newSongIds = new ArrayList<>();
            for (int i = 0; i < playlistDto.getSongIds().length; i++) {
                newSongIds.add(playlistDto.getSongIds()[i]);
            }
            //Add new Songs
            for (Long newSongId : newSongIds) {
                if (!songIds.contains(newSongId)) {
                    songIds.add(newSongId);
                    songs.add(songRepository.findById(newSongId)
                            .orElseThrow(() -> new SongNotFoundException(newSongId)));
                }
            }
            //Remove old Songs
            for (Long songId : songIds) {
                if (!newSongIds.contains(songId)) {
                    songs.remove(songRepository.findById(songId)
                            .orElseThrow(() -> new SongNotFoundException(songId)));

                }
            }
        }
        return ResponseEntity.ok(new PlaylistDto(playlistRepository.save(playlist)));
    }

    public ResponseEntity<PlaylistDto> deletePlaylist(long playlistId) {
        PlaylistDto playlistDto = new PlaylistDto(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId)));
        playlistRepository.deleteById(playlistId);
        return ResponseEntity.ok(playlistDto);
    }

    public List<SongDto> findSongsFromPlaylist(long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        List<Long> songsIds = new ArrayList<>();
        for(Song song: playlist.getSongs()){
            songsIds.add(song.getId());
        }
        List<SongDto> songDtos = new ArrayList<>();
                songRepository.findAllById(songsIds).forEach(song -> songDtos.add(new SongDto(song)));
        return songDtos;
    }

    public ResponseEntity<PlaylistDto> findDefaultPlaylist() {
        for (Playlist playlist : playlistRepository.findAll()) {
            if (playlist.isDefault()) {
                return ResponseEntity.ok(new PlaylistDto(playlist));
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<PlaylistDto> setDefaultPlaylist(long playlistId) {
        for (Playlist playlist : playlistRepository.findAll()) {
            if (playlist.isDefault()) {
                playlist.setDefault(false);
            }
        }
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        playlist.setDefault(true);
        return ResponseEntity.ok(new PlaylistDto(playlist));
    }

    public ResponseEntity<PlaylistDto> addSongToPlaylist(long playlistId, long songId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        playlist.addSong(songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId)));
        return ResponseEntity.ok(new PlaylistDto(playlistRepository.save(playlist)));
    }
}