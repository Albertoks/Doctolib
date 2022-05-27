package src.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.controllers.PatientReservationController;
import src.models.AppColor;
import src.models.Constants;
import src.models.User;

public class PatientReservation extends JPanel {
        private JPanel doctorPanel, reservationPanel, reservationTitlePanel, dateTimeContainer, footerBtnsPanel;
        private JComboBox<User> doctorsComboBox;
        private CustomButton btnPrevious, btnNext;
        private HashMap<LocalDate, ArrayList<JButton>> availableHourBtns;
        private PatientReservationController doctorReservationController;

        public PatientReservation() {
                this.doctorPanel = new JPanel();
                this.reservationPanel = new JPanel();
                this.reservationTitlePanel = new JPanel();
                this.dateTimeContainer = new JPanel();
                this.footerBtnsPanel = new JPanel();
                this.availableHourBtns = new HashMap<>();

                this.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.setPreferredSize(new Dimension(Constants.panelWidth, Constants.panelHeight));
                this.setMaximumSize(new Dimension(Constants.panelWidth, Constants.panelHeight));

                // ** Doctor choice panel

                this.doctorPanel.setLayout(new BoxLayout(this.doctorPanel, BoxLayout.Y_AXIS));
                this.doctorPanel.setPreferredSize(new Dimension(Constants.panelWidth, 141));
                this.doctorPanel.setMaximumSize(new Dimension(Constants.panelWidth, 141));
                this.doctorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                this.doctorPanel.setBackground(Color.white);

                this.doctorsComboBox = new JComboBox<>();

                JPanel doctorContainer = new JPanel();
                JLabel doctorLabel = new JLabel("Choisissez un docteur");

                doctorContainer.setLayout(new BorderLayout());
                doctorContainer.setPreferredSize(new Dimension(Constants.panelWidth, 50));
                doctorContainer.setMaximumSize(new Dimension(Constants.panelWidth, 50));
                doctorContainer.setBackground(Color.WHITE);

                doctorContainer.add(doctorLabel, BorderLayout.NORTH);
                doctorContainer.add(doctorsComboBox, BorderLayout.CENTER);

                this.doctorPanel.setBackground(Color.WHITE);
                this.doctorPanel.add(doctorContainer);

                // ** Reservation panel

                this.reservationPanel.setLayout(new BoxLayout(this.reservationPanel, BoxLayout.Y_AXIS));
                this.reservationPanel.setPreferredSize(new Dimension(Constants.panelWidth, 430));
                this.reservationPanel.setMaximumSize(new Dimension(Constants.panelWidth, 430));
                this.reservationPanel.setBackground(Color.WHITE);
                this.reservationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                this.reservationTitlePanel.setPreferredSize(new Dimension(Constants.panelWidth, 30));
                this.reservationTitlePanel.setMaximumSize(new Dimension(Constants.panelWidth, 30));
                this.reservationTitlePanel.setBackground(Color.WHITE);

                JLabel reservationTitleLabel = new JLabel("Choisissez un horaire");

                this.reservationTitlePanel.add(reservationTitleLabel);

                this.dateTimeContainer.setLayout(new GridLayout(1, 5));
                this.dateTimeContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.dateTimeContainer.setPreferredSize(new Dimension(900, 340));
                this.dateTimeContainer.setMaximumSize(new Dimension(900, 340));
                this.dateTimeContainer.setBackground(Color.WHITE);

                // Assemble title and date & time panel of the 2nd box
                this.reservationPanel.add(this.reservationTitlePanel);
                this.reservationPanel.add(this.dateTimeContainer);

                // Prev./Suiv. buttons

                JPanel buttonPanel = new JPanel();

                this.btnPrevious = new CustomButton("< Semaine pré.", Color.WHITE, new Dimension(160, 30));
                this.btnNext = new CustomButton("Semaine suiv. >", Color.WHITE, new Dimension(160, 30));
                this.btnPrevious.setEnabled(false);
                this.btnNext.setEnabled(false);

                buttonPanel.add(btnPrevious);
                buttonPanel.add(btnNext);

                this.footerBtnsPanel.setLayout(new BorderLayout());
                this.footerBtnsPanel.setPreferredSize(new Dimension(Constants.panelWidth, 50));
                this.footerBtnsPanel.setMaximumSize(new Dimension(Constants.panelWidth, 50));
                this.footerBtnsPanel.add(buttonPanel, BorderLayout.EAST);

                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                container.setPreferredSize(new Dimension(Constants.panelWidth, Constants.panelHeight));
                container.add(this.doctorPanel);
                container.add(Box.createVerticalStrut(20));
                container.add(this.reservationPanel);
                container.add(Box.createVerticalStrut(20));
                container.add(this.footerBtnsPanel);

                this.add(container, BorderLayout.CENTER);

                this.doctorReservationController = new PatientReservationController(this);
                this.doctorsComboBox.addItemListener(this.doctorReservationController);
                this.btnPrevious.addActionListener(this.doctorReservationController);
                this.btnNext.addActionListener(this.doctorReservationController);
        }

