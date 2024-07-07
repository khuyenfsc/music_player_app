package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlaylistButton extends JButton implements ActionListener {
    FileWriter fileWriter;
    DiscPanel discPanel;
    ControlPanel controlPanel;
    TitlePanel titlePanel;

    void createPlaylistFile(String fileName) throws IOException {
        fileWriter = new FileWriter(".\\src\\playlists\\" + fileName);
        fileWriter.close();
    }

    void listFiles(File[] listOfFiles){
        PlaylistFrame playlistFrame = new PlaylistFrame(discPanel, titlePanel, controlPanel);
        playlistFrame.setListOfFiles(listOfFiles);
    }


    PlaylistButton(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        this.setFocusable(false);
        this.setBorder(null);
        this.setIcon(new ImageIcon(".\\src\\images\\playlist.png"));
        this.setBounds(5, 60, 120, 50);
        this.setBackground(null);
        this.setText("Playlist");
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setForeground(Color.white);
        this.addActionListener(this);

        this.controlPanel = controlPanel;
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] strings = {"Create a new playlist", "Show playlists"};

        int response = JOptionPane.showOptionDialog(null, "You want?", "Playlist", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, strings, 0);

        if(response == 0){
            String playlistName = JOptionPane.showInputDialog("Enter playlist name:");

            try {
                createPlaylistFile(playlistName);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if(response == 1){
            File directory = new File(".\\src\\playlists");
            File[] listOfFile = directory.listFiles();
            listFiles(listOfFile);
        }

    }
}
