package src.models;

import java.sql.*;

public class Database {
    private static Database instance = null;

    private Connection conn;
    private PreparedStatement statement;
    private String dbName = "doctolib";
    private String login = "root";
    private String password = "";
    private String strUrl = "jdbc:mysql://localhost:3306/" + dbName;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(strUrl, login, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non chargé !");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Echec de la déconnexion !" + e.getMessage());
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public boolean register(String login, String password, String nom, String prenom) {
        try {
            String query = "INSERT INTO `users` (`login`, `password`, `nom`, `prenom`, `admin`) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, nom);
            statement.setString(4, prenom);
            statement.setBoolean(5, false);

            if (statement.execute())
                return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean registerAdmin(String login, String password, String nom, String prenom) {
        try {
            String query = "INSERT INTO `users` (`login`, `password`, `nom`, `prenom`, `admin`) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, nom);
            statement.setString(4, prenom);
            statement.setBoolean(5, true);

            statement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User login(String login, String password) {
        try {
            String query = "Select * from users where login = ? And password = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                return new User(results.getString("prenom"), results.getString("login"), results.getString("password"),
                        results.getBoolean("admin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
