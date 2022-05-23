package src.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PatientSchedule extends JPanel {
    private CustomButton aVenir;
    private CustomButton passe;
    private Color colorSet;
    public PatientSchedule() {

        JPanel jPanel = new JPanel();
        //jPanel.setPreferredSize(new Dimension(300,40));
        GridBagConstraints c=new GridBagConstraints();
        jPanel.setLayout(new GridBagLayout());
        colorSet = new Color(38, 171, 168);
        aVenir = new CustomButton("À venir", colorSet,new Dimension(200,40),false);
        passe = new CustomButton("Passé", Color.WHITE,new Dimension(200,40),false);


        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        jPanel.add(aVenir, c);
         
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        jPanel.add(passe, c);

        this.add(jPanel);

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }
}
