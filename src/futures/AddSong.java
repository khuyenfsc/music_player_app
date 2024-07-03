package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.PlaySong;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSong extends JButton implements ActionListener {
    DiscPanel discPanel;
    TitlePanel titlePanel;
    ControlPanel controlPanel;

    public AddSong(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        this.addActionListener(this);
        this.setPreferredSize(new Dimension(100, 50));
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                try {
                    PlaySong playSong = new PlaySong(fileChooser.getSelectedFile().getAbsolutePath(), discPanel, controlPanel);
                    System.out.println(fileChooser.getSelectedFile().getName());

                    titlePanel.setTitle(fileChooser.getSelectedFile().getName());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}
