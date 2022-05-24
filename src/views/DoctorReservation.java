package src.views;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class DoctorReservation extends JPanel{
    public DoctorReservation(){
        GridBagConstraints c=new GridBagConstraints();

        JPanel panelPatient = new JPanel();
        JPanel panelRdv = new JPanel();

        JLabel labelRdv= new JLabel("Choisissez la date et l'heure du rendez-vous");
        JLabel labelPatient= new JLabel("Choisissez un patient");

    
        JLabel textDate= new JLabel("Date");
        JLabel textHour= new JLabel("Heure");

        String[] optionsPatient = {"Dupond", "Tintin", "Milou", "Shanks", "Roronoa"};
        String[] optionsHours = {"8h00", "8h30", "9h00", "9h30", "10h30"};
        String[] optionsToDate = {"22/05", "23/05", "24/05", "25/05", "26/05"};

        JComboBox<String> jComboBoxPatient = new JComboBox<>(optionsPatient);
        JComboBox<String> jComboBoxDate = new JComboBox<>(optionsToDate);
        JComboBox<String> jComboBoxHeure = new JComboBox<>(optionsHours);

        jComboBoxDate.setBounds(80, 50, 140, 20);

        panelRdv.setLayout(new GridBagLayout());
        panelRdv.setBackground(Color.white);
        panelRdv.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelRdv.add(labelRdv,c);

        c.weightx = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panelRdv.add(textDate,c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panelRdv.add(jComboBoxDate,c);

        c.weightx = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panelRdv.add(textHour,c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelRdv.add(jComboBoxHeure,c);


        panelPatient.setLayout(new GridBagLayout());
        panelPatient.setBackground(Color.white);
        panelPatient.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelPatient.add(labelPatient,c);

       
        c.weightx =1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panelPatient.add(jComboBoxPatient,c);

        this.setLayout(new GridBagLayout());
        
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,50,0,50);
        this.add(panelPatient,c);

        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 50;
        c.insets = new Insets(20,50,0,50);
        this.add(panelRdv,c);
        //String selectedFruit = "You selected " + jComboBox.getItemAt(jComboBox.getSelectedIndex()); // code pour récupérer l'option sélectionnée
    

    }
}
