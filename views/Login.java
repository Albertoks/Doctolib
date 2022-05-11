package views;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import controllers.LoginController;

public class Login extends JPanel {
    private JTextField login;
    private JPasswordField password;
    private JButton btnLogin, btnRegister;
    private JLabel title,nlogin,mdp;
    private LoginController loginController;
    private Color couleur;
    private Image image;


    public Login() {

        this.couleur = new ColorUIResource(0, 152, 229);
        this.image = new ImageIcon("img/logopetit.jpg").getImage();


        this.title = new JLabel("Connexion");

        this.login = new JTextField(10);
        this.nlogin = new JLabel("Login");

        this.password = new JPasswordField(10);
        this.mdp = new JLabel("Password");

        this.btnLogin = new JButton("Se connecter");
        this.btnRegister = new JButton("S'inscrire");

        this.setLayout(null);

        int x = 500;
        int y = 250;
        this.title.setBounds(x-40, y-70,75,25);
    
        this.login.setBounds(x, y,140,25);
        this.nlogin.setBounds(x-100, y,75,25);

        this.password.setBounds(x, y+50,140,25);
        this.mdp.setBounds(x-100, y+50,75,25);

        this.btnLogin.setBounds(x-100,y+200,245,25);
        this.btnRegister.setBounds(x+70, y+335,150,25);

        //this.login.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        this.loginController = new LoginController(this);

        this.add(title);

        this.add(login);
        this.add(nlogin);

        this.add(password);
        this.add(mdp);

        this.add(btnLogin);
        this.add(btnRegister);

        this.btnRegister.addActionListener(this.loginController);
        this.btnLogin.addActionListener(this.loginController);

        this.setBackground(this.couleur);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();	
        int height = getHeight(); 
        g.setColor(Color.white);
        g.fillRect(width/4, height/5, width/2,height-300);
        g.drawImage(image,2,3,325,125,this);
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