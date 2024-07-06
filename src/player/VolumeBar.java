package player;

import main.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumeBar extends JSlider implements ChangeListener {
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    int checkVisible = 0;
    MainPlayer mainPlayer;

    VolumeBar(MainPlayer mainPlayer){
        this.setPreferredSize(new Dimension(20, 100));
        this.setOrientation(SwingConstants.VERTICAL);
        this.setMaximum(100);
        this.setBackground(null);
        this.setVisible(true);
        this.addChangeListener(this);

        if(mainPlayer.futures != null){
            label.setText(mainPlayer.futures.addSong.getPlaySong().getVolume() + "");
            this.setValue(mainPlayer.futures.addSong.getPlaySong().getVolume());
        }else{
            label.setText(0+"");
            this.setValue(0);
        }


        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.white);

        panel.setBounds(60, 440 - 120, 30, 133);
        panel.setBackground(null);
        panel.add(label);
        panel.add(this);
        panel.setVisible(false);

        this.mainPlayer = mainPlayer;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (this.mainPlayer!= null){
            this.mainPlayer.futures.addSong.getPlaySong().setVolume((0.01*this.getValue()));
        }
        this.label.setText(this.getValue()+"");
    }
}
