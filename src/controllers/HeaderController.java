package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.views.AppFrame;
import src.views.DoctorReservation;
import src.views.DoctorSchedule;
import src.views.Header;
import src.views.PatientReservation;
import src.views.PatientSchedule;

public class HeaderController implements ActionListener{
    private Header header;

    public HeaderController(Header header){
        this.header = header;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.header.getMesRdv()) {
            this.header.getPrendreRdv().setSelected(false);
            if(AppFrame.user.isAdmin()){
                if(!this.header.getMesRdv().getIsSelected())
                    AppFrame.setPanel(new DoctorSchedule());
            } else {
                if(!this.header.getMesRdv().getIsSelected())
                    AppFrame.setPanel(new PatientSchedule());
            }
            this.header.getMesRdv().setSelected(true);

        } else if (e.getSource() == this.header.getPrendreRdv()) {
            this.header.getMesRdv().setSelected(false);
            if(AppFrame.user.isAdmin()){
                if(!this.header.getPrendreRdv().getIsSelected())
                    AppFrame.setPanel(new DoctorReservation());
            } else {
                if(!this.header.getPrendreRdv().getIsSelected())
                    AppFrame.setPanel(new PatientReservation());
            }
            this.header.getPrendreRdv().setSelected(true);
        }
    }

}
