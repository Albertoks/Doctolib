package src.views;

import javax.swing.JPanel;

import src.controllers.HeaderController;
import src.models.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class Header extends JPanel {
    private CustomButton mesRdv;
    private CustomButton prendreRdv;
    private Color colorSet;
    private HeaderController headerController;

    public Header(){
        this.setPreferredSize(new Dimension(Constants.width, 80));
        this.setMinimumSize(new Dimension(Constants.width, 80));

        this.headerController = new HeaderController(this);
        colorSet= new Color(0, 152, 229);
        mesRdv = new CustomButton("Mes rendez-vous", colorSet,new Dimension(200,80),true);

        prendreRdv = new CustomButton("Prendre rendez-vous", colorSet,new Dimension(200,80),true);

        mesRdv.setSelected(true);
        mesRdv.addActionListener(this.headerController);
        prendreRdv.addActionListener(this.headerController);

        this.setLayout(new GridBagLayout());

        GridBagConstraints c=new GridBagConstraints();

        //---------------------- HEADER
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(mesRdv, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.add(prendreRdv, c);

        this.setBackground(Color.WHITE);
    }

    public CustomButton getMesRdv() {
        return mesRdv;
    }

    public CustomButton getPrendreRdv() {
        return prendreRdv;
    }
}
