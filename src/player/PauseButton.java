package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {
    ImageIcon pauseIcon = new ImageIcon(".\\src\\images\\play.png");
    int checkPause = 0;
    ControlPanel controlPanel;

    PauseButton(ControlPanel controlPanel){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setIcon(pauseIcon);
        this.setBorder(null);
        this.addActionListener(this);
        this.controlPanel = controlPanel;
//        this.controlPanel.mainPlayer.futures.addSong.getPlaySong().setPauseButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkPause == 1){
            this.setIcon(new ImageIcon(".\\src\\images\\pause.png"));
            checkPause = 0;
            this.controlPanel.mainPlayer.futures.addSong.getPlaySong().playMusic();
            this.controlPanel.mainPlayer.discPanel.setTimerStart();
            this.controlPanel.durationBar.startCount();
        }else{
            this.setIcon(new ImageIcon(".\\src\\images\\play.png"));
            checkPause = 1;
            this.controlPanel.mainPlayer.futures.addSong.getPlaySong().pauseMusic();
            this.controlPanel.mainPlayer.discPanel.setTimerStop();
            this.controlPanel.durationBar.stopCount();
        }
    }
}
