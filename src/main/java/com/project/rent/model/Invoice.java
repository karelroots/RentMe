package com.project.rent.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoices") // Arvete andmed
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id", unique=true)
    private int id;

    @Column(name = "payee_id")
    private int payeeId;

    @Column(name = "payer_id")
    private int payerId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "sum")
    private int sum;

    @Column(name = "time")
    private String datetime;

    @Column(name = "pay_by")
    private String payBy;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "paid_at")
    private String paidAt;

    private String payee;
    private String payer;
}
