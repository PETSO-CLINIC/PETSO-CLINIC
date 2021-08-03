package com.example.demo.model;

public class Signup {
    String password;
    String username;
    Long role;

    public Signup(String password, String username, Long role) {
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public Signup() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
