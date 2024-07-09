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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SongsFrame extends JFrame implements ActionListener {
    private JPanel container = new JPanel();
    private Scanner scanner;
    private ArrayList<SongButton> songs = new ArrayList<>();
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    private JButton addButton = new JButton("ADD");
    private FileWriter fileWriter;
    private String playlistFilePath;

    void setPlaylistFilePath(String playlistFilePath){
        this.playlistFilePath = playlistFilePath;
    }

    void getListSongs(String playlistFilePath){
        int ordinalNum = 0;
        setPlaylistFilePath(playlistFilePath);

        try {
            openFileToRead(playlistFilePath);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            setSongButton(data, ordinalNum);
            ordinalNum++;
        }

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane);

        closeFileAfterRead(playlistFilePath);
    }

    void openFileToRead(String filePath) throws IOException {
        scanner = new Scanner(Paths.get(filePath));
    }

    void closeFileAfterRead(String filePath){
        scanner.close();
    }

    void setSongButton(String data, int ordinalNum){
        SongButton songButton = new SongButton(data);
        songButton.setSongs(songs);
        songButton.setTitlePanel(titlePanel);
        songButton.setDiscPanel(discPanel);
        songButton.setControlPanel(controlPanel);
        songButton.setOrdinalNum(ordinalNum);
        songButton.setFilePath(data);
        songButton.setPlaylistFilePath(playlistFilePath);
        songButton.setSongsFrame(this);

        songs.add(songButton);
        container.add(songButton);
    }

    void setAddButton(){
        addButton.setPreferredSize(new Dimension(500, 50));
        addButton.setIcon(new ImageIcon(".\\src\\images\\add.png"));
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        addButton.setFocusable(false);
        addButton.addActionListener(this);
    }

    public void setDiscPanel(DiscPanel discPanel) {
        this.discPanel = discPanel;
    }

    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    void openFileToWrite(String filePath) throws IOException {
        fileWriter = new FileWriter(filePath, true);
    }

    void closeFileAfterWrite(String filePath) throws IOException {
        fileWriter.close();
    }

    void writeToFile(String data) throws IOException {
        openFileToWrite(playlistFilePath);

        fileWriter.write(data);

        closeFileAfterWrite(playlistFilePath);
    }

    SongsFrame(){
        container.setBackground(new Color(62,62,66));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        setAddButton();

        this.setSize(500, 500);
        this.setLayout(new BorderLayout());
        this.add(addButton, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.showOpenDialog(null);

        File file = fileChooser.getSelectedFile();

        try {
            writeToFile(file.getAbsolutePath()+"\n");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.dispose();
        SongsFrame songsFrame = new SongsFrame();
        songsFrame.setControlPanel(controlPanel);
        songsFrame.setDiscPanel(discPanel);
        songsFrame.setTitlePanel(titlePanel);
        songsFrame.getListSongs(playlistFilePath);
    }
}
