package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeButton extends JButton implements ActionListener {
    private ControlPanel controlPanel;

    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    VolumeButton(){
        this.setBackground(null);
        this.setIcon(new ImageIcon(".\\src\\images\\volume.png"));
        this.setBorder(null);
        this.addActionListener(this);
        this.setPreferredSize(new Dimension(32, 32));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(controlPanel.getMainPlayer().getVolumeBar().getCheckVisible() == 0){
            this.controlPanel.getMainPlayer().getVolumeBar().getPanel().setVisible(true);
            this.controlPanel.getMainPlayer().getVolumeBar().setCheckVisible(1);
        }else{
            this.controlPanel.getMainPlayer().getVolumeBar().getPanel().setVisible(false);
            this.controlPanel.getMainPlayer().getVolumeBar().setCheckVisible(0);
        }
    }
}
