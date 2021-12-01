package com.PayMyBuddy.PayMyBuddy.DTO;

public class CreditBankAccountDTO {
    Integer userId;
    Integer amount;
    Integer accountNumber;

    public CreditBankAccountDTO() {
    }

    public CreditBankAccountDTO(Integer userId, Integer amount, Integer accountNumber) {
        this.userId = userId;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
