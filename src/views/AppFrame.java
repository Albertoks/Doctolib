package src.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.models.Constants;
import src.models.User;

public class AppFrame implements Runnable {

    public static JFrame mainFrame = new JFrame("Doctolib");
    public static ClassLoader loader;
    public static User user;
    private static Header header;
    private static Footer footer;

    public AppFrame() {
        Login loginPanel = new Login();
        header = new Header();
        footer = new Footer();

        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

        mainFrame.setSize(new Dimension(Constants.width, Constants.height));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.add(loginPanel);
    }

    public static void setPanel(JPanel newPanel) {
        if (newPanel instanceof Register || newPanel instanceof Login) {
            mainFrame.setContentPane(newPanel);
        } else {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(Constants.width, Constants.height));
            panel.setMinimumSize(new Dimension(Constants.width, Constants.height));

            panel.add(header, BorderLayout.NORTH);
            panel.add(newPanel, BorderLayout.CENTER);
            panel.add(footer, BorderLayout.SOUTH);

            mainFrame.setContentPane(panel);
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public void run() {
        mainFrame.setVisible(true);
    }

}
