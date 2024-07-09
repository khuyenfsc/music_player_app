package features;

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
    private int ordinalNum;
    private ArrayList<SongButton> songs;
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    private File song;
    private String playlistFilePath;
    private FileWriter fileWriter;
    private SongsFrame songsFrame;
    private String filePath;

    private class ClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            if(SwingUtilities.isRightMouseButton(e)){
                int response = JOptionPane.showOptionDialog(null, "You want?", "Song", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Play", "Delete"}, 0);

                if(response == 0){
                    PlaySong playSong;

                    try {
                        playSong = new PlaySong();
                        playSong.setFilePath(song.getAbsolutePath());
                        playSong.setDiscPanel(discPanel);
                        playSong.setControlPanel(controlPanel);
                        playSong.stopPreviousSong();
                        playSong.setTotalTime();
                        playSong.playMusic();

                        controlPanel.setPlaysong(playSong);
                        controlPanel.getDurationBar().setIsPlaylist(1);
                        controlPanel.getDurationBar().setSongs(songs);
                        controlPanel.getDurationBar().setOrdinalNum(ordinalNum);

                        for (int i = 0; i < songs.size(); i++){
                            if(i != ordinalNum){
                                songs.get(i).setBackground(null);
                            }
                        }

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

                    songsFrame = new SongsFrame();
                    songsFrame.setDiscPanel(discPanel);
                    songsFrame.setTitlePanel(titlePanel);
                    songsFrame.setControlPanel(controlPanel);
                    songsFrame.getListSongs(playlistFilePath);
                }
            }

        }
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

    public void setOrdinalNum(int ordinalNum) {
        this.ordinalNum = ordinalNum;
    }

    public void setSongs(ArrayList<SongButton> songs) {
        this.songs = songs;
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

    SongButton(String data){
        song = new File(data);
        ClickListener clickListener = new ClickListener();

        this.setText(song.getName());
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setPreferredSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(500, 50));
        this.setBackground(null);
        this.setFocusable(false);
        this.setForeground(Color.white);
        this.addMouseListener(clickListener);
        this.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PlaySong playSong;

        try {
            playSong = new PlaySong();
            playSong.setFilePath(song.getAbsolutePath());
            playSong.setDiscPanel(discPanel);
            playSong.setControlPanel(controlPanel);
            playSong.stopPreviousSong();
            playSong.setTotalTime();
            playSong.playTheSong();

            controlPanel.setPlaysong(playSong);
            controlPanel.getDurationBar().setIsPlaylist(1);
            controlPanel.getDurationBar().setSongs(songs);
            controlPanel.getDurationBar().setOrdinalNum(ordinalNum);

            titlePanel.changeTitle(song.getName());

            for (int i = 0; i < songs.size(); i++){
                if(i != ordinalNum){
                    songs.get(i).setBackground(null);
                }
            }

            setBackground(Color.lightGray);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
