package com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("User already exists with this email");
    }
}
