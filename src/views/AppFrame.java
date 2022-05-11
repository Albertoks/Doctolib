package src.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.models.Constants;

public class AppFrame implements Runnable {

    public static JFrame mainFrame = new JFrame();

    public AppFrame() {
        Login loginPanel = new Login();
        mainFrame.getContentPane().add(loginPanel);
        mainFrame.setBounds(100, 100, Constants.width, Constants.height);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setPanel(JPanel newPanel) {
        // mainFrame.remove(mainFrame.getContentPane()); ps grosse merde ca ne marche ap
        mainFrame.setContentPane(newPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public void run() {
        mainFrame.setVisible(true);
    }

}
