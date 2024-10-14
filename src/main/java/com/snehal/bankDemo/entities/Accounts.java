package com.snehal.bankDemo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
