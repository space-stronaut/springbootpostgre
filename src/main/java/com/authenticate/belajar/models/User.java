package com.authenticate.belajar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @Column(name = "uuid")
    private String id;
    private String username;
    private String email;
    private String password;
    @Column(name = "isActive")
    private String isActive;

    // Getter dan Setter untuk id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter dan Setter untuk username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter dan Setter untuk email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter dan Setter untuk password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter dan Setter untuk isActive
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}
