package com.snehal.bankDemo.dtos;

import com.snehal.bankDemo.entities.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String accountNumber;

    private Double balance;

    public static AccountDto accountToAccountDto(Accounts accounts){
        AccountDto accountDto = new AccountDto(accounts.getAccountNumber(), accounts.getBalance());
        return accountDto;
    }

    public static Accounts accountDtoToAccount(AccountDto accountDto){
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setBalance(accountDto.getBalance());

        return accounts;
    }

}
