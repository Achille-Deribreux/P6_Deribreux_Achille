package com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions;

public class UserNotFoundException extends RuntimeException{
    String detail;
    public UserNotFoundException(String detail) {
        super("No user found "+detail);
        this.detail = detail;
    }
}
