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
    ControlPanel controlPanel;



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

    void fastForward(){
        mediaPlayer.seek(Duration.millis(mediaPlayer.getCurrentTime().toMillis() + 10000));
        mediaPlayer.play();
    }

    void reWind(){
        if(mediaPlayer.getCurrentTime().toMillis() > 10000){
            mediaPlayer.seek(Duration.millis(mediaPlayer.getCurrentTime().toMillis() - 10000));
            mediaPlayer.play();
        }else{
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.play();
        }
    }

    void seekToSpecificTime(Duration specificTime){
        mediaPlayer.seek(specificTime);
        mediaPlayer.play();
    }

    void setIconForPauseButton(){
        controlPanel.pauseButton.setIcon(new ImageIcon(".\\src\\images\\pause.png"));;
    }

    void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

    int getVolume(){
        return (int)mediaPlayer.getVolume();
    }

    public PlaySong(String filePath, DiscPanel discPanel, ControlPanel controlPanel) throws Exception{
        JFXPanel jfxPanel = new JFXPanel();
        // Stop the song is playing
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }

        mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));

        while(mediaPlayer.getStatus() != MediaPlayer.Status.READY){
            Thread.sleep(10);
        }

        controlPanel.setTotalDuration(mediaPlayer.getTotalDuration().toMinutes());
        System.out.println(Duration.millis(1000*mediaPlayer.getTotalDuration().toSeconds()));

        mediaPlayer.play();
        this.controlPanel = controlPanel;

        setIconForPauseButton();

        discPanel.setTimerStart();
        controlPanel.resetDurationBar();
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
