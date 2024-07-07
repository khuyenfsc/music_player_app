package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButton extends JButton implements ActionListener {
    ControlPanel controlPanel;

    ReplayButton(ControlPanel controlPanel){
        this.setBackground(null);
        this.setBorder(null);
        this.addActionListener(this);
        this.setIcon(new ImageIcon(".\\src\\images\\replay.png"));
        this.setPreferredSize(new Dimension(32, 32));

        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.controlPanel.durationBar.checkReplay == 0){
            this.controlPanel.durationBar.checkReplay = 1;
            this.controlPanel.durationBar.isPlaylist = 0;
            this.setBackground(Color.lightGray);
        }else{
            this.controlPanel.durationBar.checkReplay = 0;
            this.controlPanel.durationBar.isPlaylist = 1;
            this.setBackground(null);
        }
    }
}
