package entities;

public class User {
     protected int id;
     protected String nom_complet;
     protected String login;
     protected String password;
     protected String role;

    public User() {
        this.role="ROLE_ADMIN";
    }

    public User(String nom_complet, String login, String password) {
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
        this.role="ROLE_ADMIN";
    }

    public User(int id, String nom_complet, String login, String password) {
        this.id = id;
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
        this.role="ROLE_ADMIN";
    }

     
     
    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
     
     
    
}
