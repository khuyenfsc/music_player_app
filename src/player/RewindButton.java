package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RewindButton extends JButton implements ActionListener {
    ControlPanel controlPanel;

    RewindButton(ControlPanel controlPanel){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setIcon(new ImageIcon(".\\src\\images\\left_forward.png"));
        this.addActionListener(this);
        this.setBorder(null);

        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controlPanel.playSong.reWind();
        this.controlPanel.durationBar.decreaseCurrentTime();
        this.controlPanel.getDiscPanel().setTimerStart();
        this.controlPanel.pauseButton.setIcon(new ImageIcon(".\\src\\images\\pause.png"));
        this.controlPanel.pauseButton.checkPause = 0;
    }
}
