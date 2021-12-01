package com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions;

public class BankAccountNotFoundException extends RuntimeException{
    String detail;
    public BankAccountNotFoundException(String detail){
        super("Bank Account not found " +detail);
        this.detail = detail;
    }
}
