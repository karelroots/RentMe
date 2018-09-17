package com.project.rent.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "esemed")
public class Ese {
    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1
    )
    private int id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nimi;

    @Column(columnDefinition = "tüüp")
    private String tüüp;

    @Column(columnDefinition = "kirjeldus")
    private String kirjeldus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getTüüp() {
        return tüüp;
    }

    public void setTüüp(String tüüp) {
        this.tüüp = tüüp;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public void setKirjeldus(String kirjeldus) {
        this.kirjeldus = kirjeldus;
    }
}