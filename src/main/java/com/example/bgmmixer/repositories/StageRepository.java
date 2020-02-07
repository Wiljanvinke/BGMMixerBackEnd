package com.example.bgmmixer.repositories;

import com.example.bgmmixer.model.Stage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StageRepository extends CrudRepository<Stage, Long> {
}
