package src.views;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.controllers.DateController;
import src.models.Constants;
import src.models.User;

public class Schedule extends JPanel {
    private User user;
    private String[] week = { null, null, null, null, null };
    private String[] time = { "8h", "8h30", "9h", "9h30", "10h", "10h30", "11h", "11h30", "12h", "12h30", "13h",
            "13h30", "14h", "14h30", "15h", "15h30", "16h", "16h30" };
    private HashMap<Date, User> reservations;
    private JButton semainePre, semaineSuiv;
    private DateController dateController;

    public Schedule(User user) {
        this.user = user;
        this.dateController = new DateController(this);

        int width = 160, height = 30;
        this.setLayout(null);

        this.semainePre = new JButton("< Semaine pré.");
        this.semainePre.setBounds(660, 550, width, height);
        this.semainePre.addActionListener(this.dateController);
        this.add(semainePre);

        this.semaineSuiv = new JButton("Semaine suiv. >");
        this.semaineSuiv.setBounds(850, 550, width, height);
        this.semaineSuiv.addActionListener(this.dateController);
        this.add(semaineSuiv);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 18; i++) {
            g.drawLine(50, 800 / 27 * i + 27, 1010, 800 / 27 * i + 27);
            g.drawString(this.time[i], 10, 800 / 27 * i + 27);
        }
        int pas = (Constants.width - 100) / 5;
        int posX = 0;
        for (int i = 0; i < 6; i++) {
            g.drawLine(50 + posX, 27, 50 + posX, 520);
            if (i < 5)
                g.drawString(this.week[i], 80 + posX, 10);
            posX += pas;
        }
        // for(Entry<String, User> rdv : reservations.entrySet()) {
        //     String[] reservation = rdv.getKey().split("-");
        //     String year = reservation[0];
        //     String month = reservation[1];
        //     String day = reservation[2];

        // }

    }

    public String[] getWeek() {
        return week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }

    public JButton getSemainePre() {
        return semainePre;
    }

    public JButton getSemaineSuiv() {
        return semaineSuiv;
    }

    public void setReservations(HashMap<Date, User> hashMap) {
        this.reservations = hashMap;
    }
}
