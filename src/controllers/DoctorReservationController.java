package src.controllers;

import java.util.ArrayList;

import javax.swing.JComboBox;

import src.models.Database;
import src.models.User;
import src.views.AppFrame;
import src.views.DoctorReservation;

public class DoctorReservationController {
    private DoctorReservation doctorReservation;
    private Database database;
    private ArrayList<User> users;
    public DoctorReservationController(DoctorReservation doctorReservation){
        database =  Database.getInstance();
        users=database.getPatients();
        this.doctorReservation=doctorReservation;

        for(int i=0;i<users.size();i++){
            this.doctorReservation.getjComboBoxPatient().insertItemAt(users.get(i).toString(),i);
        }
        
    }
}
