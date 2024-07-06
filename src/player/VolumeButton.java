package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeButton extends JButton implements ActionListener {
    ControlPanel controlPanel;

    VolumeButton(ControlPanel controlPanel){
        this.setBackground(null);
        this.setIcon(new ImageIcon(".\\src\\images\\volume.png"));
        this.setBorder(null);
        this.addActionListener(this);
        this.setPreferredSize(new Dimension(32, 32));

        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(controlPanel.mainPlayer.volumeBar.checkVisible == 0){
            this.controlPanel.mainPlayer.volumeBar.panel.setVisible(true);
            this.controlPanel.mainPlayer.volumeBar.checkVisible = 1;
        }else{
            this.controlPanel.mainPlayer.volumeBar.panel.setVisible(false);
            this.controlPanel.mainPlayer.volumeBar.checkVisible = 0;
        }
    }
}
