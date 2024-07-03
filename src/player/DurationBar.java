package player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DurationBar extends JSlider implements ActionListener, ChangeListener {
    JLabel timeLabel = new JLabel();
    Timer timer = new Timer(1000, this);
    int progressTime = 1;
    ControlPanel controlPanel;
    int currenMinute = 0;

    void startCount(){
        timer.start();
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
        if(progressTime == 60){
            currenMinute++;
            progressTime = 0;
        }

        if(progressTime + 60 * currenMinute >= controlPanel.getTotalMinute()*60 + controlPanel.getExtraSecond()){
            timer.stop();
        }
        this.setValue(progressTime);
        progressTime++;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        controlPanel.changeCurrentTime(this.getValue(), currenMinute);

    }

}
