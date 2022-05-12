package com.example.demo8;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.util.concurrent.Callable;

public class Controller {

    @FXML
    private MediaView mediaView;
    private MediaPlayer player;
    @FXML
    private Slider vol;
    @FXML
    private Label lblTime;


    @FXML
    private Label total;



    @FXML
    public void initialize() {
        String video = getClass().getResource("Chance-The-Rapper-Somewhere-In-Paradise-ft-Jeremih-R-Kelly.mp4").toExternalForm();
        Media media = new Media(video);
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        vol.setValue(player.getVolume() * 100);
        vol.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(vol.getValue() / 100);
            }
        });

        bindTime();
    }
    public void bindTime(){
        lblTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(player.getCurrentTime()) + "  ";
            }
        }, player.currentTimeProperty()));
    }
    public String getTime(Duration time){
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if ( seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;

        if (hours > 0) return String.format("%d:%02d:%02d", hours, minutes,seconds);

        else return String.format("%02d:%02d", minutes, seconds);
    }


    @FXML
    void playVideo(MouseEvent event) {
        player.play();
    }

    @FXML
    void stopVideo(MouseEvent event) {
        player.stop();
    }

    @FXML
    void pauseVideo(MouseEvent event) {
        player.pause();
    }

}


