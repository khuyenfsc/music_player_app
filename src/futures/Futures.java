package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class Futures extends JPanel {
    public AddSong addSong;
    public PlaylistButton playlistButton;
    public Futures(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        addSong = new AddSong(discPanel, titlePanel, controlPanel);
        playlistButton = new PlaylistButton(discPanel, titlePanel, controlPanel);

        this.setLayout(null);
        this.setBounds(0, 0, 200, 600);
        this.setBackground(new Color(37,37,38));
        this.add(addSong);
        this.add(playlistButton);
    }
}
