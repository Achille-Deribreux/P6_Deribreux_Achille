package com.PayMyBuddy.PayMyBuddy.Data;

import com.PayMyBuddy.PayMyBuddy.Configuration.UserDetailsImpl;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;

import java.time.LocalDateTime;
import java.util.*;

public class TestData {

    public static UserDetailsImpl getPrincipal() {
        return new UserDetailsImpl(1, "test@test.com", "abcd");
    }

    public static User getSampleUser(){
        return new User(123,"Achille","Deribreux","a@d.be",100,"xxx");
    }

    public static UserDTO getSampleUserDTO(){
        return new UserDTO(123,"Achille","Deribreux","a@d.be",100);
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
        return new BankAccount(1,123456789,"CBC");
    }

    public static Optional<BankAccount> getSampleOptionnalBankAccount(){
        return Optional.of(new BankAccount(1,123456789,"CBC"));
    }

    public static List<BankAccount> getSampleBankAccountList() {
        return new ArrayList<>(Arrays.asList(
                new BankAccount(1, 123, "CBC"),
                new BankAccount(1, 12345, "CBC"),
                new BankAccount(1, 12346, "CBC")
        ));
    }

    public static List<Connection> getSampleConnectionList(){
        return new ArrayList<>(Arrays.asList(
                new Connection(1,2),
                new Connection(1,3),
                new Connection(1,4)
        ));
    }

    public static List<User> getUserListForSampleConnectionList(){
        return new ArrayList<>(Arrays.asList(
                new User(2,"A","A","A",100,"mdp"),
                new User(3,"B","B","B",100,"mdp"),
                new User(4,"C","C","C",100,"mdp")
        ));
    }

    public static Connection getSampleConnection(){
        return new Connection(1,2);
    }

    public static Connection getSampleInverseConnection(){
        return new Connection(2,1);
    }

    public static User getSampleUserForConnection(){
        return new User(2,"Achille","Deribreux","a@d.be",100,"xxx");
    }

    public static List<User> getUserListForSampleNonConnectionList(){
        return new ArrayList<>(Arrays.asList(
                new User(2,"A","A","A",100,"mdp"),
                new User(3,"B","B","B",100,"mdp"),
                new User(4,"C","C","C",100,"mdp"),
                new User(5,"D","D","D",100,"mdp")
        ));
    }

    public static List<UserDTO> getNonConnectionUser(){
        return new ArrayList<>(Arrays.asList(
                new UserDTO(5,"D","D","D",100)
        ));
    }

    public static List<User> getNonConnectionUserOutDTO(){
        return new ArrayList<>(Arrays.asList(
                new User(5,"D","D","D",100,"mdp")
        ));
    }

    public static Iterable<User> getAllUsersIterable() {
        return Collections.singleton(new User(5, "D", "D", "D", 100, "mdp"));
    }

    public static List<User> getAllUsersList() {
        return new ArrayList<>(Arrays.asList(new User(5, "D", "D", "D", 100, "mdp")));
    }

    public static List<User> getSampleUserList(){
        return new ArrayList<>(Arrays.asList(
                new User(2,"A","A","A",100,"mdp"),
                new User(3,"B","B","B",100,"mdp"),
                new User(4,"C","C","C",100,"mdp"),
                new User(5,"D","D","D",100,"mdp")
        ));
    }

    public static List<UserDTO> getSampleUserDTOList(){
        return new ArrayList<>(Arrays.asList(
                new UserDTO(2,"A","A","A",100),
                new UserDTO(3,"B","B","B",100),
                new UserDTO(4,"C","C","C",100),
                new UserDTO(5,"D","D","D",100)
        ));
    }

    //Transactions
    public static Iterable<Transaction> getSampleForfindBySenderId(){
        return Collections.singleton(new Transaction(1,2,1000, LocalDateTime.now(),"hl"));
    }
    public static Iterable<Transaction> getSampleForfindByReceiverId(){
        return Collections.singleton(new Transaction(2,1,500, LocalDateTime.now(),"hl"));
    }

    public static List<TransactionDTO> getSampleForTransactionDTO(){
        return new ArrayList<>(Arrays.asList(
                new TransactionDTO("X X","-1000.0€","hl",Formatter.convertDate(LocalDateTime.now())),
                new TransactionDTO("X X","+500.0€","hl", Formatter.convertDate(LocalDateTime.now()))
        ));
    }

    public static List<Connection> getSampleConnectionListForJPATest(){
        return new ArrayList<>(Arrays.asList(
                new Connection(1,1,2),
                new Connection(2,1,3),
                new Connection(3,1,4)
        ));
    }
}
