package com.project.rent.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role") //andmetabel, mis ühendab kasutajad mingi konkreetse rolliga
public class UserRole {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "role_id")
    private int roleId;
}
