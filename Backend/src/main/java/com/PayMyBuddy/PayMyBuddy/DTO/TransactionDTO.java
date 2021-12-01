package com.PayMyBuddy.PayMyBuddy.DTO;

import java.time.LocalDateTime;

public class TransactionDTO {
    private String connection;
    private String amount;
    private String description;
    private LocalDateTime datestamp;

    public TransactionDTO(String connection, String amount, String description, LocalDateTime datestamp) {
        this.connection = connection;
        this.amount = amount;
        this.description = description;
        this.datestamp = datestamp;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(LocalDateTime datestamp) {
        this.datestamp = datestamp;
    }
}

