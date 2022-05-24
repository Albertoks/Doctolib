package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.views.AppFrame;
import src.views.DoctorSchedule;
import src.views.Footer;
import src.views.Login;
import src.views.PatientSchedule;

public class FooterController implements ActionListener{
    private Footer footer;

    public FooterController(Footer footer){
        this.footer = footer;    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.footer.getexitButton()) {
            System.out.println("deconexion");
            AppFrame.setPanel(new Login());
        }
           
    }

}
