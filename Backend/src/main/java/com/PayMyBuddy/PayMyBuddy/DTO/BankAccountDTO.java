package com.PayMyBuddy.PayMyBuddy.DTO;

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
}
