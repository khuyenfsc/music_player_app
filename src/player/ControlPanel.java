package player;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    DurationBar durationBar = new DurationBar(this);
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

    int getTotalMinute(){
        return this.totalMinute;
    }
    int getExtraSecond(){
        return this.extraSecond;
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
    }

    ControlPanel(){
        startTime.setText("00:00");
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setForeground(Color.white);

        stopTime.setText("00:00");
        stopTime.setFont(new Font("Arial", Font.PLAIN, 15));
        stopTime.setForeground(Color.white);

        this.setBackground(null);
        System.out.println(durationBar.getX() + " " + durationBar.getY());
        System.out.println(durationBar.getWidth() + " " + durationBar.getHeight());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        this.add(startTime);
        this.add(durationBar);
        this.add(stopTime);
    }

    GridBagConstraints setControlPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 30;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        return gbc;
    }


}
