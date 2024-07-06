package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FastForwardButton extends JButton implements ActionListener {
    ControlPanel controlPanel;

    FastForwardButton(ControlPanel controlPanel){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setBorder(null);
        this.addActionListener(this);
        this.setIcon(new ImageIcon(".\\src\\images\\fast_forward.png"));

        this.controlPanel = controlPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controlPanel.mainPlayer.futures.addSong.getPlaySong().fastForward();
        this.controlPanel.durationBar.increaseCurrentTime();
    }
}
