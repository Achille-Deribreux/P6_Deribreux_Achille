package com.PayMyBuddy.PayMyBuddy.Model.Specific;

public class AddTransaction {
    int userId;
    int receiverId;
    int amount;
    String description;

    public AddTransaction(int userId, int receiverId, int amount, String description) {
        this.userId = userId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
