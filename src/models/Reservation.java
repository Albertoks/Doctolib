package src.models;

import java.util.Date;

public class Reservation {
    private Date date;
    private String time;
    private User patient;

    public Reservation(Date date, String time, User patient) {
        this.date = date;
        this.time = time;
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
    
}
