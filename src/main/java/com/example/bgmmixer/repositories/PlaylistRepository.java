package com.example.bgmmixer.repositories;

import com.example.bgmmixer.model.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
}
