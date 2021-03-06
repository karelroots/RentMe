package com.project.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "offers") // Pakkumiste andmed
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id", unique=true)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "price")
    private int price;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String datetime;

    @Column(name = "rent_period")
    private String rentPeriod;

    @Column(name = "item_picture")
    private String pictureName;

    private String userName;

}
