package com.project.rent;

public class Kasutaja {
    private int id;
    private String eesnimi, perenimi, email;

    public Kasutaja(int id, String eesnimi, String perenimi, String email) {
        this.id = id;
        this.eesnimi = eesnimi;
        this.perenimi = perenimi;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Kasutaja[id=%d, eesnimi='%s', perenimi='%s, email='%s']",
                id, eesnimi, perenimi, email);
    }
}

