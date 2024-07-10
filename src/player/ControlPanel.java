package player;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private int checkStreamPlay;//0 - from addButton,, 1 - from playlist//
    private PlaySong playSong;
    private DiscPanel discPanel;
    private DurationBar durationBar = new DurationBar();
    private PauseButton pauseButton = new PauseButton();
    private RewindButton rewindButton = new RewindButton();
    private FastForwardButton fastForwardButton = new FastForwardButton();
    private VolumeButton volumeButton = new VolumeButton();
    private ReplayButton replayButton = new ReplayButton();
    private MainPlayer mainPlayer;
    private JLabel startTime = new JLabel();
    private JLabel stopTime = new JLabel();
    private double totalDuration;
    private int extraSecond;
    private int totalMinute;

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    public void setMainPlayer(MainPlayer mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    void changeStopTime(){
        stopTime.setText(totalMinute + ":" + repeat("0", (extraSecond / 10 == 0)?1:0) + extraSecond);
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
        startTime.setText(currentMinute + ":" + repeat("0", (currentSecond/10 == 0)?1:0) + currentSecond);
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setForeground(Color.white);

        revalidate();
    }

    void setTotalDuration(double totalDuration){
        this.totalDuration = totalDuration;
        this.extraSecond = (int)((totalDuration - Math.floor(totalDuration))*60);
        this.totalMinute = (int)Math.floor(totalDuration);
        changeStopTime();
    }


    void resetDurationBar(){
        this.durationBar.setValue(0);
        this.durationBar.setProgressTime(1);
    }

    public void setCheckStreamPlay(int checkStreamPlay){
        this.checkStreamPlay = checkStreamPlay;
    }

    public void setPlaysong(PlaySong playSong){
        this.playSong = playSong;
    }

    public PlaySong getPlaySong(){
        return this.playSong;
    }

    public DurationBar getDurationBar(){
        return this.durationBar;
    }

    public MainPlayer getMainPlayer(){
        return this.mainPlayer;
    }


    ControlPanel(){
        durationBar.setControlPanel(this);
        pauseButton.setControlPanel(this);
        rewindButton.setControlPanel(this);
        fastForwardButton.setControlPanel(this);
        volumeButton.setControlPanel(this);
        replayButton.setControlPanel(this);

        startTime.setText("00:00");
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setForeground(Color.white);

        stopTime.setText("00:00");
        stopTime.setFont(new Font("Arial", Font.PLAIN, 15));
        stopTime.setForeground(Color.white);


        this.setBackground(null);
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

    }


}
