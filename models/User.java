package models;

public class User {
    private String prenom, login, password;
    private Boolean admin;

    public User(String prenom, String login, String password, Boolean admin) {
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return admin;
    }
}