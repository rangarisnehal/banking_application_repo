package com.snehal.bankDemo.global_exception_handler;

import com.snehal.bankDemo.custom_exceptions.InsufficientBalanceException;
import com.snehal.bankDemo.custom_exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundExceptionHandler(UserNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public String InsufficientBalanceExceptionHandler(InsufficientBalanceException ex){
        return ex.getMessage();
    }
}
