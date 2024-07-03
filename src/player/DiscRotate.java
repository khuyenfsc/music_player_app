package player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DiscRotate extends JPanel {
//    GraphicsConfiguration getDefault(){
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//        return gd.getDefaultConfiguration();
//    }
//
//    BufferedImage rotate(BufferedImage img, int angle){
//        double sin = Math.abs(Math.sin(angle)) ; double cos = Math.abs(Math.cos(angle));
//        int w = img.getWidth(), h = img.getHeight();
//        int newW = (int)Math.floor(cos*w + sin*h), newH = (int)Math.floor(cos*h + sin*w);
//
//        GraphicsConfiguration gc = getDefault();
//        BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.BITMASK);
//        Graphics2D g2d = result.createGraphics();
//
//        g2d.translate((newW - w)/2, (newH - h)/2);
//        g2d.rotate(Math.toRadians(angle), w/2, h/2);
//        g2d.drawRenderedImage(img, null);
//        g2d.dispose();
//        return result;
//    }

        int newOriginX;
        int newOriginY;

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
