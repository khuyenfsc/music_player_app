package features;

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
    private FileWriter fileWriter;
    private DiscPanel discPanel;
    private ControlPanel controlPanel;
    private TitlePanel titlePanel;


    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    void createPlaylistFile(String fileName) throws IOException {
        fileWriter = new FileWriter(".\\src\\playlists\\" + fileName);
        fileWriter.close();
    }

    void listFiles(File[] listOfFiles){
        PlaylistFrame playlistFrame = new PlaylistFrame();
        playlistFrame.setControlPanel(controlPanel);
        playlistFrame.setDiscPanel(discPanel);
        playlistFrame.setTitlePanel(titlePanel);
        playlistFrame.setListOfFiles(listOfFiles);
    }


    PlaylistButton(){
        this.setFocusable(false);
        this.setBorder(null);
        this.setIcon(new ImageIcon(".\\src\\images\\playlist.png"));
        this.setBounds(5, 60, 120, 50);
        this.setBackground(null);
        this.setText("Playlist");
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setForeground(Color.white);
        this.addActionListener(this);

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