        public CustomButton getPreviousBtn() {
                return btnPrevious;
        }

        public CustomButton getNextBtn() {
                return btnNext;
        }

        public JPanel getDateTimeContainer() {
                return dateTimeContainer;
        }

        public JComboBox<User> getDoctorsComboBox() {
                return doctorsComboBox;
        }

        public HashMap<LocalDate, ArrayList<JButton>> getAvailableHourBtns() {
                return availableHourBtns;
        }

        public void setReservationPanel(HashMap<LocalDate, ArrayList<LocalTime>> availableReservations) {
                for (Map.Entry<LocalDate, ArrayList<LocalTime>> availableReservation : availableReservations
                                .entrySet()) {
                        JPanel dateTimePanel = new JPanel(), datePanel = new JPanel(), timePanel = null,
                                        timePanelContent = null;

                        dateTimePanel.setLayout(new BoxLayout(dateTimePanel, BoxLayout.Y_AXIS));
                        dateTimePanel.setBackground(Color.WHITE);

                        datePanel.setLayout(new BorderLayout());
                        datePanel.setPreferredSize(new Dimension(150, 50));
                        datePanel.setMaximumSize(new Dimension(150, 50));
                        datePanel.setMinimumSize(new Dimension(150, 50));
                        datePanel.setBackground(Color.WHITE);
                        datePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        String dayLabelString = availableReservation.getKey().getDayOfWeek().getDisplayName(
                                        TextStyle.FULL,
                                        Locale.getDefault());
                        dayLabelString = dayLabelString.substring(0, 1).toUpperCase() + dayLabelString.substring(1);
                        String month = availableReservation.getKey().getMonth().getDisplayName(
                                        TextStyle.FULL,
                                        Locale.getDefault());
                        String monthLabelString = availableReservation.getKey().getDayOfMonth() + " "
                                        + month.substring(0, 1).toUpperCase() + month.substring(1);

                        JLabel dayLabel = new JLabel(dayLabelString, SwingConstants.CENTER),
                                        monthLabel = new JLabel(monthLabelString, SwingConstants.CENTER);
                        dayLabel.setBackground(AppColor.PRIMARY);
                        monthLabel.setBackground(AppColor.TERNARY);

                        // Add day and month to date panel
                        datePanel.add(dayLabel, BorderLayout.NORTH);
                        datePanel.add(monthLabel, BorderLayout.SOUTH);

                        if (!this.availableHourBtns.containsKey(availableReservation.getKey()))
                                this.availableHourBtns.put(availableReservation.getKey(), new ArrayList<>());

                        int nbAvailableReservations = availableReservations.get(availableReservation.getKey()).size();

                        if (nbAvailableReservations != 0) {

                                timePanel = new JPanel();
                                timePanelContent = new JPanel();

                                timePanel.setBackground(Color.WHITE);
                                timePanelContent.setBackground(Color.WHITE);

                                int nbCols = nbAvailableReservations <= 9 ? 1 : 2;
                                int nbRows = nbCols == 1 ? nbAvailableReservations
                                                : (int) Math.ceil((double) nbAvailableReservations / (double) 2);

                                timePanelContent.setLayout(new GridLayout(nbRows, nbCols, 10, 5));

                                for (LocalTime availableTime : availableReservation.getValue()) {
                                        int width = nbCols == 1 ? 150 : 70;
                                        CustomButton availableTimeBtn = new CustomButton(availableTime.toString(),
                                                        AppColor.TERNARY, new Dimension(width, 25), 15);
                                        availableTimeBtn.setForeground(Color.WHITE);

                                        this.availableHourBtns.get(availableReservation.getKey())
                                                        .add(availableTimeBtn);

                                        timePanelContent.add(availableTimeBtn);
                                        availableTimeBtn.setActionCommand(LocalDateTime
                                                        .parse(availableReservation.getKey() + "T" + availableTime)
                                                        .toString());
                                        availableTimeBtn.addActionListener(this.doctorReservationController);
                                }
                                // Add time panel to another panel (for layout purpose)
                                timePanel.add(timePanelContent);
                        }

                        // Assemble all date & time panel
                        dateTimePanel.add(datePanel);
                        if (timePanel != null)
                                dateTimePanel.add(timePanel);
                        this.dateTimeContainer.add(dateTimePanel);
                }

                if (!this.btnNext.isEnabled())
                        this.btnNext.setEnabled(true);

                this.dateTimeContainer.revalidate();
        }

        public void setDoctorsList(ArrayList<User> doctors) {
                for (int i = 0; i < doctors.size(); i++) {
                        this.doctorsComboBox.insertItemAt(doctors.get(i), i);
                }
                this.doctorsComboBox.revalidate();
        }
}
