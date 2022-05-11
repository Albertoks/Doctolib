package controllers;

import java.awt.event.*;

import javax.swing.JOptionPane;

import models.Database;
import models.User;
import views.Accueil;
import views.AppFrame;
import views.Login;
import views.Register;

public class LoginController implements ActionListener {
    private Login loginPanel;
    private Database database;
    private User user = null;

    public LoginController(Login login) {
        this.loginPanel=login;
        this.database = Database.getInstance();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.loginPanel.getBtnLogin()) {
            if (this.loginPanel.getLogin().length() > 0 && this.loginPanel.getPassword().length() > 0) {
                user = database.login(this.loginPanel.getLogin(), this.loginPanel.getPassword());
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect.", "Erreur login",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    AppFrame.setPanel(new Accueil(user.getPrenom()));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez remplir les champs avant de valider.", "Erreur champ",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.loginPanel.getBtnRegister()) {
            AppFrame.setPanel(new Register());
        }
    }
}
