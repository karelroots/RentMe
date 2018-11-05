package com.project.rent.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contractoffers") // Rendilepingute andmed
public class ContractOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contractoffer_id", unique=true)
    private int id;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "offer_id")
    private int offerId;

    @Column(name = "offeruser_id")
    private int offerUserId;

    @Column(name = "wish_id")
    private int wishId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "price")
    private int price;

    @Column(name = "location")
    private String location;

    @Column(name = "offer_date")
    private String offerDateTime;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "item_picture")
    private String pictureName;

    private String userName;
    private String owner;
}
