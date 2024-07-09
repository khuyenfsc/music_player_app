package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RewindButton extends JButton implements ActionListener {
    private ControlPanel controlPanel;

    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    RewindButton(){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setIcon(new ImageIcon(".\\src\\images\\left_forward.png"));
        this.addActionListener(this);
        this.setBorder(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controlPanel.getPlaySong().reWind();
        this.controlPanel.getDurationBar().decreaseCurrentTime();
        this.controlPanel.getDiscPanel().setTimerStart();
        this.controlPanel.getPauseButton().setIcon(new ImageIcon(".\\src\\images\\pause.png"));
        this.controlPanel.getPauseButton().setCheckPause(0);
    }
}
