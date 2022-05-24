package src.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import src.views.AppFrame;

public class Database {
    private static Database instance = null;

    private Connection cnx;
    private PreparedStatement stmt;
    private String dbName = "doctolib";
    private String login = "root";
    private String password = "";
    private String strUrl = "jdbc:mysql://localhost:3306/" + dbName;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.cnx = DriverManager.getConnection(strUrl, login, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non chargé !");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public User login(String login, String password) {
        try {
            String query = "Selectfrom users where login = ? And password = ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();

            if (results.next())
                return new User(results.getString("lastname"), results.getString("firstname"),
                        results.getString("login"),
                        results.getString("password"),
                        results.getBoolean("admin"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean register(String login, String password, String lastname, String firstname) {
        try {
            String query = "INSERT INTO `users`(`login`, `password`, `firstname`, `lastname`) SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT login FROM users WHERE login = ?)";
            stmt = cnx.prepareStatement(query);

            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, lastname);
            stmt.setString(4, firstname);
            stmt.setString(5, login);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private boolean registerAdmin(String login, String password, String lastname, String firstname) {
        try {
            String query = "INSERT INTO `users`(`login`, `password`, `firstname`, `lastname`, `admin`) SELECT ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT login FROM users WHERE login = ?)";
            stmt = cnx.prepareStatement(query);

            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, lastname);
            stmt.setString(4, firstname);
            stmt.setBoolean(5, true);
            stmt.setString(6, login);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<Reservation> getDoctorReservations(String doctorLogin, Date firstDate, Date lastDate) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            String query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.patient = users.id AND doctor = (SELECT id FROM users WHERE login = ?) AND date >= ? AND date <= ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, doctorLogin);
            stmt.setDate(2, firstDate);
            stmt.setDate(3, lastDate);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                reservations.add(new Reservation(results.getDate("date"), results.getString("time"),
                        new User(results.getString("firstname"), results.getString("lastname"), null, null, null)));

            return reservations;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, ArrayList<Reservation>> getPatientReservations(String patientLogin) {
        HashMap<String, ArrayList<Reservation>> reservations = new HashMap<String, ArrayList<Reservation>>();
        reservations.put("old", new ArrayList<Reservation>());
        reservations.put("soon", new ArrayList<Reservation>());
        try {
            // Old reservation
            String query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date < NOW()";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, patientLogin);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                reservations.get("old").add(new Reservation(results.getDate("date"), results.getString("time"),
                        new User(results.getString("lastname"), results.getString("firstname"), null, null, null)));

            // Reservation coming soon
            query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date >= NOW()";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, patientLogin);
            results = stmt.executeQuery();

            while (results.next())
                reservations.get("soon").add(new Reservation(results.getDate("date"), results.getString("time"),
                        new User(results.getString("lastname"), results.getString("firstname"), null, null, null)));

            return reservations;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<LocalDate, ArrayList<LocalTime>> getAvailableReservation(String doctor, String patient) {
        return this.getAvailableReservation(doctor, patient, 0);
    }

    public HashMap<LocalDate, ArrayList<LocalTime>> getAvailableReservation(String doctor, String patient,
            int diffWeek) {
        HashMap<LocalDate, ArrayList<LocalTime>> availableReservations = new HashMap<LocalDate, ArrayList<LocalTime>>();
        ArrayList<Reservation> takenReservation = new ArrayList<Reservation>();

        LocalDate currentTabDate = LocalDate.now().plusWeeks(diffWeek);

        Date firstDate = null, lastDate = null;

        for (int i = 0; i < Constants.nbDayInTab; i++) {
            LocalTime time = LocalTime.of(Constants.startHour, Constants.startMinute);

            if (i == 0) {
                while (time.compareTo(LocalTime.now()) < 0)
                    time = time.plusMinutes(Constants.gapBetweenRDV);
                if (time.compareTo(LocalTime.of(Constants.endHour, Constants.endMinute)) >= 0) {
                    currentTabDate = currentTabDate.plusDays(1);
                    time = LocalTime.of(Constants.startHour, Constants.startMinute);
                }
            }

            if (currentTabDate.getDayOfWeek() == DayOfWeek.SATURDAY)
                currentTabDate = currentTabDate.plusDays(2);
            else if (currentTabDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                currentTabDate = currentTabDate.plusDays(1);

            availableReservations.put(currentTabDate, new ArrayList<LocalTime>());

            while (time.compareTo(LocalTime.of(Constants.endHour, Constants.endMinute)) <= 0) {
                availableReservations.get(currentTabDate).add(time);
                time = time.plusMinutes(Constants.gapBetweenRDV);
            }

            if (i == 0)
                firstDate = Date.valueOf(currentTabDate);
            else if (i == Constants.nbDayInTab - 1)
                lastDate = Date.valueOf(currentTabDate);

            currentTabDate = currentTabDate.plusDays(1);
        }

        try {
            // Get all reservation taken by this doctor
            String query = "SELECT date, time FROM reservations WHERE (reservations.doctor = (SELECT id FROM users WHERE login = ?) OR reservations.patient = (SELECT id FROM users WHERE login = ?)) AND date > ? AND date <= ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, doctor);
            stmt.setString(2, patient);
            stmt.setDate(3, firstDate);
            stmt.setDate(4, lastDate);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                takenReservation
                        .add(new Reservation(results.getDate("date"), results.getString("time"), AppFrame.user));

            // Remove already taken reservation
            for (Reservation reservation : takenReservation) {
                // System.out.println(reservation.getDate() + " " + reservation.getTime());
                availableReservations.get(LocalDate.parse(reservation.getDate().toString()))
                        .remove(LocalTime.parse(reservation.getTime(), DateTimeFormatter.ISO_TIME));
            }

            return availableReservations;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<LocalTime> getAvailableReservation(String doctor, String patient, Date takenDate) {
        ArrayList<LocalTime> takenReservation = new ArrayList<LocalTime>();

        LocalTime time = LocalTime.of(Constants.startHour, Constants.startMinute);

        if (takenDate.compareTo(Date.valueOf(LocalDate.now())) == 0)
            while (time.compareTo(LocalTime.now()) < 0)
                time = time.plusMinutes(Constants.gapBetweenRDV);

        while (time.compareTo(LocalTime.of(Constants.endHour, Constants.endMinute)) <= 0) {
            takenReservation.add(time);
            time = time.plusMinutes(Constants.gapBetweenRDV);
        }

        System.out.println(doctor);
        System.out.println(patient);
        System.out.println(takenDate);

        try {
            // Get all reservation taken by this doctor
            String query = "SELECT time FROM reservations WHERE (reservations.doctor = (SELECT id FROM users WHERE login = ?) OR reservations.patient = (SELECT id FROM users WHERE login = ?)) AND date = ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, doctor);
            stmt.setString(2, patient);
            stmt.setDate(3, takenDate);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                System.out.println(results.getTime("time"));
                LocalTime reservedTime = LocalTime.parse(results.getString("time"));
                if (takenReservation.contains(reservedTime))
                    takenReservation.remove(reservedTime);
            }

            return takenReservation;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
     * Get all doctors
     */
    public ArrayList<User> getDoctors() {
        ArrayList<User> doctors = new ArrayList<User>();
        try {
            String query = "SELECT lastname, firstname, login FROM users WHERE admin = ?";
            stmt = cnx.prepareStatement(query);
            stmt.setBoolean(1, true);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                doctors.add(new User(results.getString("lastname"), results.getString("firstname"),
                        results.getString("login"), null, true));

            return doctors;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
     * Get all patients
     */
    public ArrayList<User> getPatients() {
        ArrayList<User> patient = new ArrayList<User>();
        try {
            String query = "SELECT lastname, firstname, login FROM users WHERE admin = ?";
            stmt = cnx.prepareStatement(query);
            stmt.setBoolean(1, false);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                patient.add(new User(results.getString("lastname"), results.getString("firstname"),
                        results.getString("login"), null, true));

            return patient;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void logout() {
        try {
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Echec de la déconnexion !" + e.getMessage());
        }
    }
}
