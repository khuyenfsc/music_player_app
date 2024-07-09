package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FastForwardButton extends JButton implements ActionListener {
    private ControlPanel controlPanel;

    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    FastForwardButton(){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setBorder(null);
        this.addActionListener(this);
        this.setIcon(new ImageIcon(".\\src\\images\\fast_forward.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controlPanel.getPlaySong().fastForward();
        this.controlPanel.getDurationBar().increaseCurrentTime();
        this.controlPanel.getDiscPanel().setTimerStart();
        this.controlPanel.getPauseButton().setIcon(new ImageIcon(".\\src\\images\\pause.png"));
        this.controlPanel.getPauseButton().setCheckPause(0);
    }
}
