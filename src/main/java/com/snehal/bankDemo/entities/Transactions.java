package com.snehal.bankDemo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    @Column(name = "transaction_type")
    private String type;

    private double amount;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Accounts fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Accounts toAccount;
}
