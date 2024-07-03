package main;

import futures.*;
import player.*;
import player.*;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args) throws Exception {
        JFrame appWindow = new JFrame();

        MainPlayer mainPlayer = new MainPlayer();

        Futures futures = new Futures(mainPlayer.getDiscPanel(), mainPlayer.getTitlePanel(), mainPlayer.getControlPanel());

        GridBagConstraints gbc = new GridBagConstraints();

        appWindow.setLayout(new GridBagLayout());
        appWindow.setVisible(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 40;
        gbc.weighty = 100;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        appWindow.add(futures, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 60;
        gbc.insets = new Insets(0, 0, 0, -1);
        appWindow.add(mainPlayer, gbc);

        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setSize(600, 600);
        appWindow.setResizable(false);


    }
}