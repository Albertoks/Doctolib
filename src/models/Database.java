package src.models;

import java.nio.file.FileStore;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;

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
            String query = "Select * from users where login = ? And password = ?";
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

    public boolean registerAdmin(String login, String password, String lastname, String firstname) {
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

    public ArrayList<Reservation> getDoctorReservations(String doctor, Date firstDate, Date lastDate) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            String query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.patient = users.id AND doctor = (SELECT id FROM users WHERE login = ?) AND date >= ? AND date <= ?";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, doctor);
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

    public HashMap<String, ArrayList<Reservation>> getPatientReservations(String patient) {
        HashMap<String, ArrayList<Reservation>> reservations = new HashMap<String, ArrayList<Reservation>>();
        reservations.put("old", new ArrayList<Reservation>());
        reservations.put("soon", new ArrayList<Reservation>());
        try {
            // Old reservation
            String query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date < NOW()";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, patient);
            ResultSet results = stmt.executeQuery();

            while (results.next())
                reservations.get("old").add(new Reservation(results.getDate("date"), results.getString("time"),
                        new User(results.getString("lastname"), results.getString("firstname"), null, null, null)));

            // Reservation coming soon
            query = "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date >= NOW()";
            stmt = cnx.prepareStatement(query);
            stmt.setString(1, patient);
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

    /*
     * public HashMap<Date, ArrayList<Date>> getAvailableReservation(String patient)
     * {
     * HashMap<Date, ArrayList<Date>> reservations = new HashMap<Date,
     * ArrayList<Date>>();
     * reservations.put("old", new ArrayList<Date>());
     * reservations.put("soon", new ArrayList<Date>());
     * 
     * try {
     * // Old reservation
     * String query =
     * "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date < NOW()"
     * ;
     * statement = conn.prepareStatement(query);
     * statement.setString(1, patient);
     * ResultSet results = statement.executeQuery();
     * 
     * while (results.next())
     * reservations.get("old").add(new Reservation(results.getDate("date"),
     * results.getString("time"),
     * new User(results.getString("firstname"), results.getString("lastname"), null,
     * null, null)));
     * 
     * // Reservation coming soon
     * query =
     * "SELECT date, time, firstname, lastname FROM reservations, users WHERE reservations.doctor = users.id AND reservations.patient = (SELECT id FROM users WHERE login = ?) AND date >= NOW()"
     * ;
     * statement = conn.prepareStatement(query);
     * statement.setString(1, patient);
     * results = statement.executeQuery();
     * 
     * while (results.next()) {
     * reservations.get("soon").add(new Reservation(results.getDate("date"),
     * results.getString("time"),
     * new User(results.getString("firstname"), results.getString("lastname"), null,
     * null, null)));
     * }
     * return reservations;
     * 
     * } catch (SQLException e) {
     * System.out.println(e.getMessage());
     * }
     * return null;
     * }
     */

    public void logout() {
        try {
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Echec de la déconnexion !" + e.getMessage());
        }
    }
}
