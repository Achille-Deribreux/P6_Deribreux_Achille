package com.PayMyBuddy.PayMyBuddy.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bankaccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "accountnumber")
    private int accountNumber;

    @Column(name = "bank")
    private String bank;

    public BankAccount(int id, int userId, int accountNumber, String bank) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public BankAccount(int userId, int accountNumber, String bank) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public BankAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && userId == that.userId && accountNumber == that.accountNumber && Objects.equals(bank, that.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, accountNumber, bank);
    }
}
