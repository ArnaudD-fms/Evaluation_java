package fr.fms.trainingsales.model;

/**
 * Représente un utilisateur connecté pouvant passer commande pour une formation.
 */
public class User {
    private int id;
    private String email;
    private String login;
    private String company;

    public User(String email, String login) {
        this.email = email;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
