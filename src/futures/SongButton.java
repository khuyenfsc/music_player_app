package futures;

import player.ControlPanel;
import player.DiscPanel;
import player.PlaySong;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class SongButton extends JButton implements ActionListener{
    int ordinalNum;
    ArrayList<SongButton> songs;
    DiscPanel discPanel;
    ControlPanel controlPanel;
    TitlePanel titlePanel;
    File song;
    String playlistFilePath;
    FileWriter fileWriter;
    SongsFrame songsFrame;
    String filePath;

    class ClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            if(SwingUtilities.isRightMouseButton(e)){
                int response = JOptionPane.showOptionDialog(null, "You want?", "Song", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Play", "Delete"}, 0);

                if(response == 0){
                    PlaySong playSong;

                    try {
                        playSong = new PlaySong(song.getAbsolutePath(), discPanel, controlPanel);

                        controlPanel.setPlaysong(playSong);
                        controlPanel.durationBar.isPlaylist = 1;
                        controlPanel.durationBar.setSongs(songs);
                        controlPanel.durationBar.setOrdinalNum(ordinalNum);

                        titlePanel.changeTitle(song.getName());

                        setBackground(Color.lightGray);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(response == 1){
                    try {
                        deleteContextInFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        openFileToWrite(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    for (int i = 0; i < songs.size(); i++){
                        if(i != ordinalNum){
                            try {
                                fileWriter.write(songs.get(i).filePath + "\n");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                    try {
                        closeFileAfterWrite();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    songsFrame.dispose();

                    songsFrame = new SongsFrame(discPanel, titlePanel, controlPanel);
                    songsFrame.getListSongs(playlistFilePath);
                }
            }

        }
    }

    void setPlaylistFilePath(String playlistFilePath){
        this.playlistFilePath = playlistFilePath;
    }

    void setFilePath(String filePath){
        this.filePath = filePath;
    }

    void openFileToWrite(boolean appendPermisson) throws IOException {
        fileWriter = new FileWriter(playlistFilePath, appendPermisson);
    }

    void closeFileAfterWrite() throws IOException {
        fileWriter.close();
    }

    void writeToFile(String data) throws IOException {
        openFileToWrite(true);

        fileWriter.write(data);

        closeFileAfterWrite();
    }

    void deleteContextInFile() throws IOException {
        openFileToWrite(false);

        fileWriter.write("");

        closeFileAfterWrite();
    }

    void setSongsFrame(SongsFrame songsFrame){
        this.songsFrame = songsFrame;
    }

    public String getFilePath(){
        return this.filePath;
    }

    SongButton(String data, int ordinalNum, ArrayList<SongButton> songs, DiscPanel discPanel, TitlePanel titlePanel, ControlPanel controlPanel){
        song = new File(data);
        ClickListener clickListener = new ClickListener();

        this.setText(song.getName());
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setPreferredSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(500, 50));
        this.setBackground(null);
        this.setFocusable(false);
        this.setForeground(Color.white);
//        this.addMouseListener(clickListener);
        this.addActionListener(this);

        this.songs = songs;
        this.ordinalNum = ordinalNum;
        this.controlPanel = controlPanel;
        this.discPanel = discPanel;
        this.titlePanel = titlePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PlaySong playSong;

        try {
            playSong = new PlaySong(song.getAbsolutePath(), discPanel, controlPanel);

            controlPanel.setPlaysong(playSong);
            controlPanel.durationBar.isPlaylist = 1;
            controlPanel.durationBar.setSongs(songs);
            controlPanel.durationBar.setOrdinalNum(ordinalNum);

            titlePanel.changeTitle(song.getName());

            setBackground(Color.lightGray);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
