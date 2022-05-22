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
    private JLabel title, textlogin, prenom, nom, mdp;
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
        this.textlogin = new JLabel("Pseudo");

        this.firstname = new JTextField(10);
        this.prenom = new JLabel("Prenom");

        this.lastname = new JTextField(10);
        this.nom = new JLabel("Nom");

        this.password = new JPasswordField(10);
        this.mdp = new JLabel("Mot de passe");

        this.btnRegister = new JButton("S'inscrire");
        this.btnLogin = new JButton("Se connecter");

        this.setLayout(null);
        this.registerController = new RegisterController(this);

        int x = 490, y = 180, height = 25, width = 100;
        int xbarre = 400, widthbarre = 250;

        this.title.setBounds(x , y, 75, height);

        this.prenom.setBounds(x+5, y+50, width, height);
        this.firstname.setBounds(xbarre, y+80, widthbarre, height);

        this.nom.setBounds(x+10, y + 125, width, height);
        this.lastname.setBounds(xbarre, y + 155, widthbarre, height);

        this.textlogin.setBounds(x+5, y + 200, width, height);
        this.login.setBounds(xbarre, y + 230, widthbarre, height);

        this.mdp.setBounds(x, y + 275, width, height);
        this.password.setBounds(xbarre, y + 305, widthbarre, height);

        this.btnRegister.setBounds(xbarre, y + 380, widthbarre, height);
        this.btnRegister.setBackground(Color.YELLOW);

        this.btnLogin.setBounds(xbarre, y + 430, widthbarre, height);
        this.btnLogin.setBackground(Color.WHITE);

        this.add(title);

        this.add(login);
        this.add(textlogin);

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
        g.fillRect(width / 4, height / 5, width / 2, height - 200);
        g.drawImage(image, width / 3, 3, 325, 125, this);
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
