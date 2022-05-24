package src.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.models.Constants;
import src.models.User;

public class AppFrame implements Runnable {

    public static JFrame mainFrame = new JFrame("Doctolib");
    public static ClassLoader loader;
    public static User user;

    public AppFrame() {
        user = new User("Quintois", "Arnaud", "Naunau", "arnaud", false);
        Login loginPanel = new Login();
        PatientSchedule patientSchedule = new PatientSchedule();
        DoctorReservation doctorReservation = new DoctorReservation();

        DoctorSchedule schedule = new DoctorSchedule();

        Header header = new Header();
        Footer footer = new Footer();

        // patientSchedule.setPreferredSize(new Dimension(300,500));

        // patientSchedule.setPreferredSize(new Dimension(300,500));

        /*
         * Container cont=mainFrame.getContentPane();
         * cont.setLayout(new GridBagLayout());
         * mainFrame.setLayout(new GridBagLayout());
         */

        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        mainFrame.setSize(new Dimension(Constants.width, Constants.height));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        // mainFrame.add(loginPanel);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // ---------------------- HEADER

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        mainFrame.add(header);
        // ---------------------------

        // -------------------------Panel principal
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 500; // make this component tall
        c.ipadx = 500; // make this component tall
        c.insets = new Insets(20, 50, 0, 50);
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 1;

        mainFrame.add(patientSchedule);
        // ----------------------------------

        // ---------------------- FOOTER
        // button = new CustomButton("Se déconnecter",Color.ORANGE,new
        // Dimension(200,40),true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // reset to default
        c.weighty = 1.0; // request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 0, 0); // top padding
        c.gridx = 0; // aligned with button 2
        c.gridwidth = 1; // 2 columns wide
        c.gridy = 2; // third row

        mainFrame.add(footer);
        // -----------------------

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
