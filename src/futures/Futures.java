package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class Futures extends JPanel {

    public Futures(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        AddSong addSong = new AddSong(discPanel, titlePanel, controlPanel);
        this.setBackground(new Color(37,37,38));
        this.add(addSong);
    }
}
