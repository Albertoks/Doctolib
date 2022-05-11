package views;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accueil extends JPanel {
    private JLabel text;

    public Accueil(String prenom) {
        this.text = new JLabel("Bonjour, vous êtes connecté en tant que " + prenom);
        this.add(text);
    }
}
