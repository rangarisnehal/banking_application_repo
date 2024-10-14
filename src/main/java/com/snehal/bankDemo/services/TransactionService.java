package com.snehal.bankDemo.services;

import com.snehal.bankDemo.custom_exceptions.InsufficientBalanceException;
import com.snehal.bankDemo.custom_exceptions.UserNotFoundException;
import com.snehal.bankDemo.entities.Accounts;
import com.snehal.bankDemo.entities.Transactions;
import com.snehal.bankDemo.repositories.AccountRepository;
import com.snehal.bankDemo.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transactions transferBalance(Long fromAccountId, Long toAccountId, Double amount) {
        LOGGER.info("Initiating balance transfer from account {} to account {}", fromAccountId, toAccountId);

        Accounts fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new UserNotFoundException("No account found to debited " + fromAccountId));
        Accounts toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new UserNotFoundException("NO account Found to Credit "+ toAccountId));

        // Check if the from account has sufficient balance
        if (fromAccount.getBalance() < amount) {
            LOGGER.error("Insufficient balance in account {}", fromAccountId);
            throw new InsufficientBalanceException("Insufficent Balance int acount");
        }

        // Debit the from account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);
        LOGGER.info("Debited {} from account {}", amount, fromAccountId);

        // Credit the to account
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);
        LOGGER.info("Credited {} to account {}", amount, toAccountId);

        // Create a new transaction
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setType("debit");
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setTimestamp(LocalDateTime.now());
        Transactions savedTransaction = transactionRepository.save(transaction);
        LOGGER.info("Transaction created successfully");
        return savedTransaction;
    }

    public List<Transactions> getTransactionLogs(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByTimestampBetween(start, end);
    }

}
