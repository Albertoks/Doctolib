package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import src.models.Database;
import src.views.AppFrame;
import src.views.Schedule;

public class DateController implements ActionListener {
    private Schedule schedule;
    private Database database;
    private ArrayList<Calendar> rawWeek = new ArrayList<Calendar>();

    public DateController(Schedule schedule) {
        this.schedule = schedule;
        this.database = Database.getInstance();

        for (int i = 0; i < 5; i++) {
            Calendar cal = Calendar.getInstance();

            if (cal.get(Calendar.DAY_OF_WEEK) == 7)
                cal.add(Calendar.DATE, 2);
            else if (cal.get(Calendar.DAY_OF_WEEK) == 1)
                cal.add(Calendar.DATE, 1);
            else if (cal.get(Calendar.DAY_OF_WEEK) == 3)
                cal.add(Calendar.DATE, -1);
            else if (cal.get(Calendar.DAY_OF_WEEK) == 4)
                cal.add(Calendar.DATE, -2);
            else if (cal.get(Calendar.DAY_OF_WEEK) == 5)
                cal.add(Calendar.DATE, -3);
            else if (cal.get(Calendar.DAY_OF_WEEK) == 6)
                cal.add(Calendar.DATE, -4);

            cal.add(Calendar.DATE, i);
            this.rawWeek.add(cal);
        }

        this.setWeek(rawWeek);
        Date firstDate = new java.sql.Date(this.rawWeek.get(0).getTimeInMillis());
        Date lastDate = new java.sql.Date(this.rawWeek.get(4).getTimeInMillis());

        schedule.setReservations(database.getDoctorReservations(AppFrame.user.getLogin(), firstDate, lastDate));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.schedule.getSemainePre()) {
            this.removeWeek();
        } else if (e.getSource() == this.schedule.getSemaineSuiv()) {
            this.addWeek();
        }
    }

    public void addWeek() {
        for (int i = 0; i < 5; i++) {
            Calendar tmpDate = rawWeek.get(i);
            tmpDate.add(Calendar.DATE, 7);
            this.rawWeek.remove(i);
            this.rawWeek.add(i, tmpDate);
        }
        this.setWeek(this.rawWeek);
        Date firstDate = new java.sql.Date(this.rawWeek.get(0).getTimeInMillis());
        Date lastDate = new java.sql.Date(this.rawWeek.get(4).getTimeInMillis());

        schedule.setReservations(database.getDoctorReservations(AppFrame.user.getLogin(), firstDate, lastDate));
    }

    public void removeWeek() {
        for (int i = 0; i < 5; i++) {
            Calendar tmpDate = rawWeek.get(i);
            tmpDate.add(Calendar.DATE, -7);
            this.rawWeek.remove(i);
            this.rawWeek.add(i, tmpDate);
        }
        this.setWeek(rawWeek);
        Date firstDate = new java.sql.Date(this.rawWeek.get(0).getTimeInMillis());
        Date lastDate = new java.sql.Date(this.rawWeek.get(4).getTimeInMillis());

        schedule.setReservations(database.getDoctorReservations(AppFrame.user.getLogin(), firstDate, lastDate));
    }

    public void afficherTableau() {
        // if (this.user.isAdmin()) {}
        schedule.repaint();
    }

    private void setWeek(ArrayList<Calendar> rawWeek) {
        String[] week = { null, null, null, null, null };
        for (int i = 0; i < 5; i++) {
            String day = "";
            String month = "";

            if (rawWeek.get(i).get(Calendar.DAY_OF_WEEK) == 2)
                day = "Lundi ";
            else if (rawWeek.get(i).get(Calendar.DAY_OF_WEEK) == 3)
                day = "Mardi ";
            else if (rawWeek.get(i).get(Calendar.DAY_OF_WEEK) == 4)
                day = "Mercredi ";
            else if (rawWeek.get(i).get(Calendar.DAY_OF_WEEK) == 5)
                day = "Jeudi ";
            else if (rawWeek.get(i).get(Calendar.DAY_OF_WEEK) == 6)
                day = "Vendredi ";

            if (rawWeek.get(i).get(Calendar.MONTH) == 0)
                month = " Janvier";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 1)
                month = " Février";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 2)
                month = " Mars";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 3)
                month = " Avril";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 4)
                month = " Mai";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 5)
                month = " Juin";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 6)
                month = " Juillet";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 7)
                month = " Aout";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 8)
                month = " Septembre";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 9)
                month = " Octobre";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 10)
                month = " Novembre";
            else if (rawWeek.get(i).get(Calendar.MONTH) == 11)
                month = " Décembre";

            week[i] = day + rawWeek.get(i).get(Calendar.DAY_OF_MONTH) + month;
            // System.out.println(day + rawWeek.get(i).get(Calendar.DAY_OF_MONTH) + month);
            schedule.setWeek(week);
            schedule.setRawWeek(rawWeek);

            this.afficherTableau();
        }
    }
}
