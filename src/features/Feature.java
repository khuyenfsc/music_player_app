package features;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class Feature extends JPanel {
    private AddSong addSong;
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    public PlaylistButton playlistButton;

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public AddSong getAddSong(){
        return this.addSong;
    }

    public void setAddSong() {
        addSong = new AddSong();
        addSong.setTitlePanel(titlePanel);
        addSong.setDiscPanel(discPanel);
        addSong.setControlPanel(controlPanel);
    }

    public void setPlaylistButton() {
        playlistButton = new PlaylistButton();
        playlistButton.setTitlePanel(titlePanel);
        playlistButton.setDiscPanel(discPanel);
        playlistButton.setControlPanel(controlPanel);
    }

    public void addComponentToFeature(){
        this.add(addSong);
        this.add(playlistButton);
    }

    public Feature(){
        this.setLayout(null);
        this.setBounds(0, 0, 200, 600);
        this.setBackground(new Color(37,37,38));

    }
}
