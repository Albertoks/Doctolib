package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.App;
import src.views.AppFrame;
import src.views.Header;
import src.views.PatientReservation;
import src.views.PatientSchedule;
import src.views.DoctorSchedule;

public class HeaderController implements ActionListener{
    private Header header;

    public HeaderController(Header header){
        this.header = header;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.header.getMesRdv()) {
            this.header.getMesRdv().setSelected(true);
            this.header.getPrendreRdv().setSelected(false);
            if(AppFrame.user.isAdmin()){
                AppFrame.setPanel(new DoctorSchedule());
            } else {
                AppFrame.setPanel(new PatientSchedule());
            }
        } else if (e.getSource() == this.header.getPrendreRdv()) {
            this.header.getPrendreRdv().setSelected(true);
            this.header.getMesRdv().setSelected(false);
            if(AppFrame.user.isAdmin()){
                // AppFrame.setPanel(new DoctorSchedule());
            } else {
                AppFrame.setPanel(new PatientReservation());
            }
        }
    }

}
