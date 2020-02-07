package com.example.bgmmixer.model;

import com.example.bgmmixer.dtos.SongDto;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double duration;
    @ManyToOne
    private File file;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER)
    private List<Stage> stages;

    @Lob
    private String description;

    public Song(){

    }

    public Song(String name, File file) throws InterruptedException {
        this.name = name;
        this.file = file;
        setLengthByFile();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) throws InterruptedException {
        setLengthByFile();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setLengthByFile() throws InterruptedException {
        new JFXPanel();
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(Long.toString(file.getId()))
                .toUriString();
        System.out.println(fileUri);
        Media media = new Media(fileUri);
        //Media media = new Media("file:/C:/_FILES/HomeProject/BGMMixerBackend/test.mp3");

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(() -> {

            System.out.println("Duration: " + media.getDuration().toMillis());
            duration = media.getDuration().toMillis();
            // display media's metadata
            for (Map.Entry<String, Object> entry : media.getMetadata().entrySet()){
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            synchronized (this){
                this.notify();
            }

        });
        synchronized (this){
            wait(10000);
        }
    }

    public boolean addStage(Stage stage){
        return stages.add(stage);
    }
}
