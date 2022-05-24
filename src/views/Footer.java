package src.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Footer extends JPanel {
    private CustomButton exitButton;
    public Footer(){
        this.setLayout(new GridBagLayout());
        exitButton = new CustomButton(new ImageIcon("res/logout_ic"), Color.ORANGE, true, new Dimension(60,60));
        this.add(exitButton);
        this.setBackground(Color.ORANGE);
    }
}
