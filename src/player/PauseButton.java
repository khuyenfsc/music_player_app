package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {
    private ImageIcon pauseIcon = new ImageIcon(".\\src\\images\\play.png");
    private int checkPause = 0;
    private ControlPanel controlPanel;

    public void setCheckPause(int checkPause) {
        this.checkPause = checkPause;
    }

    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    PauseButton(){
        this.setBackground(null);
        this.setPreferredSize(new Dimension(64, 64));
        this.setIcon(pauseIcon);
        this.setBorder(null);
        this.addActionListener(this);
//        this.controlPanel.mainPlayer.futures.addSong.getPlaySong().setPauseButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkPause == 1){
            this.setIcon(new ImageIcon(".\\src\\images\\pause.png"));
            checkPause = 0;

            this.controlPanel.getPlaySong().playMusic();

            this.controlPanel.getMainPlayer().getDiscPanel().setTimerStart();
            this.controlPanel.getDurationBar().startCount();
        }else{
            this.setIcon(new ImageIcon(".\\src\\images\\play.png"));
            checkPause = 1;

            this.controlPanel.getPlaySong().pauseMusic();

            this.controlPanel.getMainPlayer().getDiscPanel().setTimerStop();
            this.controlPanel.getDurationBar().stopCount();
        }
    }
}
