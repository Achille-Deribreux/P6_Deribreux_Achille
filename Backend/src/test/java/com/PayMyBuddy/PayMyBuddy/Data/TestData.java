package com.PayMyBuddy.PayMyBuddy.Data;

import com.PayMyBuddy.PayMyBuddy.Configuration.UserDetailsImpl;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Model.User;

import java.time.LocalDateTime;
import java.util.Date;

public class TestData {

    public static UserDetailsImpl getPrincipal() {
        return new UserDetailsImpl(1, "test@test.com", "abcd");
    }

    public static User getSampleUser(){
        return new User(123,"Achille","Deribreux","a@d.be",100,"xxx");
    }

    public static Transaction getSampleTransaction(){
        return new Transaction(3,4,100, null,"hello");
    }

    public static CreditBankAccountDTO getSampleCreditBankAccountDTO(){
        return new CreditBankAccountDTO(3,100,12345678);
    }

    public static AddConnection getSampleAddConnection(){
        return new AddConnection("a@d.be",3);
    }

    public static BankAccount getSampleBankAccount(){
        return new BankAccount(1,356789,"DTO");
    }
}
