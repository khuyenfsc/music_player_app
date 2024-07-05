package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;

public class PlaySong implements ActionListener{
    private static MediaPlayer mediaPlayer;
    private Duration timePause;
    private boolean musicPaused = false;
    private JButton playButton = new JButton("Play");



    public void resumeMusic(){
        if(musicPaused){
            mediaPlayer.seek(timePause);
            playMusic();
            musicPaused = false;
        }
    }

    public void pauseMusic(){
        timePause = mediaPlayer.getCurrentTime();
        musicPaused = true;
        mediaPlayer.pause();
    }

    public void playMusic(){
        mediaPlayer.play();

    }

    public JButton playButtonMethod(){
        playButton.addActionListener(this);
        return playButton;
    }



    public PlaySong(String filePath, DiscPanel discPanel, ControlPanel controlPanel) throws Exception{
        JFXPanel jfxPanel = new JFXPanel();

        mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));

        while(mediaPlayer.getStatus() != MediaPlayer.Status.READY){
            Thread.sleep(10);
        }

        controlPanel.setTotalDuration(mediaPlayer.getTotalDuration().toMinutes());

        mediaPlayer.play();
        discPanel.setTimerStart();
        controlPanel.resetDurationBar();
    }

    public PlaySong(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==playButton){
            if(musicPaused){
                System.out.println("continue");
                resumeMusic();
            }else{
                pauseMusic();
            }
        }
    }


}
