package com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions;

public class IncorrectLoginException extends RuntimeException{
    String detail;

    public IncorrectLoginException(String detail) {
        super("Incorrect email/password" + detail);
        this.detail = detail;
    }
}
