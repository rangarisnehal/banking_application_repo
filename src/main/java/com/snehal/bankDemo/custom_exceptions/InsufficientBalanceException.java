package com.snehal.bankDemo.custom_exceptions;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String msg){
        super(msg);
    }
}
