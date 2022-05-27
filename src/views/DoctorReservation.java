package src.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import src.controllers.DoctorReservationController;
import src.models.AppColor;
import src.models.Constants;

import src.models.DateLabelFormatter;

import java.awt.Component;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;

public class DoctorReservation extends JPanel {
    private JComboBox<String> jComboBoxPatient;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JComboBox<String> jComboBoxHeure;
    private CustomButton reserverButton;

    public DoctorReservation() {

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setPreferredSize(new Dimension(Constants.panelWidth, Constants.panelHeight));
        this.setMaximumSize(new Dimension(Constants.panelWidth, Constants.panelHeight));

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLUE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(Constants.panelWidth, 51));
        buttonPanel.setMaximumSize(new Dimension(Constants.panelWidth, 51));

        reserverButton = new CustomButton("Reserver", AppColor.TERNARY, new Dimension(160, 30));
        reserverButton.setForeground(Color.WHITE);

        buttonPanel.add(reserverButton, BorderLayout.EAST);

        JPanel panelPatient = new JPanel();
        JPanel panelRdv = new JPanel();

        JPanel containerRdv = new JPanel();
        JPanel containerPatient = new JPanel();

        JPanel containeroptionsPatient = new JPanel();
        JPanel containeroptionsHours = new JPanel();
        JPanel containeroptionsDate = new JPanel();

        JLabel labelRdv = new JLabel(" Choisissez la date et l'heure du rendez-vous");
        JLabel labelPatient = new JLabel();

        if (AppFrame.user.isAdmin())
            labelPatient.setText("Choisissez un patient");
        else
            labelPatient.setText("Choisissez un docteur");

        JLabel textDate = new JLabel("Date");
        JLabel textHour = new JLabel("Heure");

        jComboBoxPatient = new JComboBox<>();
        DoctorReservationController doctorReservationController = new DoctorReservationController(this);
        reserverButton.addActionListener(doctorReservationController);
        AppFrame.mainFrame.getRootPane().setDefaultButton(this.reserverButton);

        Properties properties = new Properties();
        properties.put("text.day", "Day");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model, properties);

        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        jComboBoxHeure = new JComboBox<>();

        model.addChangeListener(doctorReservationController);

        panelRdv.setLayout(new BoxLayout(panelRdv, BoxLayout.Y_AXIS));
        panelRdv.setPreferredSize(new Dimension(Constants.panelWidth, 256));
        panelRdv.setMaximumSize(new Dimension(Constants.panelWidth, 256));

        panelRdv.setBackground(Color.WHITE);
        panelRdv.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        containerRdv.setLayout(new BorderLayout());
        containerRdv.setPreferredSize(new Dimension(Constants.panelWidth, 30));
        containerRdv.setMaximumSize(new Dimension(Constants.panelWidth, 30));

        containerPatient.setLayout(new BorderLayout());

        containeroptionsHours.setLayout(new BorderLayout());
        containeroptionsHours.setPreferredSize(new Dimension(200, 40));
        containeroptionsHours.setMaximumSize(new Dimension(200, 40));

        containeroptionsDate.setLayout(new BorderLayout());
        containeroptionsDate.setPreferredSize(new Dimension(200, 40));
        containeroptionsDate.setMaximumSize(new Dimension(200, 40));

        containeroptionsPatient.setLayout(new BorderLayout());

        containerRdv.setBackground(Color.WHITE);
        containerRdv.add(labelRdv);

        panelRdv.add(containerRdv);

        panelRdv.add(Box.createVerticalStrut(30));
        containeroptionsDate.add(textDate, BorderLayout.NORTH);
        containeroptionsDate.add(datePicker, BorderLayout.CENTER);

        panelRdv.add(containeroptionsDate);
        containeroptionsHours.add(textHour, BorderLayout.NORTH);
        containeroptionsHours.add(jComboBoxHeure, BorderLayout.CENTER);

        panelRdv.add(Box.createVerticalStrut(30));

        panelRdv.add(containeroptionsHours);

        panelPatient.setLayout(new BoxLayout(panelPatient, BoxLayout.Y_AXIS));
        panelPatient.setPreferredSize(new Dimension(Constants.panelWidth, 141));
        panelPatient.setMaximumSize(new Dimension(Constants.panelWidth, 141));

        panelPatient.setBackground(Color.white);
        panelPatient.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        containerPatient.setPreferredSize(new Dimension(Constants.panelWidth, 50));
        containerPatient.setMaximumSize(new Dimension(Constants.panelWidth, 50));

        containerPatient.add(labelPatient, BorderLayout.NORTH);
        containerPatient.add(jComboBoxPatient, BorderLayout.CENTER);
        containerPatient.setBackground(Color.WHITE);

        panelPatient.add(Box.createVerticalStrut(10));

        panelPatient.setBackground(Color.WHITE);
        panelPatient.add(containerPatient);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setPreferredSize(new Dimension(Constants.panelWidth, Constants.panelHeight));
        container.add(Box.createVerticalStrut(54));
        container.add(panelPatient);
        container.add(Box.createVerticalStrut(54));
        container.add(panelRdv);

        this.add(container, BorderLayout.CENTER);

        buttonPanel.setBorder(new EmptyBorder(0, 0, 21, 0));
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    public JComboBox<String> getjComboBoxPatient() {
        return jComboBoxPatient;
    }

    public void setjComboBoxPatient(JComboBox<String> jComboBoxPatient) {
        this.jComboBoxPatient = jComboBoxPatient;
    }

    public JDatePickerImpl getDatePanelImpl() {
        return this.datePicker;
    }

    public UtilDateModel getModel() {
        return model;
    }

    public JDatePanelImpl getDatePanel() {
        return datePanel;
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    public JComboBox<String> getjComboBoxHeure() {
        return jComboBoxHeure;
    }

    public void setjComboBoxHeure(JComboBox<String> jComboBoxHeure) {
        this.jComboBoxHeure = jComboBoxHeure;
        this.revalidate();
    }

    public CustomButton getReserverButton() {
        return reserverButton;
    }
}
