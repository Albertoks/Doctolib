package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JOptionPane;

import src.models.Database;
import src.models.User;
import src.views.AppFrame;
import src.views.PatientReservation;

public class PatientReservationController implements ActionListener, ItemListener {
    private Database database;
    private PatientReservation patientReservation;
    private long diffWeek = 0;
    private User selectedDoctor;
    private LocalDate selectedDate;
    private LocalTime selectedTime;

    public PatientReservationController(PatientReservation patientReservation) {
        this.database = Database.getInstance();
        this.patientReservation = patientReservation;
        patientReservation.setDoctorsList(database.getDoctors());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.patientReservation.getPreviousBtn()
                || e.getSource() == this.patientReservation.getNextBtn()) {
            if (e.getSource() == this.patientReservation.getPreviousBtn()) {
                --diffWeek;
            } else if (e.getSource() == this.patientReservation.getNextBtn())
                ++diffWeek;

            if (diffWeek == 0)
                this.patientReservation.getPreviousBtn().setEnabled(false);
            else
                this.patientReservation.getPreviousBtn().setEnabled(true);

            this.patientReservation.getDateTimeContainer().removeAll();
            this.patientReservation
                    .setReservationPanel(this.database.getAvailableReservation(this.selectedDoctor.getLogin(),
                            AppFrame.user.getLogin(), diffWeek));
        } else {
            // JButton reservationButton = (JButton) e.getSource();
            String[] reservationDateTime = e.getActionCommand().split("T");
            this.selectedDate = LocalDate.parse(reservationDateTime[0]);
            this.selectedTime = LocalTime.parse(reservationDateTime[1]);

            String day = this.selectedDate.getDayOfWeek().getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault());
            String month = this.selectedDate.getMonth().getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault());
            day = day.substring(0, 1).toUpperCase() + day.substring(1);
            month = month.substring(0, 1).toUpperCase() + month.substring(1);

            String dayMonth = day + " " + this.selectedDate.getDayOfMonth() + " " + month;
            String minute = this.selectedTime.getMinute() == 0 ? "0" + this.selectedTime.getMinute()
                    : "" + this.selectedTime.getMinute();
            String time = this.selectedTime.getHour() + ":" + minute;

            int reply = JOptionPane.showConfirmDialog(null,
                    "Souhaitez-vous réserver votre rendez-vous le " + dayMonth + " à " + time + " ?",
                    "Réservation d'un rendez-vous",
                    JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                Boolean success = database.addReservation(this.selectedDoctor.getLogin(),
                        AppFrame.user.getLogin(),
                        Date.valueOf(this.selectedDate),
                        Time.valueOf(this.selectedTime));
                if (success) {
                    JOptionPane.showMessageDialog(null, "Votre rendez-vous à été réservé.");
                    this.patientReservation.getDoctorsComboBox().setSelectedItem(null);
                    this.patientReservation.getDateTimeContainer().removeAll();
                    this.patientReservation.getDateTimeContainer().revalidate();
                } else
                    JOptionPane.showMessageDialog(null,
                            "Une erreur est survenue lors de la réservation de votre date. Veuillez réessayer ultérieurement.",
                            "Erreur réservation",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.selectedDoctor = (User) this.patientReservation.getDoctorsComboBox().getSelectedItem();
            patientReservation.getDateTimeContainer().removeAll();
            patientReservation.setReservationPanel(this.database.getAvailableReservation(this.selectedDoctor.getLogin(),
                    AppFrame.user.getLogin(), diffWeek));
        }
    }

}
