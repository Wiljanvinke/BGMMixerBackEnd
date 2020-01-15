package com.example.bgmmixer.repositories;

import com.example.bgmmixer.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SongRepository extends CrudRepository<Song, Long> {

}
