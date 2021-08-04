package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    private String firstnamePetOwner;
    private String lastnamePetOwner;
    private String dobPetOwner;
    private String typeOfPet;

    @OneToMany(mappedBy = "appUser")
    List<Blog> blogs;


    public AppUser() {
    }

    public AppUser(String firstnamePetOwner, String lastnamePetOwner, String dobPetOwner, String typeOfPet) {
        this.firstnamePetOwner = firstnamePetOwner;
        this.lastnamePetOwner = lastnamePetOwner;
        this.dobPetOwner = dobPetOwner;
        this.typeOfPet = typeOfPet;
    }

    public Long getId() {
        return id;
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

    public String getFirstnamePetOwner() {
        return firstnamePetOwner;
    }

    public void setFirstnamePetOwner(String firstnamePetOwner) {
        this.firstnamePetOwner = firstnamePetOwner;
    }

    public String getLastnamePetOwner() {
        return lastnamePetOwner;
    }

    public void setLastnamePetOwner(String lastnamePetOwner) {
        this.lastnamePetOwner = lastnamePetOwner;
    }

    public String getDobPetOwner() {
        return dobPetOwner;
    }

    public void setDobPetOwner(String dobPetOwner) {
        this.dobPetOwner = dobPetOwner;
    }

    public String getTypeOfPet() {
        return typeOfPet;
    }

    public void setTypeOfPet(String typeOfPet) {
        this.typeOfPet = typeOfPet;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
