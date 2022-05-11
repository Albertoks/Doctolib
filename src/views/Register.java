package src.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import src.controllers.RegisterController;

public class Register extends JPanel {
    private JLabel title, nlogin, prenom, nom, mdp;
    private JTextField login, firstname, lastname;
    private JPasswordField password;
    private JButton btnRegister, btnLogin;
    private RegisterController registerController;
    private Color couleur;
    private Image image;

    public Register() {
        this.couleur = new ColorUIResource(0, 152, 229);
        this.title = new JLabel("Inscription");
        this.image = new ImageIcon("res/logopetit.jpg").getImage();

        this.login = new JTextField(10);
        this.nlogin = new JLabel("Login");

        this.firstname = new JTextField(10);
        this.prenom = new JLabel("Prenom");

        this.lastname = new JTextField(10);
        this.nom = new JLabel("Nom");

        this.password = new JPasswordField(10);
        this.mdp = new JLabel("Password");

        this.btnRegister = new JButton("S'inscrire");
        this.btnLogin = new JButton("Se connecter");

        this.setLayout(null);
        this.registerController = new RegisterController(this);

        int x = 500;
        int y = 250;
        this.title.setBounds(x - 40, y - 70, 75, 25);

        this.login.setBounds(x, y, 140, 25);
        this.nlogin.setBounds(x - 100, y, 75, 25);

        this.password.setBounds(x, y + 50, 140, 25);
        this.mdp.setBounds(x - 100, y + 50, 75, 25);

        this.lastname.setBounds(x, y + 100, 140, 25);
        this.nom.setBounds(x - 100, y + 100, 75, 25);

        this.firstname.setBounds(x, y + 150, 140, 25);
        this.prenom.setBounds(x - 100, y + 150, 75, 25);

        this.btnRegister.setBounds(x - 100, y + 200, 245, 25);
        this.btnLogin.setBounds(x + 70, y + 335, 150, 25);

        this.add(title);

        this.add(login);
        this.add(nlogin);

        this.add(firstname);
        this.add(prenom);

        this.add(lastname);
        this.add(nom);

        this.add(password);
        this.add(mdp);

        this.add(btnRegister);
        this.add(btnLogin);

        this.setBackground(this.couleur);
        this.repaint();

        this.btnRegister.addActionListener(this.registerController);
        this.btnLogin.addActionListener(this.registerController);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.white);
        g.fillRect(width / 4, height / 5, width / 2, height - 300);
        g.drawImage(image, 2, 3, 325, 125, this);
    }

    public JLabel getTitle() {
        return title;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public String getLogin() {
        return login.getText();
    }

    public String getFirstname() {
        return firstname.getText();
    }

    public String getLastname() {
        return lastname.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

}
