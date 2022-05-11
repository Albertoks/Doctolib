package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import src.models.Database;
import src.models.User;
import src.views.AppFrame;
import src.views.Login;
import src.views.Register;
import src.views.Schedule;

public class LoginController implements ActionListener {
    private Login loginPanel;
    private Database database;
    private User user = null;

    public LoginController(Login login) {
        this.loginPanel = login;
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
                    AppFrame.setPanel(new Schedule(user));
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
