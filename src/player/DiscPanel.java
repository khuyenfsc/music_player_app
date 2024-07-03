package player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiscPanel extends JPanel implements ActionListener {
    BufferedImage discIcon = ImageIO.read(new File("C:\\Users\\khuyen\\project\\java_learning\\music_player_app\\src\\images\\disc.png"));
    BufferedImage discImage;
    int angle = 1;
    DiscRotate discRotate = new DiscRotate();
    Timer timer = new Timer(100, this);
    JLabel titleLabel = new JLabel();

    public void setTitleLabel(String title){
        titleLabel.setText(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(500, 500, 500, 500);

        this.setLayout(null);
        this.add(titleLabel);
    }

    void setTimerStart(){
        timer.start();
    }

    DiscPanel() throws IOException {
        this.setBackground(null);
        discImage = discRotate.rotate(discIcon, angle);

    }

    GridBagConstraints setDiscAndTitlePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 60;
        gbc.weightx = 100;

        return gbc;
    }

    public void paintComponent(Graphics g){

        discImage = discRotate.rotate(discIcon, angle);

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g ;
        g2d.drawImage(discImage,(getWidth() - Math.min(getHeight(),getWidth()))/2 - discRotate.getNewOriginX() + 35, 0 - discRotate.getNewOriginY()  + 40, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(angle >= 360){
            angle = 1;
        }
        repaint();
        angle++;
    }
}
