package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame {

    public static JFrame mainFrame=new JFrame();

    public AppFrame() {
        Login loginPanel = new Login();
        mainFrame.getContentPane().add(loginPanel);
        mainFrame.setBounds(100, 100, 800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static void setPanel(JPanel newPanel) {
        //mainFrame.remove(mainFrame.getContentPane()); ps grosse merde ca ne marche ap
        mainFrame.setContentPane(newPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

}
