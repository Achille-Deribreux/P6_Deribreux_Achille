package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDAO extends CrudRepository<Transaction, Integer> {

    Iterable<Transaction> findBySenderId(Integer senderId);
    Iterable<Transaction> findByReceiverId(Integer receiverId);
}
