package player;

import features.AddSong;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumeBar extends JSlider implements ChangeListener {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private int checkVisible = 0;
    private MainPlayer mainPlayer;
    private AddSong addSong;

    public void setMainPlayer(MainPlayer mainPlayer){
        this.mainPlayer = mainPlayer;
    }

    public JPanel getPanel(){
        return panel;
    }

    public int getCheckVisible(){
        return checkVisible;
    }

    public void setCheckVisible(int checkVisible){
        this.checkVisible = checkVisible;
    }

    VolumeBar(){
        this.setPreferredSize(new Dimension(20, 100));
        this.setOrientation(SwingConstants.VERTICAL);
        this.setMaximum(100);
        this.setBackground(null);
        this.setVisible(true);
        this.addChangeListener(this);

        if(mainPlayer != null){
            label.setText(addSong.getPlaySong().getVolume() + "");
            this.setValue(addSong.getPlaySong().getVolume());
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

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (this.mainPlayer!= null){
            this.mainPlayer.getControlPanel().getPlaySong().setVolume((0.01*this.getValue()));
        }
        this.label.setText(this.getValue()+"");
    }
}
