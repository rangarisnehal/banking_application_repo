package com.snehal.bankDemo.repositories;

import com.snehal.bankDemo.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Long> {
}
