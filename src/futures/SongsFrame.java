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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SongsFrame extends JFrame implements ActionListener {
    JPanel container = new JPanel();
    Scanner scanner;
    ArrayList<SongButton> songs = new ArrayList<>();
    DiscPanel discPanel;
    ControlPanel controlPanel;
    TitlePanel titlePanel;
    JButton addButton = new JButton("ADD");
    FileWriter fileWriter;
    String filePath;

    void getListSongs(String filePath){
        int ordinalNum = 0;
        this.filePath = filePath;

        try {
            openFileToRead(filePath);
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

        closeFileAfterRead(filePath);
    }

    void openFileToRead(String filePath) throws IOException {
        scanner = new Scanner(Paths.get(filePath));
    }

    void closeFileAfterRead(String filePath){
        scanner.close();
    }

    void setSongButton(String data, int ordinalNum){
        SongButton songButton = new SongButton(data, ordinalNum, songs, discPanel, titlePanel, controlPanel);
        songButton.setFilePath(data);
        songButton.setPlaylistFilePath(filePath);
        songButton.setSongsFrame(this);

        songs.add(songButton);
        container.add(songButton);
    }

    void openFileToWrite(String filePath) throws IOException {
        fileWriter = new FileWriter(filePath, true);
    }

    void closeFileAfterWrite(String filePath) throws IOException {
        fileWriter.close();
    }

    void writeToFile(String data) throws IOException {
        openFileToWrite(filePath);

        fileWriter.write(data);

        closeFileAfterWrite(filePath);
    }

    SongsFrame(DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        container.setBackground(new Color(62,62,66));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        addButton.setPreferredSize(new Dimension(500, 50));
        addButton.setIcon(new ImageIcon(".\\src\\images\\add.png"));
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        this.setSize(500, 500);
        this.setLayout(new BorderLayout());
        this.add(addButton, BorderLayout.SOUTH);
        this.setVisible(true);

        this.controlPanel = controlPanel;
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
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
        SongsFrame songsFrame = new SongsFrame(discPanel, titlePanel, controlPanel);
        songsFrame.getListSongs(filePath);
    }
}
