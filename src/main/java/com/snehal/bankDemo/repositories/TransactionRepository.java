package com.snehal.bankDemo.repositories;

import com.snehal.bankDemo.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
