package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String petName;
    private String imgUrl;
    private String description;
    Timestamp timestamp;

    @ManyToOne
    AppUser appUser;

    public Blog(long id, String petName, String imgUrl, String description) {
        this.id = id;
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
    }


    public Blog(String petName, String imgUrl, String description, Timestamp timestamp) {
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Blog(String petName, String imgUrl, String description, Timestamp timestamp, AppUser appUser) {
        this.petName = petName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.timestamp = timestamp;
        this.appUser = appUser;
    }

    public void setTimestamp(Timestamp timestamp) {
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

}
