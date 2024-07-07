package player;

import futures.Futures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainPlayer extends JPanel {
    DiscPanel discPanel = new DiscPanel();
    ControlPanel controlPanel = new ControlPanel(discPanel, this);
    TitlePanel titlePanel = new TitlePanel();
    Futures futures;
    VolumeBar volumeBar = new VolumeBar(this);
    JLayeredPane layeredPane = new JLayeredPane();

    public ControlPanel getControlPanel(){
        return this.controlPanel;
    }

    public DiscPanel getDiscPanel(){
        return this.discPanel;
    }

    public TitlePanel getTitlePanel(){
        return this.titlePanel;
    }

    public void setFutures(Futures futures){
        this.futures = futures;
    }

    public MainPlayer() throws IOException {
        layeredPane.setBounds(0, 0, 450, 600);

        this.setBackground(new Color(62,62,66));
        this.setBounds(200, 0, 450, 600);
        this.setLayout(null);

        layeredPane.add(discPanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(0));
        layeredPane.add(controlPanel, Integer.valueOf(0));
        layeredPane.add(volumeBar.panel, Integer.valueOf(1));


        this.add(layeredPane);
    }


}
