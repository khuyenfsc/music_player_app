package player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DiscRotate extends JPanel {
    private int newOriginX;
    private int newOriginY;

    public int getNewOriginX() {
        return newOriginX;
    }

    public int getNewOriginY(){
        return newOriginY;
    }

    public BufferedImage rotate(BufferedImage image, int angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        newOriginX = (neww - w)/2;
        newOriginY = (newh - h)/2;
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    DiscRotate(){

    }
}
