package player;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    int checkStreamPlay;//0 - from addButton,, 1 - from playlist//
    PlaySong playSong;
    DiscPanel discPanel;
    public DurationBar durationBar = new DurationBar(this);
    PauseButton pauseButton = new PauseButton(this);
    RewindButton rewindButton = new RewindButton(this);
    FastForwardButton fastForwardButton = new FastForwardButton(this);
    VolumeButton volumeButton = new VolumeButton(this);
    ReplayButton replayButton = new ReplayButton(this);
    MainPlayer mainPlayer;
    JLabel startTime = new JLabel();
    JLabel stopTime = new JLabel();
    double totalDuration;
    int extraSecond;
    int totalMinute;

    void changeStopTime(){
        stopTime.setText(totalMinute + ":" + "0".repeat((extraSecond / 10 == 0)?1:0) + extraSecond);
        stopTime.setFont(new Font("Arial", Font.PLAIN, 15));
        stopTime.setForeground(Color.white);

        durationBar.startCount();
        durationBar.setMaxTimer();

        revalidate();


    }

    DiscPanel getDiscPanel(){
        return this.discPanel;
    }

    int getTotalMinute(){
        return this.totalMinute;
    }
    int getExtraSecond(){
        return this.extraSecond;
    }

    PauseButton getPauseButton(){
        return this.pauseButton;
    }

    void changeCurrentTime(int currentSecond, int currentMinute){
        startTime.setText(currentMinute + ":" + "0".repeat((currentSecond/10 == 0)?1:0) + currentSecond);
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setForeground(Color.white);

        revalidate();
    }

    void setTotalDuration(double totalDuration){
        this.totalDuration = totalDuration;
        this.extraSecond = (int)((totalDuration - Math.floor(totalDuration))*60);
        this.totalMinute = (int)Math.floor(totalDuration);
        System.out.println(extraSecond);
        changeStopTime();
    }


    void resetDurationBar(){
        this.durationBar.setValue(0);
        this.durationBar.progressTime = 1;
    }

    public void setCheckStreamPlay(int checkStreamPlay){
        this.checkStreamPlay = checkStreamPlay;
    }

    public void setPlaysong(PlaySong playSong){
        this.playSong = playSong;
    }

    ControlPanel(DiscPanel discPanel, MainPlayer mainPlayer){
        startTime.setText("00:00");
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setForeground(Color.white);

        stopTime.setText("00:00");
        stopTime.setFont(new Font("Arial", Font.PLAIN, 15));
        stopTime.setForeground(Color.white);


        this.setBackground(null);
        System.out.println(durationBar.getX() + " " + durationBar.getY());
        System.out.println(durationBar.getWidth() + " " + durationBar.getHeight());
        this.setBounds(0, 420, 450, 180);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.add(startTime);
        this.add(durationBar);
        this.add(stopTime);
        this.add(volumeButton);
        this.add(rewindButton);
        this.add(pauseButton);
        this.add(fastForwardButton);
        this.add(replayButton);

        this.discPanel = discPanel;
        this.mainPlayer = mainPlayer;
    }


}
