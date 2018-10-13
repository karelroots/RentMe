package com.project.rent.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "userslog")
public class UserLog implements Comparable<UserLog> {

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

    @Column(name = "time")
    private String datetime;

    private String username;

    @Override
    public int compareTo(UserLog o) {
        LocalDateTime ldt1 = LocalDateTime.parse(o.getDatetime());
        LocalDateTime ldt2 = LocalDateTime.parse(this.getDatetime());
        if(ldt2.isAfter(ldt1)) {
            return -1;
        }
        return 1;
    }
}
