package com.project.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") //kasutaja andmed
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique=true)
    private int id;
    @Column(name = "username", unique=true)
    @NotEmpty(message = "*Please provide a user")
    private String username;
    @Column(name = "email", unique=true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @Column(name = "confirmPassword")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please confirm your password")
    private String confirmPassword;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;
    @Column(name = "lastName")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "location")
    private String location;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column(name = "avatarName")
    private String avatarName;

    public boolean isAdmin() { //kas kasutajal on roll ADMIN?
        if(!roles.isEmpty()) {
            for (Role role : getRoles()) {
                if (role.getRole().equals("ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}