package src.models;

import java.util.Date;

public class Reservation {
    private Date date;
    private String time;
    private User user;

    public Reservation(Date date, String time, User user) {
        this.date = date;
        this.time = time;
        this.user = user;
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
        return user;
    }

    public void setPatient(User patient) {
        this.user = patient;
    }

}
