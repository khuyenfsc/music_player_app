package player;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    String title = "";

    public TitlePanel(){
        this.setBackground(null);
    }

    GridBagConstraints setTitlePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 10;
        gbc.weightx = 100;

        return gbc;
    }

    public void setTitle(String title) {
        this.title = title;

        JLabel label = new JLabel();
        label.setText(title);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.white);
        label.setBounds(20, 0, 350, 50);
        this.setLayout(null);
        this.add(label);
        revalidate();



    }
}
