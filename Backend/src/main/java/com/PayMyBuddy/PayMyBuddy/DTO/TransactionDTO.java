package com.PayMyBuddy.PayMyBuddy.DTO;

import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    private String connection;
    private String amount;
    private String description;
    private String datestamp;

    public TransactionDTO(String connection, String amount, String description, String datestamp) {
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

    public String getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(connection, that.connection) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(datestamp, that.datestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connection, amount, description, datestamp);
    }
}

