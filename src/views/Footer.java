package src.views;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JPanel;

import src.controllers.FooterController;
import src.models.Constants;

public class Footer extends JPanel {

    private CustomButton exitButton;
    private FooterController footerController;

    public Footer(){

        JPanel pan = new JPanel();

        this.footerController = new FooterController(this);
       
        this.exitButton = new CustomButton(new ImageIcon("res/logout_ic.png"), Color.ORANGE, true, new Dimension(60,60)); 
        this.exitButton.addActionListener(this.footerController);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS ));
        
        pan.setLayout(new BorderLayout());
        pan.setPreferredSize(new Dimension(Constants.width, 60));
        pan.setMinimumSize(new Dimension(Constants.width, 60));
        
        this.setPreferredSize(new Dimension(Constants.width, 60));
        this.setMinimumSize(new Dimension(Constants.width, 60));

        pan.add(exitButton , BorderLayout.WEST);
        this.add(Box.createHorizontalStrut(30));

        this.add(pan);
    }

    public CustomButton getexitButton() {
        return exitButton;
    }
}

