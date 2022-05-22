package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import src.views.Schedule;

public class DateController implements ActionListener {
    private Schedule schedule;
    private Calendar date = new GregorianCalendar(2022, Calendar.JANUARY, 3);
    private String[] tmpWeek = { null, null, null, null, null };

    

    public DateController(Schedule schedule) {
        this.schedule = schedule;
        for (int i = 0; i < 5; i++) {
            String day = "";
            String month = "";

            if (date.get(Calendar.DAY_OF_WEEK) == 2)
                day = "Lundi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 3)
                day = "Mardi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 4)
                day = "Mercredi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 5)
                day = "Jeudi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 6)
                day = "Vendredi ";

            if (date.get(Calendar.MONTH) == 0)
                month = " Janvier";
            else if (date.get(Calendar.MONTH) == 1)
                month = " Février";
            else if (date.get(Calendar.MONTH) == 2)
                month = " Mars";
            else if (date.get(Calendar.MONTH) == 3)
                month = " Avril";
            else if (date.get(Calendar.MONTH) == 4)
                month = " Mai";
            else if (date.get(Calendar.MONTH) == 5)
                month = " Juin";
            else if (date.get(Calendar.MONTH) == 6)
                month = " Juillet";
            else if (date.get(Calendar.MONTH) == 7)
                month = " Aout";
            else if (date.get(Calendar.MONTH) == 8)
                month = " Septembre";
            else if (date.get(Calendar.MONTH) == 9)
                month = " Octobre";
            else if (date.get(Calendar.MONTH) == 10)
                month = " Novembre";
            else if (date.get(Calendar.MONTH) == 11)
                month = " Décembre";

            this.tmpWeek[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
        schedule.setWeek(tmpWeek);
        this.afficherTableau();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.schedule.getSemainePre()) {
            this.removeWeek();
        } else if(e.getSource() == this.schedule.getSemaineSuiv()) {
            this.addWeek();
        }
        
    }

    public void addWeek() {
        date.add(Calendar.DATE, 2);
        for (int i = 0; i < 5; i++) {
            String day = "";
            String month = "";

            if (date.get(Calendar.DAY_OF_WEEK) == 2)
                day = "Lundi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 3)
                day = "Mardi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 4)
                day = "Mercredi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 5)
                day = "Jeudi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 6)
                day = "Vendredi ";

            if (date.get(Calendar.MONTH) == 0)
                month = " Janvier";
            else if (date.get(Calendar.MONTH) == 1)
                month = " Février";
            else if (date.get(Calendar.MONTH) == 2)
                month = " Mars";
            else if (date.get(Calendar.MONTH) == 3)
                month = " Avril";
            else if (date.get(Calendar.MONTH) == 4)
                month = " Mai";
            else if (date.get(Calendar.MONTH) == 5)
                month = " Juin";
            else if (date.get(Calendar.MONTH) == 6)
                month = " Juillet";
            else if (date.get(Calendar.MONTH) == 7)
                month = " Aout";
            else if (date.get(Calendar.MONTH) == 8)
                month = " Septembre";
            else if (date.get(Calendar.MONTH) == 9)
                month = " Octobre";
            else if (date.get(Calendar.MONTH) == 10)
                month = " Novembre";
            else if (date.get(Calendar.MONTH) == 11)
                month = " Décembre";

            this.tmpWeek[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
        schedule.setWeek(tmpWeek);
        this.afficherTableau();
    }

    public void removeWeek() {
        date.add(Calendar.DATE, -12);
        for (int i = 0; i < 5; i++) {
            String day = "";
            String month = "";

            if (date.get(Calendar.DAY_OF_WEEK) == 2)
                day = "Lundi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 3)
                day = "Mardi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 4)
                day = "Mercredi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 5)
                day = "Jeudi ";
            else if (date.get(Calendar.DAY_OF_WEEK) == 6)
                day = "Vendredi ";

            if (date.get(Calendar.MONTH) == 0)
                month = " Janvier";
            else if (date.get(Calendar.MONTH) == 1)
                month = " Février";
            else if (date.get(Calendar.MONTH) == 2)
                month = " Mars";
            else if (date.get(Calendar.MONTH) == 3)
                month = " Avril";
            else if (date.get(Calendar.MONTH) == 4)
                month = " Mai";
            else if (date.get(Calendar.MONTH) == 5)
                month = " Juin";
            else if (date.get(Calendar.MONTH) == 6)
                month = " Juillet";
            else if (date.get(Calendar.MONTH) == 7)
                month = " Aout";
            else if (date.get(Calendar.MONTH) == 8)
                month = " Septembre";
            else if (date.get(Calendar.MONTH) == 9)
                month = " Octobre";
            else if (date.get(Calendar.MONTH) == 10)
                month = " Novembre";
            else if (date.get(Calendar.MONTH) == 11)
                month = " Décembre";

            this.tmpWeek[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
        schedule.setWeek(tmpWeek);
        this.afficherTableau();
    }

    public void afficherTableau() {
        // if (this.user.isAdmin()) {}
        schedule.repaint();
    }
    
}
