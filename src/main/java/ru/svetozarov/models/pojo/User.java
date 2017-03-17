package ru.svetozarov.models.pojo;

import javax.persistence.*;

/**
 * Created by Шмыга on 23.02.2017.
 */
@Entity
@Table(name = "user", schema = "example")
public class User {
    @Id
    private int idUser;
    @Column(name = "login")
    private String login;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(int idUser, String login, String password, String role) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
