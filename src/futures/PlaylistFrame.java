package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class PlaylistFrame extends JFrame {
    JPanel container = new JPanel();
    File[] listOfFiles;
    ActionListener actionListener;
    DiscPanel discPanel;
    ControlPanel controlPanel;
    TitlePanel titlePanel;


    void setListOfFiles(File[] listOfFiles){
        this.listOfFiles = listOfFiles;
        addListOfFileToFrame();
    }

    void addListOfFileToFrame(){
        if(listOfFiles != null){
            for(File file : listOfFiles){
                JButton playlistButton = new JButton(file.getName());

                setActionListener(file.getAbsolutePath());

                System.out.println(file.getName());

                playlistButton.setFocusable(false);
                playlistButton.setFont(new Font("Arial", Font.PLAIN, 20));
                playlistButton.setForeground(Color.white);
                playlistButton.setPreferredSize(new Dimension(500, 300));
                playlistButton.setMaximumSize(new Dimension(300, 50));
                playlistButton.setBackground(null);

                playlistButton.addActionListener(actionListener);

                container.add(playlistButton);
            }
        }
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane);
    }

    void setActionListener(String filePath){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SongsFrame songsFrame = new SongsFrame(discPanel, titlePanel, controlPanel);
                songsFrame.getListSongs(filePath);
                dispose();
            }
        };
    }


    PlaylistFrame(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        container.setBackground(new Color(62,62,66));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        this.setSize(300, 300);
        this.setVisible(true);

        this.controlPanel = controlPanel;
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
    }

}
