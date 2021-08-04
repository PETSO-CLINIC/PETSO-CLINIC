package com.example.demo.model;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalTime;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String petName;
    private String imgUrl;
    private String description;
    LocalTime timestamp;

    @ManyToOne
    AppUser appUser;



    public Blog(long id, String petName, String imgUrl, String description) {
        this.id = id;
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
    }


    public Blog(String petName, String imgUrl, String description, LocalTime timestamp) {
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.timestamp = timestamp;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public Blog(String petName, String imgUrl, String description, LocalTime timestamp, AppUser appUser) {
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.timestamp = timestamp;
        this.appUser = appUser;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public Blog() {
    }

    public long getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
