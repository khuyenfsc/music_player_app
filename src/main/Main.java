package main;

import futures.*;
import player.*;

import javax.swing.*;

public class Main{
    public static void main(String[] args) throws Exception {

        JFrame appWindow = new JFrame();

        MainPlayer mainPlayer = new MainPlayer();

        Futures futures = new Futures(mainPlayer.getDiscPanel(), mainPlayer.getTitlePanel(), mainPlayer.getControlPanel());

        appWindow.setVisible(true);

        mainPlayer.setFutures(futures);
        appWindow.add(futures);

        appWindow.add(mainPlayer);

        appWindow.setLayout(null);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setSize(600, 630);
        appWindow.setResizable(true);

    }
}