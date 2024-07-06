package player;

import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;


public class DurationBar extends JSlider implements ActionListener, ChangeListener {
    Timer timer = new Timer(1000, this);
    int progressTime = 1;
    int previousTime = 1;
    int checkReplay = 0;
    ControlPanel controlPanel;

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
            this.controlPanel.mainPlayer.futures.addSong.getPlaySong().seekToSpecificTime(Duration.millis(progressTime));
            this.setValue(progressTime);
        }
    }

    DurationBar(ControlPanel controlPanel){

        this.setValue(0);
        this.addChangeListener(this);
        this.setOrientation(SwingConstants.HORIZONTAL);
        this.setPreferredSize(new Dimension(300, 20));
        this.setBackground(null);

        this.controlPanel = controlPanel;

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

        if(progressTime >= controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond()){
            timer.stop();
            controlPanel.getDiscPanel().setTimerStop();
            replayMedia();
        }else if(!timer.isRunning()){
            timer.start();
            controlPanel.getDiscPanel().setTimerStart();
        }

        if(this.controlPanel.mainPlayer.futures.addSong.getPlaySong() != null && Math.abs(progressTime - previousTime) > 1){
            this.controlPanel.mainPlayer.futures.addSong.getPlaySong().seekToSpecificTime(Duration.millis((this.progressTime * 1000)));
            previousTime = progressTime;
            controlPanel.pauseButton.setIcon(new ImageIcon(".\\src\\images\\pause.png"));
            controlPanel.pauseButton.checkPause = 0;
        }

    }
}


