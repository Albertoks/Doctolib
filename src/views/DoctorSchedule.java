package src.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.controllers.DateController;
import src.models.AppColor;
import src.models.Constants;
import src.models.Reservation;

public class DoctorSchedule extends JPanel {
    private ArrayList<Calendar> rawWeek = new ArrayList<Calendar>();
    private String[] week = { null, null, null, null, null };
    private String[] time = { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
            "13:00",
            "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30" };
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    private JButton semainePre, semaineSuiv;
    private DateController dateController;

    public DoctorSchedule() {
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

        if (this.reservations.size() > 0) {
            for (int i = 0; i < reservations.size(); i++) {
                int reservationX = 0, reservationY = 0, decalX = 45, decalY = 15;

                String monday = "" + this.rawWeek.get(0).get(Calendar.DAY_OF_MONTH);
                String thuesday = "" + this.rawWeek.get(1).get(Calendar.DAY_OF_MONTH);
                String wenesday = "" + this.rawWeek.get(2).get(Calendar.DAY_OF_MONTH);
                String thirsday = "" + this.rawWeek.get(3).get(Calendar.DAY_OF_MONTH);
                String friday = "" + this.rawWeek.get(4).get(Calendar.DAY_OF_MONTH);

                String[] reservation = this.reservations.get(i).getDate().toString().split("-");
                String[] tmpTime = this.reservations.get(i).getTime().split(":");
                String finalTime = tmpTime[0] + ":" + tmpTime[1];

                if (reservation[2].equals(monday))
                    reservationX = 51;
                else if (reservation[2].equals(thuesday))
                    reservationX = 243;
                else if (reservation[2].equals(wenesday))
                    reservationX = 435;
                else if (reservation[2].equals(thirsday))
                    reservationX = 627;
                else if (reservation[2].equals(friday))
                    reservationX = 819;

                if (finalTime.equals(time[0]))
                    reservationY = 28;
                else if (finalTime.equals(time[1]))
                    reservationY = 57;
                else if (finalTime.equals(time[2]))
                    reservationY = 86;
                else if (finalTime.equals(time[3]))
                    reservationY = 115;
                else if (finalTime.equals(time[4]))
                    reservationY = 144;
                else if (finalTime.equals(time[5]))
                    reservationY = 173;
                else if (finalTime.equals(time[6]))
                    reservationY = 202;
                else if (finalTime.equals(time[6]))
                    reservationY = 231;
                else if (finalTime.equals(time[8]))
                    reservationY = 260;
                else if (finalTime.equals(time[9]))
                    reservationY = 289;
                else if (finalTime.equals(time[10]))
                    reservationY = 318;
                else if (finalTime.equals(time[11]))
                    reservationY = 347;
                else if (finalTime.equals(time[12]))
                    reservationY = 376;
                else if (finalTime.equals(time[13]))
                    reservationY = 405;
                else if (finalTime.equals(time[14]))
                    reservationY = 434;
                else if (finalTime.equals(time[15]))
                    reservationY = 463;
                else if (finalTime.equals(time[16]))
                    reservationY = 492;
                else if (finalTime.equals(time[17]))
                    reservationY = 521;

                g.setColor(AppColor.TERNARY);
                g.fillRect(reservationX, reservationY, 191, 28);
                g.setColor(Color.WHITE);
                g.drawString(
                        this.reservations.get(i).getPatient().getFirstname() + " "
                                + this.reservations.get(i).getPatient().getLastname(),
                        reservationX + decalX, reservationY + decalY);
            }
        }
    }

    public ArrayList<Calendar> getRawWeek() {
        return rawWeek;
    }

    public void setRawWeek(ArrayList<Calendar> week) {
        this.rawWeek = week;
    }

    public JButton getSemainePre() {
        return semainePre;
    }

    public JButton getSemaineSuiv() {
        return semaineSuiv;
    }

    public void setReservations(ArrayList<src.models.Reservation> reservations) {
        this.reservations = reservations;
    }

    public String[] getWeek() {
        return week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }
}
