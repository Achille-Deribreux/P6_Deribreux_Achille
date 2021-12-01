package com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions;

public class NotEnoughBalanceException extends RuntimeException{
    public NotEnoughBalanceException() {
        super("Not enough money on your account");
    }
}
