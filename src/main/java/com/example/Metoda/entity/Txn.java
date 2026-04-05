package com.example.Metoda.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "txn_table")
@Data
public class Txn {

    @Id
    private int id;

    private String status;

    @Column(name = "payment_type")
    private String paymentType;

    private String channel;
    private String risk;
    private String currency;
    private double amount;
}