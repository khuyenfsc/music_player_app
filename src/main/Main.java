package main;

import features.*;
import player.*;

import javax.swing.*;

public class Main{
    public static void main(String[] args) throws Exception {

        JFrame appWindow = new JFrame();

        MainPlayer mainPlayer = new MainPlayer();

        Feature feature = new Feature();
        feature.setTitlePanel(mainPlayer.getTitlePanel());
        feature.setControlPanel(mainPlayer.getControlPanel());
        feature.setDiscPanel(mainPlayer.getDiscPanel());
        feature.setAddSong();
        feature.setPlaylistButton();
        feature.addComponentToFeature();

        appWindow.setVisible(true);

        mainPlayer.setFeature(feature);
        appWindow.add(feature);

        appWindow.add(mainPlayer);

        appWindow.setLayout(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setSize(650, 630);
        appWindow.setResizable(false);

    }
}