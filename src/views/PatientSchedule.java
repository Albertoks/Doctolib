package src.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.controllers.PatientScheduleController;
import src.models.AppColor;
import src.models.Constants;
import src.models.Reservation;

public class PatientSchedule extends JPanel {

    private CustomButton aVenir;
    private CustomButton passe;
    private Color colorSet;
    private JPanel listeRdv;
    private PatientScheduleController patientScheduleController;

    private JPanel footerPanel, paginationPanel;
    private CustomButton previous, next;
    private JLabel currentPage;

    private int page;
    private int currentMax;
    private boolean isOld;
    private ArrayList<Reservation> liste;

    private int currentNbReservation;

    public PatientSchedule() {

        footerPanel = new JPanel();
        paginationPanel = new JPanel();
        currentMax = 1;
        page = 1;
        paginationPanel.setLayout(new GridBagLayout());
        isOld = false;
        this.setPreferredSize(new Dimension(Constants.panelWidth, Constants.panelHeight));
        this.setMinimumSize(new Dimension(Constants.panelWidth, Constants.panelHeight));

        listeRdv = new JPanel();
        listeRdv.setPreferredSize(new Dimension(Constants.panelWidth, 590));
        listeRdv.setMaximumSize(new Dimension(Constants.panelWidth, 590));

        this.previous = new CustomButton("<", Color.WHITE, new Dimension(40, 30));
        this.next = new CustomButton(">", Color.WHITE, new Dimension(40, 30));
        this.currentPage = new JLabel("");

        paginationPanel.add(currentPage);
        paginationPanel.setBackground(Color.WHITE);
        this.previous.setEnabled(false);

        this.footerPanel.setLayout(new BorderLayout());
        this.footerPanel.add(previous, BorderLayout.WEST);
        this.footerPanel.add(paginationPanel, BorderLayout.CENTER);
        this.footerPanel.add(next, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();

        this.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridBagLayout());

        this.setBackground(Color.WHITE);
        listeRdv.setBackground(Color.WHITE);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        colorSet = new Color(38, 171, 168);
        aVenir = new CustomButton("?? venir", colorSet, new Dimension(200, 40), false);
        passe = new CustomButton("Pass??", Color.WHITE, new Dimension(200, 40), false);

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(aVenir, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(passe, c);

        // ---------------- PANEL AFICHAGE RDV

        this.add(buttonPanel, BorderLayout.NORTH);

        this.patientScheduleController = new PatientScheduleController(this);
        aVenir.addActionListener(this.patientScheduleController);
        passe.addActionListener(this.patientScheduleController);
        previous.addActionListener(patientScheduleController);
        next.addActionListener(patientScheduleController);

        this.patientScheduleController.loadSchedule();
    }

    public void afficherRDV(ArrayList<Reservation> liste) {
        this.liste = liste;
        JLabel labelDate;
        JLabel labelDoctor;

        JPanel panelDate;
        JPanel panelDoctor;

        JPanel rdv;
        String date;

        if (this.hasNext()) {
            this.next.setEnabled(true);
            currentNbReservation = 4;
        } else {
            this.next.setEnabled(false);
            currentNbReservation = liste.size() > 4 ? Math.abs(liste.size() - 4) : liste.size();
        }

        if (this.hasPrevious())
            this.previous.setEnabled(true);
        else
            this.previous.setEnabled(false);

        currentMax = (page * 4 > liste.size()) ? liste.size() : page * 4;

        this.footerPanel.setPreferredSize(new Dimension(239, 40 + (108 * (4 - currentNbReservation))));
        this.footerPanel.setMaximumSize(new Dimension(239, 40 + (108 * (4 - currentNbReservation))));

        this.currentPage.setText(4 * (page - 1) + 1 + " - " + currentMax + " sur " + liste.size() + " ");

        listeRdv.setLayout(new BoxLayout(listeRdv, BoxLayout.Y_AXIS));
        // listeRdv.setLayout(new GridBagLayout());

        for (int i = 4 * (page - 1); i < currentMax; i++) {
            LocalDate localDate = LocalDate.parse(liste.get(i).getDate().toString());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(liste.get(i).getDate());

            rdv = new JPanel();
            rdv.setLayout(new BoxLayout(rdv, BoxLayout.Y_AXIS));
            rdv.setBackground(Color.WHITE);
            rdv.setMinimumSize(new Dimension(Constants.width, 80));
            rdv.setBorder(new EmptyBorder(30, 0, 0, 0));

            panelDate = new JPanel();
            panelDoctor = new JPanel();

            panelDate.setMaximumSize(new Dimension(891, 39));
            panelDoctor.setMaximumSize(new Dimension(891, 39));

            panelDate.setPreferredSize(new Dimension(891, 39));
            panelDoctor.setPreferredSize(new Dimension(891, 39));

            date = localDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH)) + " "
                    + calendar.get(Calendar.YEAR);

            labelDate = new JLabel("Le " + date + " ?? "
                    + liste.get(i).getTime().substring(0, liste.get(i).getTime().lastIndexOf(":")));
            labelDoctor = new JLabel(liste.get(i).getPatient().toString());

            labelDate.setBorder(new EmptyBorder(0, 15, 0, 0));
            labelDate.setForeground(Color.WHITE);
            labelDoctor.setBorder(new EmptyBorder(0, 15, 0, 0));

            panelDate.setLayout(new BorderLayout());
            panelDoctor.setLayout(new BorderLayout());

            panelDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            panelDoctor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            panelDate.setBackground(AppColor.PRIMARY_DARK);
            panelDoctor.setBackground(Color.WHITE);

            panelDate.add(labelDate, BorderLayout.WEST);
            panelDoctor.add(labelDoctor, BorderLayout.WEST);

            rdv.add(panelDate);
            rdv.add(panelDoctor);

            this.listeRdv.add(rdv);

        }

        this.listeRdv.add(footerPanel);
        footerPanel.setBorder(new EmptyBorder(21 + (108 * (4 - currentNbReservation)), 0, 0, 0));
        footerPanel.setBackground(Color.WHITE);
        listeRdv.setBackground(Color.WHITE);

        this.add(listeRdv, BorderLayout.CENTER);

    }

    public void cleanListeRDV() {
        this.listeRdv.removeAll();
        this.listeRdv.revalidate();
        this.listeRdv.repaint();
    }

    public CustomButton getaVenir() {
        return aVenir;
    }

    public void setaVenir(CustomButton aVenir) {
        this.aVenir = aVenir;
    }

    public CustomButton getPasse() {
        return passe;
    }

    public void setPasse(CustomButton passe) {
        this.passe = passe;
    }

    public CustomButton getPrevious() {
        return previous;
    }

    public void setPrevious(CustomButton previous) {
        this.previous = previous;
    }

    public CustomButton getNext() {
        return next;
    }

    public void setNext(CustomButton next) {
        this.next = next;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean isOld) {
        this.isOld = isOld;
    }

    public boolean hasNext() {
        return page * 4 < this.liste.size();
    }

    public boolean hasPrevious() {
        return page > 1;
    }

}
