package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.PlaySong;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddSong extends JButton implements ActionListener {
    DiscPanel discPanel;
    TitlePanel titlePanel;
    ControlPanel controlPanel;
    PlaySong playSong;

    public AddSong(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        this.addActionListener(this);
        this.setBounds(5, 0, 150, 50);
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
        this.controlPanel = controlPanel;
        this.setText("Play a song");
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setBackground(null);
        this.setFocusable(false);
        this.setForeground(Color.white);
        this.setBorder(null);
        this.setIcon(new ImageIcon(".\\src\\images\\add.png"));
    }

    public PlaySong getPlaySong(){
        return this.playSong;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                try {
                    playSong = new PlaySong(fileChooser.getSelectedFile().getAbsolutePath(), discPanel, controlPanel);

                    controlPanel.setPlaysong(playSong);

                    titlePanel.changeTitle(fileChooser.getSelectedFile().getName());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}
