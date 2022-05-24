package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.models.Database;
import src.models.User;
import src.views.AppFrame;
import src.views.Reservation;

public class ReservationController implements ChangeListener, ActionListener {
    private Reservation doctorReservation;
    private Database database;
    private ArrayList<User> users;
    private ArrayList<LocalTime> time;

    public ReservationController(Reservation doctorReservation) {
        database = Database.getInstance();
        if (AppFrame.user.isAdmin())
            users = database.getPatients();
        else
            users = database.getDoctors();
        this.doctorReservation = doctorReservation;

        for (int i = 0; i < users.size(); i++) {
            this.doctorReservation.getjComboBoxPatient().insertItemAt(users.get(i).toString(), i);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        String patient;
        if (doctorReservation.getDatePanelImpl().getModel().getValue() != null) {
            if (this.doctorReservation.getjComboBoxPatient().getSelectedItem() == null)
                patient = "";
            else
                patient = this.users.get(this.doctorReservation.getjComboBoxPatient().getSelectedIndex()).getLogin();
            long tmpDate = ((java.util.Date) this.doctorReservation.getDatePanelImpl().getModel().getValue()).getTime();
            Date date = new java.sql.Date(tmpDate);
            String date1 = new SimpleDateFormat("YYYY-MM-dd").format(date);

            this.time = database.getAvailableReservation(AppFrame.user.getLogin(),
                    patient,
                    Date.valueOf(date1));

            for (int i = 0; i < this.time.size(); i++) {
                this.doctorReservation.getjComboBoxHeure().insertItemAt(time.get(i).toString(), i);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.doctorReservation.getReserverButton()) {
            if (this.doctorReservation.getjComboBoxPatient().getSelectedItem() != null
                    && doctorReservation.getDatePanelImpl().getModel().getValue() != null
                    && this.doctorReservation.getjComboBoxHeure().getSelectedItem() != null) {
                        // database.addReservation()
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez remplir les champs avant de réserver.", "Erreur champ",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
