package src.views;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import src.models.Constants;
import src.models.User;

public class AppFrame implements Runnable {

    public static JFrame mainFrame = new JFrame("Doctolib");
    public static ClassLoader loader;
    public static User user;
    public AppFrame() {
        user = new User("Toko", "Nathan", "Toks", "admin", true);
        Login loginPanel = new Login();
        // PatientSchedule patientSchedule = new PatientSchedule();
        Header header = new Header();
        Footer footer = new Footer();


        //patientSchedule.setPreferredSize(new Dimension(300,500));

        Container cont=mainFrame.getContentPane();
        cont.setLayout(new GridBagLayout());
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setSize(new Dimension( Constants.width, Constants.height));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.add(loginPanel);
        GridBagConstraints c=new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //---------------------- HEADER
        CustomButton button = new CustomButton("Mes rendez-vous",Color.CYAN,new Dimension(200,40),true);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        // mainFrame.add(button, c);
         
        // button = new CustomButton("Prendre rendez-vous",Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        mainFrame.add(header, c);
        //---------------------------

        //-------------------------Panel principal
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 600;      //make this component tall
        c.insets = new Insets(20,0,0,0);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        mainFrame.add(new DoctorSchedule(), c);
        //----------------------------------

        //---------------------- FOOTER
        button = new CustomButton("Se déconnecter",Color.ORANGE,new Dimension(200,40),false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 2;       //third row
        mainFrame.add(footer, c);
        //-----------------------
       
    }

    public static void setPanel(JPanel newPanel) {
        mainFrame.setContentPane(newPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public void run() {
        mainFrame.setVisible(true);
    }

}
