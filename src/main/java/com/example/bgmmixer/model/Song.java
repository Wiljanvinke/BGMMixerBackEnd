package com.example.bgmmixer.model;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int length;
    private File file;

    @OneToMany(mappedBy = "song")
    private List<Stage> stages;

}
