package player;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    String title = "";
    JLabel label = new JLabel();

    public void changeTitle(String title){
        label.setText("");
        label.setText(title);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.white);
        label.setBounds(20, 0, 350, 60);

        revalidate();
    }

    TitlePanel(){
        this.setBackground(null);
        this.setLayout(null);
        this.setBounds(0, 360, 450, 60);
        this.add(label);
    }
}
