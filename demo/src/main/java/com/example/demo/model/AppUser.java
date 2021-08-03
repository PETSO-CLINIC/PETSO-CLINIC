package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    private String firstname;
    private String lastname;
    private Date dob;
    private String typeOfPet;


    public AppUser() {
    }

    public AppUser(String firstname, String lastname, Date dob, String typeOfPet) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.typeOfPet = typeOfPet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTypeOfPet() {
        return typeOfPet;
    }

    public void setTypeOfPet(String typeOfPet) {
        this.typeOfPet = typeOfPet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
