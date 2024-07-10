package player;

import features.Feature;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainPlayer extends JPanel {
    private DiscPanel discPanel = new DiscPanel();
    private ControlPanel controlPanel = new ControlPanel();
    private TitlePanel titlePanel = new TitlePanel();
    private Feature feature;
    private VolumeBar volumeBar = new VolumeBar();
    private JLayeredPane layeredPane = new JLayeredPane();

    public ControlPanel getControlPanel(){
        return this.controlPanel;
    }

    public DiscPanel getDiscPanel(){
        return this.discPanel;
    }

    public TitlePanel getTitlePanel(){
        return this.titlePanel;
    }

    public VolumeBar getVolumeBar(){
        return this.volumeBar;
    }

    public Feature getFeature(){
        return feature;
    }

    public void setFeature(Feature feature){
        this.feature = feature;
    }

    public MainPlayer() throws IOException {
        volumeBar.setMainPlayer(this);

        controlPanel.setDiscPanel(discPanel);
        controlPanel.setMainPlayer(this);

        layeredPane.setBounds(0, 0, 450, 600);

        this.setBackground(new Color(62,62,66));
        this.setBounds(200, 0, 450, 600);
        this.setLayout(null);

        layeredPane.add(discPanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(0));
        layeredPane.add(controlPanel, Integer.valueOf(0));
        layeredPane.add(volumeBar.getPanel(), Integer.valueOf(1));

        this.add(layeredPane);
    }


}
