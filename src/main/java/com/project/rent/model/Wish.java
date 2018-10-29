package com.project.rent.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wishes") // Soovide andmed
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wish_id", unique=true)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String datetime;

    @Column(name = "item_picture")
    private String pictureName;

    private String userName;

}
