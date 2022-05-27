package src.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import src.controllers.LoginController;

public class Login extends JPanel {
    private JTextField login;
    private JPasswordField password;
    private JButton btnLogin, btnRegister;
    private JLabel title, textlogin, mdp;
    private LoginController loginController;
    private Color couleur;
    private Image image;

    public Login() {
        this.couleur = new ColorUIResource(0, 152, 229);
        this.image = new ImageIcon("res/logopetit.jpg").getImage();

        this.title = new JLabel("Connexion");

        this.login = new JTextField(10);
        this.textlogin = new JLabel("Pseudo");

        this.password = new JPasswordField(10);
        this.mdp = new JLabel("Mot de Passe");

        this.btnLogin = new CustomButton("Se connecter", new Color(38, 171, 168), new Dimension(200, 40), false);
        this.btnRegister = new JButton("S'inscrire");

        this.setLayout(null);

        int x = 490, y = 180, height = 25, width = 100;
        int xbarre = 400, widthbarre = 250;
        this.title.setBounds(x, y, 75, height);
        // this.title.setSize(60,60);

        this.textlogin.setBounds(x + 5, y + 50, width, height);
        this.login.setBounds(xbarre, y + 80, widthbarre, height);

        this.mdp.setBounds(x, y + 125, width, height);
        this.password.setBounds(xbarre, y + 155, widthbarre, height);

        this.btnLogin.setBounds(xbarre, y + 235, widthbarre, height);
        this.btnLogin.setBackground(Color.YELLOW);

        this.btnRegister.setBounds(xbarre, y + 285, widthbarre, height);
        this.btnRegister.setBackground(Color.WHITE);

        this.loginController = new LoginController(this);

        this.add(title);

        this.add(login);
        this.add(textlogin);

        this.add(password);
        this.add(mdp);

        this.add(btnLogin);
        this.add(btnRegister);

        this.btnRegister.addActionListener(this.loginController);
        this.btnLogin.addActionListener(this.loginController);
        AppFrame.mainFrame.getRootPane().setDefaultButton(this.btnLogin);

        this.setBackground(this.couleur);
        this.repaint();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.white);
        g.fillRect(width / 4, height / 5, width / 2, height - 400);
        g.drawImage(image, width / 3, 3, 325, 125, this);
    }

    public String getLogin() {
        return login.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }
}