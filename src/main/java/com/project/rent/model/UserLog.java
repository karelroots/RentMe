package com.project.rent.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userslog")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true)
    private int id;

    @Column(name = "browser")
    private String browser;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "os")
    private String os;

    @Column(name = "landing_page")
    private String landingPage;

}
