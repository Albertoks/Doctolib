package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import src.models.Database;
import src.views.AppFrame;
import src.views.Login;
import src.views.Register;

public class RegisterController implements ActionListener {
    private Register registerPanel;
    private Database database;

    public RegisterController(Register register) {
        this.registerPanel = register;
        this.database = Database.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.registerPanel.getBtnRegister()) {
            if (this.registerPanel.getLogin().length() > 0 && this.registerPanel.getPassword().length() > 0
                    && this.registerPanel.getFirstname().length() > 0
                    && this.registerPanel.getLastname().length() > 0) {
                Boolean res = this.database.register(this.registerPanel.getLogin(), this.registerPanel.getPassword(),
                        this.registerPanel.getLastname(), this.registerPanel.getFirstname());
                if (res) {
                    JOptionPane.showMessageDialog(null,
                            "Votre compte a été crée.",
                            "Inscription",
                            JOptionPane.PLAIN_MESSAGE);
                    AppFrame.setPanel(new Login());
                } else if (!res)
                    JOptionPane.showMessageDialog(null,
                            "Un compte avec un pseudo similaire existe déjà. Veuillez utiliser un autre pseudo.",
                            "Compte existant",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,
                            "Une erreur est survenue lors de votre inscription. Veuillez réessayer ultérieurement.",
                            "Erreur inscription",
                            JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez remplir les champs avant de valider.", "Erreur champ",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.registerPanel.getBtnLogin()) {
            AppFrame.setPanel(new Login());
        }

    }

}
