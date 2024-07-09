package player;

import features.SongButton;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class DurationBar extends JSlider implements ActionListener, ChangeListener {
    private Timer timer = new Timer(1000, this);
    private int progressTime = 1;
    private int previousTime = 1;
    private int checkReplay = 0;
    private int ordinalNum;
    private int isPlaylist;
    private ArrayList<SongButton> songs;
    private ControlPanel controlPanel;

    public int getProgressTime(){
        return this.progressTime;
    }

    public void setProgressTime(int progressTime) {
        this.progressTime = progressTime;
    }

    public int getCheckReplay() {
        return checkReplay;
    }

    public void setCheckReplay(int checkReplay) {
        this.checkReplay = checkReplay;
    }

    public int getIsPlaylist(){
        return this.isPlaylist;
    }

    public void setIsPlaylist(int isPlaylist) {
        this.isPlaylist = isPlaylist;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    void startCount(){
        timer.start();
    }

    void stopCount(){
        timer.stop();
    }

    public void setMaxTimer(){
        this.setMaximum(controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond());
    }

    void increaseCurrentTime(){
        if(progressTime + 10 > controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond()){
            progressTime = controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond();
            previousTime = progressTime;
            this.setValue(progressTime);
        }else{
            progressTime += 10;
            previousTime = progressTime;
            this.setValue(progressTime);        }

    }

    void decreaseCurrentTime(){
        if(previousTime > 10){
            progressTime -= 10;
            previousTime = progressTime;
            this.setValue(progressTime);
        }else{
            progressTime = 0;
            previousTime = 0;
            this.setValue(progressTime);
        }

    }

    void replayMedia(){
        if(checkReplay == 1){
            progressTime = 0;
            this.controlPanel.getPlaySong().seekToSpecificTime(Duration.millis(progressTime));
            this.setValue(progressTime);
        }
    }

    public void setSongs(ArrayList<SongButton> songs){
        this.songs = songs;
    }

    public void setOrdinalNum(int ordinalNum){
        this.ordinalNum = ordinalNum;
    }

    void nextMedia(){
        if(isPlaylist == 1){
            ordinalNum++;

            progressTime = 0;
            this.setValue(progressTime);

            songs.get(ordinalNum).doClick();
            songs.get(ordinalNum - 1).setBackground(null);
        }
    }


    DurationBar(){

        this.setValue(0);
        this.addChangeListener(this);
        this.setOrientation(SwingConstants.HORIZONTAL);
        this.setPreferredSize(new Dimension(300, 20));
        this.setBackground(null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.setValue(progressTime);
        progressTime++;
        previousTime++;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        controlPanel.changeCurrentTime(progressTime % 60, progressTime / 60);
        progressTime = this.getValue();

        if(progressTime == controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond()){
            timer.stop();
            controlPanel.getDiscPanel().setTimerStop();
            replayMedia();

            if(ordinalNum < songs.size() - 1){
                nextMedia();
            }

        }else if(!timer.isRunning()){
            timer.start();
            controlPanel.getDiscPanel().setTimerStart();
        }

        if(this.controlPanel.getPlaySong() != null && Math.abs(progressTime - previousTime) > 1){
            this.controlPanel.getPlaySong().seekToSpecificTime(Duration.millis((this.progressTime * 1000)));
            previousTime = progressTime;
            controlPanel.getPauseButton().setIcon(new ImageIcon(".\\src\\images\\pause.png"));
            controlPanel.getPauseButton().setCheckPause(0);
        }

    }
}


