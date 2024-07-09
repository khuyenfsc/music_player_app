package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButton extends JButton implements ActionListener {
    private ControlPanel controlPanel;

    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    ReplayButton(){
        this.setBackground(null);
        this.setBorder(null);
        this.addActionListener(this);
        this.setIcon(new ImageIcon(".\\src\\images\\replay.png"));
        this.setPreferredSize(new Dimension(32, 32));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.controlPanel.getDurationBar().getCheckReplay() == 0){
            this.controlPanel.getDurationBar().setCheckReplay(1);
            this.controlPanel.getDurationBar().setIsPlaylist(0);
            this.setBackground(Color.lightGray);
        }else{
            this.controlPanel.getDurationBar().setCheckReplay(0);
            this.controlPanel.getDurationBar().setIsPlaylist(1);
            this.setBackground(null);
        }
    }
}
