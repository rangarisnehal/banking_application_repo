package com.snehal.bankDemo.services;

import com.snehal.bankDemo.custom_exceptions.UserNotFoundException;
import com.snehal.bankDemo.entities.Accounts;
import com.snehal.bankDemo.entities.Users;
import com.snehal.bankDemo.repositories.AccountRepository;
import com.snehal.bankDemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UsersRepository userRepository;

    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Accounts getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Accounts createAccount(Accounts account, Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        account.setUser(user);
        return accountRepository.save(account);
    }

}
