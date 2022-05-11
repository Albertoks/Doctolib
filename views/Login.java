package views;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;

public class Login extends JPanel {
    private JTextField login;
    private JPasswordField password;
    private JButton btnLogin, btnRegister;
    private JLabel title;
    private LoginController loginController;

    public Login() {
        this.title = new JLabel("Connexion");
        this.login = new JTextField(10);
        this.password = new JPasswordField(10);
        this.btnLogin = new JButton("Se connecter");
        this.btnRegister = new JButton("S'inscrire");

        this.login.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        this.loginController = new LoginController(this);

        this.add(title);
        this.add(login);
        this.add(password);
        this.add(btnLogin);
        this.add(btnRegister);

        this.btnRegister.addActionListener(this.loginController);
        this.btnLogin.addActionListener(this.loginController);
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