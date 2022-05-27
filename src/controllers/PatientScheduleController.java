package src.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import src.models.AppColor;
import src.models.Database;
import src.models.Reservation;
import src.views.AppFrame;
import src.views.PatientSchedule;

public class PatientScheduleController implements ActionListener {
    private ArrayList<Reservation> old, soon;
    private PatientSchedule patientSchedule;

    public PatientScheduleController(PatientSchedule patientSchedule) {
        this.patientSchedule = patientSchedule;
        HashMap<String, ArrayList<Reservation>> map = Database.getInstance()
                .getPatientReservations(AppFrame.user.getLogin());
        this.old = map.get("old");
        this.soon = map.get("soon");
    }

    public void loadSchedule() {
        this.patientSchedule.setPage(1);
        this.patientSchedule.cleanListeRDV();
        if (this.soon.size() > 0) {
            this.patientSchedule.afficherRDV(this.soon);
        }
        this.patientSchedule.getaVenir().setBackground(AppColor.TERNARY);
        this.patientSchedule.getPasse().setBackground(Color.WHITE);
        this.patientSchedule.setOld(false);
        this.patientSchedule.getaVenir().setForeground(Color.white);
        this.patientSchedule.getPasse().setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.patientSchedule.getaVenir()) {
            this.patientSchedule.setPage(1);
            this.patientSchedule.cleanListeRDV();
            if (this.soon.size() > 0) {
                this.patientSchedule.afficherRDV(this.soon);
            }
            this.patientSchedule.getaVenir().setBackground(AppColor.TERNARY);
            this.patientSchedule.getPasse().setBackground(Color.WHITE);
            this.patientSchedule.setOld(false);
            this.patientSchedule.getaVenir().setForeground(Color.white);
            this.patientSchedule.getPasse().setForeground(Color.BLACK);

        }
        if (e.getSource() == this.patientSchedule.getPasse()) {
            this.patientSchedule.setPage(1);
            this.patientSchedule.cleanListeRDV();
            if (this.old.size() > 0) {
                this.patientSchedule.afficherRDV(this.old);
            }
            this.patientSchedule.getaVenir().setBackground(Color.WHITE);
            this.patientSchedule.getPasse().setBackground(AppColor.TERNARY);
            this.patientSchedule.setOld(true);
            this.patientSchedule.getaVenir().setForeground(Color.BLACK);
            this.patientSchedule.getPasse().setForeground(Color.white);

        }
        if (e.getSource() == this.patientSchedule.getPrevious()) {
            this.patientSchedule.getNext().setEnabled(true);
            this.patientSchedule.cleanListeRDV();
            this.patientSchedule.setPage(this.patientSchedule.getPage() - 1);

            if (this.patientSchedule.isOld()) {
                this.patientSchedule.afficherRDV(this.old);
            } else {
                this.patientSchedule.afficherRDV(this.soon);
            }
            if (this.patientSchedule.getPage() == 1)
                this.patientSchedule.getPrevious().setEnabled(false);
            else
                this.patientSchedule.getPrevious().setEnabled(true);

        }

        if (e.getSource() == this.patientSchedule.getNext()) {
            this.patientSchedule.getPrevious().setEnabled(true);
            this.patientSchedule.cleanListeRDV();
            this.patientSchedule.setPage(this.patientSchedule.getPage() + 1);

            if (this.patientSchedule.isOld())
                this.patientSchedule.afficherRDV(this.old);
            else
                this.patientSchedule.afficherRDV(this.soon);

            if (!this.patientSchedule.hasNext())
                this.patientSchedule.getNext().setEnabled(false);
            else
                this.patientSchedule.getNext().setEnabled(true);
        }

    }

}
