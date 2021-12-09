package com.PayMyBuddy.PayMyBuddy.DTO;

import java.util.Objects;

public class BankAccountDTO {
    Integer userid;
    Integer accountNumber;
    String bank;

    public BankAccountDTO() {
    }

    public BankAccountDTO(Integer userid, Integer accountNumber, String bank) {
        this.userid = userid;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
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
        BankAccountDTO that = (BankAccountDTO) o;
        return Objects.equals(userid, that.userid) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(bank, that.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, accountNumber, bank);
    }
}
