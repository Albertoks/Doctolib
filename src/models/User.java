package src.models;

public class User {
    private String lastname, firstname, login, password;
    private Boolean admin;

    public User(String lastname, String firstname, String login, String password, Boolean admin) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.firstname+" "+this.lastname;
    }
}