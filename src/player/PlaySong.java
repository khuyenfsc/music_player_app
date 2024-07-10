package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    private String filePath;

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        PlaySong.mediaPlayer = mediaPlayer;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

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
        controlPanel.getPauseButton().setIcon(new ImageIcon(".\\src\\images\\pause.png"));;
    }

    void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

    int getVolume(){
        return (int)mediaPlayer.getVolume();
    }

    public void stopPreviousSong(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

    public void setTotalTime() throws InterruptedException {
        mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));

        while(mediaPlayer.getStatus() != MediaPlayer.Status.READY){
            Thread.sleep(10);
        }

        controlPanel.setTotalDuration(mediaPlayer.getTotalDuration().toMinutes());
    }

    public void playTheSong(){
        mediaPlayer.play();

        setIconForPauseButton();

        discPanel.setTimerStart();
        controlPanel.resetDurationBar();
    }

    public PlaySong() throws InterruptedException {
        JFXPanel jfxPanel = new JFXPanel();
        // Stop the song is playing
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==playButton){
            if(musicPaused){
                resumeMusic();
            }else{
                pauseMusic();
            }
        }
    }


}
