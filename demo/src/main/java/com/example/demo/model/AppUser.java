package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    long id;
    String username;
    String password;
    Date dob;
    String firstname;
    String lastname;

    public AppUser(long id, String username, String password, Date dob, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public AppUser(String username, String password, Date dob, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @OneToMany(mappedBy = "appUser")
    List<Blog> blogs;
    public AppUser() {
    }


    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
