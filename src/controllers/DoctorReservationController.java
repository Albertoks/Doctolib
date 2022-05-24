package src.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.models.Database;
import src.models.User;
import src.views.AppFrame;
import src.views.DoctorReservation;

public class DoctorReservationController implements ChangeListener {
    private DoctorReservation doctorReservation;
    private Database database;
    private ArrayList<User> users;
    private ArrayList<LocalTime> time;

    public DoctorReservationController(DoctorReservation doctorReservation) {
        database = Database.getInstance();
        users = database.getPatients();
        this.doctorReservation = doctorReservation;

        for (int i = 0; i < users.size(); i++) {
            this.doctorReservation.getjComboBoxPatient().insertItemAt(users.get(i).toString(), i);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        // this.doctorReservation.getjComboBoxHeure().removeAll();
        if (this.doctorReservation.getjComboBoxPatient().getSelectedItem() != null
                && doctorReservation.getDatePanelImpl().getModel().getValue() != null) {
            long tmpDate = ((java.util.Date) this.doctorReservation.getDatePanelImpl().getModel().getValue()).getTime();
            Date date = new java.sql.Date(tmpDate);
            String date1 = new SimpleDateFormat("YYYY-MM-dd").format(date);

            this.time = database.getAvailableReservation(AppFrame.user.getLogin(),
                    this.users.get(this.doctorReservation.getjComboBoxPatient().getSelectedIndex()).getLogin(), Date.valueOf(date1));

            for (int i = 0; i < this.time.size(); i++) {
                this.doctorReservation.getjComboBoxHeure().insertItemAt(time.get(i).toString(), i);
            }
        }
    }
}
