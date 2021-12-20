package com.PayMyBuddy.PayMyBuddy.Model;

import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "senderid")
    private int senderId;

    @Column(name = "receiverid")
    private int receiverId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "datestamp")
    LocalDateTime datestamp;

    @Column(name="description")
    private String description;

    public Transaction(int id, int senderId, int receiverId, double amount, LocalDateTime datestamp, String description) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.datestamp = datestamp;
        this.description = description;
    }

    public Transaction(int senderId, int receiverId, double amount, LocalDateTime datestamp, String description) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.datestamp = datestamp;
        this.description = description;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(LocalDateTime datestamp) {
        this.datestamp = datestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && senderId == that.senderId && receiverId == that.receiverId && Double.compare(that.amount, amount) == 0 && Objects.equals(Formatter.convertDate(datestamp), Formatter.convertDate(that.datestamp)) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, receiverId, amount, datestamp, description);
    }
}

