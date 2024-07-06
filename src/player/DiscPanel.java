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
    BufferedImage discIcon = ImageIO.read(new File(".\\src\\images\\disc.png"));
    BufferedImage discImage;
    int angle = 1;
    DiscRotate discRotate = new DiscRotate();
    Timer timer = new Timer(100, this);

    void setTimerStart(){
        timer.start();
    }

    void setTimerStop(){
        timer.stop();
    }

    DiscPanel() throws IOException {
        this.setBackground(null);
        this.setBounds(0, 0, 450, 360);
        discImage = discRotate.rotate(discIcon, angle);

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
