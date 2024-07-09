package features;

import player.ControlPanel;
import player.DiscPanel;
import player.PlaySong;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSong extends JButton implements ActionListener {
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    private PlaySong playSong;

    public PlaySong getPlaySong(){
        return this.playSong;
    }

    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    public AddSong(){
        this.addActionListener(this);
        this.setBounds(5, 0, 150, 50);
        this.setText("Play a song");
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setBackground(null);
        this.setFocusable(false);
        this.setForeground(Color.white);
        this.setBorder(null);
        this.setIcon(new ImageIcon(".\\src\\images\\add.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                try {
                    playSong = new PlaySong();
                    playSong.setFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                    playSong.setControlPanel(controlPanel);
                    playSong.setDiscPanel(discPanel);
                    playSong.stopPreviousSong();
                    playSong.setTotalTime();
                    playSong.playTheSong();


                    controlPanel.setPlaysong(playSong);

                    titlePanel.changeTitle(fileChooser.getSelectedFile().getName());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}
