package features;

import player.ControlPanel;
import player.DiscPanel;
import player.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PlaylistFrame extends JFrame {
    private JPanel container = new JPanel();
    private File[] listOfFiles;
    private ActionListener actionListener;
    private DiscPanel discPanel;
    private TitlePanel titlePanel;
    private ControlPanel controlPanel;
    private String playlistFilePath;
    private class ClickListener extends MouseAdapter{
        private String playlistFilePathForThisButton;

        @Override
        public void mouseClicked(MouseEvent e){
            if(SwingUtilities.isRightMouseButton(e)){

                int response = JOptionPane.showOptionDialog(null, "You want?", "Playlist", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Open", "Delete"}, 0);

                if(response == 0){
                    SongsFrame songsFrame = new SongsFrame();
                    songsFrame.setControlPanel(controlPanel);
                    songsFrame.setDiscPanel(discPanel);
                    songsFrame.setTitlePanel(titlePanel);
                    songsFrame.getListSongs(playlistFilePath);
                    dispose();
                }else if(response == 1){
                    File file = new File(playlistFilePathForThisButton);
                    System.out.println();
                    file.delete();
                    File directory = new File(".\\src\\playlists");
                    listOfFiles = directory.listFiles();

                    dispose();
                    PlaylistFrame playlistFrame = new PlaylistFrame();
                    playlistFrame.setControlPanel(controlPanel);
                    playlistFrame.setDiscPanel(discPanel);
                    playlistFrame.setTitlePanel(titlePanel);
                    playlistFrame.setListOfFiles(listOfFiles);
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

    void setListOfFiles(File[] listOfFiles){
        this.listOfFiles = listOfFiles;
        addListOfFileToFrame();
    }

    void addListOfFileToFrame(){
        if(listOfFiles != null){
            for(File file : listOfFiles){
                JButton playlistButton = new JButton(file.getName());
                playlistFilePath = file.getAbsolutePath();

                setActionListener();
                ClickListener clickListener = new ClickListener();
                clickListener.playlistFilePathForThisButton = playlistFilePath;

                playlistButton.setFocusable(false);
                playlistButton.setFont(new Font("Arial", Font.PLAIN, 20));
                playlistButton.setForeground(Color.white);
                playlistButton.setPreferredSize(new Dimension(500, 300));
                playlistButton.setMaximumSize(new Dimension(300, 50));
                playlistButton.setBackground(null);
                playlistButton.addMouseListener(clickListener);
                playlistButton.addActionListener(actionListener);

                container.add(playlistButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane);
    }

    void setActionListener(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SongsFrame songsFrame = new SongsFrame();
                songsFrame.setControlPanel(controlPanel);
                songsFrame.setDiscPanel(discPanel);
                songsFrame.setTitlePanel(titlePanel);
                songsFrame.getListSongs(playlistFilePath);
                dispose();
            }
        };
    }


    PlaylistFrame(){
        container.setBackground(new Color(62,62,66));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        this.setSize(300, 300);
        this.setResizable(false);
        this.setVisible(true);

    }

}
