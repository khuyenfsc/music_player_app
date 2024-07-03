package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainPlayer extends JPanel {

    ControlPanel controlPanel = new ControlPanel();
    DiscPanel discPanel = new DiscPanel();;
    TitlePanel titlePanel = new TitlePanel();

    public ControlPanel getControlPanel(){
        return this.controlPanel;
    }

    public DiscPanel getDiscPanel(){
        return this.discPanel;
    }

    public TitlePanel getTitlePanel(){
        return this.titlePanel;
    }

    public MainPlayer() throws IOException {

        this.setBackground(new Color(62,62,66));
        this.setLayout(new GridBagLayout());

        this.add(discPanel, discPanel.setDiscAndTitlePanel());

        this.add(titlePanel, titlePanel.setTitlePanel());

        this.add(controlPanel, controlPanel.setControlPanel());


    }


}
