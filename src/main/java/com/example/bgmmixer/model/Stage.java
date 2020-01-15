package com.example.bgmmixer.model;

import javax.persistence.*;

@Entity
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int startTime;
    private int endTime;

    @ManyToOne
    private Song song;
}
