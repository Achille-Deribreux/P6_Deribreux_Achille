package com.PayMyBuddy.PayMyBuddy.Exceptions;

import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.BankAccountNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.NotEnoughBalanceException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.CustomErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    private static final Logger logger = LogManager.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        logger.error("User not found");
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<Object> handleNotEnoughBalanceException(NotEnoughBalanceException e){
        logger.error("not enough balance");
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<Object> handleBankAccountNotFoundException(BankAccountNotFoundException e){
        logger.error("BankAccount not found");
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }

}
