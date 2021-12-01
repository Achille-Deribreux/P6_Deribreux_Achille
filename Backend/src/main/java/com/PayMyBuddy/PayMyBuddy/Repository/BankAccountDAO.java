package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountDAO extends CrudRepository<BankAccount,Integer> {
    Iterable<BankAccount>findByUserId(Integer userId);
    Optional<BankAccount>findByAccountNumber(Integer accountNumber);
}
