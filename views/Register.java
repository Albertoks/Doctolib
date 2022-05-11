package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.RegisterController;

public class Register extends JPanel {
    private JLabel title;
    private JTextField login, firstname, lastname;
    private JPasswordField password;
    private JButton btnRegister, btnLogin;
    private RegisterController registerController;

    public Register() {
        this.title = new JLabel("Inscription");
        this.login = new JTextField(10);
        this.firstname = new JTextField(10);
        this.lastname = new JTextField(10);
        this.password = new JPasswordField(10);
        this.btnRegister = new JButton("S'inscrire");
        this.btnLogin = new JButton("Se connecter");

        this.registerController = new RegisterController(this);

        this.add(title);
        this.add(login);
        this.add(firstname);
        this.add(lastname);
        this.add(password);
        this.add(btnRegister);
        this.add(btnLogin);

        this.btnRegister.addActionListener(this.registerController);
        this.btnLogin.addActionListener(this.registerController);
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
