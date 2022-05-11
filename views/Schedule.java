package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import models.Constants;
import models.User;

public class Schedule extends JPanel {
    private User user;
    private String[] week = {null, null, null, null, null};
    private String[] time = { "8h", "8h30", "9h", "9h30", "10h", "10h30", "11h", "11h30", "12h", "12h30", "13h", "13h30", "14h", "14h30", "15h", "15h30", "16h", "16h30"};
    Calendar date = new GregorianCalendar(2022, Calendar.JANUARY, 3);

    public Schedule(User user) {
        this.user = user;

        for(int i = 0; i < 5; i++){
            String day = "";
            String month = "";

            if(date.get(Calendar.DAY_OF_WEEK) == 2) day = "Lundi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 3) day = "Mardi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 4) day = "Mercredi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 5) day = "Jeudi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 6) day = "Vendredi ";

            if(date.get(Calendar.MONTH) == 0) month = " Janvier";
            else if(date.get(Calendar.MONTH) == 1) month = " Février";
            else if(date.get(Calendar.MONTH) == 2) month = " Mars";
            else if(date.get(Calendar.MONTH) == 3) month = " Avril";
            else if(date.get(Calendar.MONTH) == 4) month = " Mai";
            else if(date.get(Calendar.MONTH) == 5) month = " Juin";
            else if(date.get(Calendar.MONTH) == 6) month = " Juillet";
            else if(date.get(Calendar.MONTH) == 7) month = " Aout";
            else if(date.get(Calendar.MONTH) == 8) month = " Septembre";
            else if(date.get(Calendar.MONTH) == 9) month = " Octobre";
            else if(date.get(Calendar.MONTH) == 10) month = " Novembre";
            else if(date.get(Calendar.MONTH) == 11) month = " Décembre";

            this.week[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
        this.afficherTableau();
    }

    public void afficherTableau() {
        //this.setLayout(new GridLayout(16, 7));
        //if (this.user.isAdmin()) {}
        this.repaint();
    }

    public void addWeek() {
        date.add(Calendar.DATE, 2);
        for(int i = 0; i < 5; i++){
            String day = "";
            String month = "";

            if(date.get(Calendar.DAY_OF_WEEK) == 2) day = "Lundi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 3) day = "Mardi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 4) day = "Mercredi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 5) day = "Jeudi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 6) day = "Vendredi ";

            if(date.get(Calendar.MONTH) == 0) month = "Janvier";
            else if(date.get(Calendar.MONTH) == 1) month = "Février";
            else if(date.get(Calendar.MONTH) == 2) month = "Mars";
            else if(date.get(Calendar.MONTH) == 3) month = "Avril";
            else if(date.get(Calendar.MONTH) == 4) month = "Mai";
            else if(date.get(Calendar.MONTH) == 5) month = "Juin";
            else if(date.get(Calendar.MONTH) == 6) month = "Juillet";
            else if(date.get(Calendar.MONTH) == 7) month = "Aout";
            else if(date.get(Calendar.MONTH) == 8) month = "Septembre";
            else if(date.get(Calendar.MONTH) == 9) month = "Octobre";
            else if(date.get(Calendar.MONTH) == 10) month = "Novembre";
            else if(date.get(Calendar.MONTH) == 11) month = "Décembre";

            this.week[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
    }
    public void removeWeek() {
        date.add(Calendar.DATE, -12);
        for(int i = 0; i < 5; i++){
            String day = "";
            String month = "";

            if(date.get(Calendar.DAY_OF_WEEK) == 2) day = "Lundi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 3) day = "Mardi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 4) day = "Mercredi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 5) day = "Jeudi ";
            else if(date.get(Calendar.DAY_OF_WEEK) == 6) day = "Vendredi ";

            if(date.get(Calendar.MONTH) == 0) month = "Janvier";
            else if(date.get(Calendar.MONTH) == 1) month = "Février";
            else if(date.get(Calendar.MONTH) == 2) month = "Mars";
            else if(date.get(Calendar.MONTH) == 3) month = "Avril";
            else if(date.get(Calendar.MONTH) == 4) month = "Mai";
            else if(date.get(Calendar.MONTH) == 5) month = "Juin";
            else if(date.get(Calendar.MONTH) == 6) month = "Juillet";
            else if(date.get(Calendar.MONTH) == 7) month = "Aout";
            else if(date.get(Calendar.MONTH) == 8) month = "Septembre";
            else if(date.get(Calendar.MONTH) == 9) month = "Octobre";
            else if(date.get(Calendar.MONTH) == 10) month = "Novembre";
            else if(date.get(Calendar.MONTH) == 11) month = "Décembre";

            this.week[i] = day + date.get(Calendar.DAY_OF_MONTH) + month;
            date.add(Calendar.DATE, 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int i = 0; i < 18; i++) {
            g.drawLine(50, 800/18 * i + 200, 950, 800/18 * i + 200);
            g.drawString(this.time[i], 10, 800/18 * i + 200);
        }
        int pas = (Constants.width-100)/5;
        int posX = 0;
        for(int i = 0; i < 6; i++){
            g.drawLine(50 + posX, 200, 50 + posX, 950);
            if(i < 5) g.drawString(this.week[i], 80 + posX, 190);
            posX += pas;
        }
    }
}
