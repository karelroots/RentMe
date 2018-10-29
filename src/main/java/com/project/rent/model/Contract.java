package com.project.rent.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contracts") // Rendilepingute andmed
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contract_id", unique=true)
    private int id;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "price")
    private String price;

    @Column(name = "location")
    private String location;

    @Column(name = "rent_date")
    private String rentDateTime;

    @Column(name = "return_date")
    private String returnDateTime;

    @Column(name = "item_picture")
    private String pictureName;

}
