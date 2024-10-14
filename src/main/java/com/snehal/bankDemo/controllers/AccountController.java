package com.snehal.bankDemo.controllers;

import com.snehal.bankDemo.entities.Accounts;
import com.snehal.bankDemo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Accounts> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Accounts> createAccount(@RequestBody Accounts account, @PathVariable Long id) {
        return ResponseEntity.ok(accountService.createAccount(account, id));
    }

}
