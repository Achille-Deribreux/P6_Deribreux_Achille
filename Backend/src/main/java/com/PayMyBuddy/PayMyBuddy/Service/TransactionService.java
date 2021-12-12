package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Repository.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    UserService userService;

    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<TransactionDTO> getAllTransactionById(Integer userId){
        if(userId == 0){
            return Collections.emptyList();
        }
        //TODO : cleanup and use collectors
        List<TransactionDTO> allTransactions = new ArrayList<>();
        Iterable<Transaction> negativeTransactions = getTransactionsBySenderId(userId);
        Iterable<Transaction> positiveTransactions = getTransactionsByReceiverId(userId);

        for(Transaction neg : negativeTransactions){
            if(neg.getReceiverId() != 0) {
                allTransactions.add(new TransactionDTO(userService.getUserNameById(neg.getReceiverId()),Formatter.convertAmount(false, neg.getAmount()), neg.getDescription(), neg.getDatestamp()));
            }
            else if(neg.getReceiverId() == 0){
                allTransactions.add(new TransactionDTO("Bank",Formatter.convertAmount(false,neg.getAmount()),neg.getDescription(),neg.getDatestamp()));
            }
        }
        for(Transaction pos : positiveTransactions){
            if(pos.getSenderId() != 0) {
                allTransactions.add(new TransactionDTO(userService.getUserNameById(pos.getSenderId()), Formatter.convertAmount(true, pos.getAmount()), pos.getDescription(), pos.getDatestamp()));
            }else if(pos.getSenderId() == 0){
                allTransactions.add(new TransactionDTO("Bank",Formatter.convertAmount(true,pos.getAmount()),pos.getDescription(),pos.getDatestamp()));
            }
        }
        List<TransactionDTO> sortedList = allTransactions.stream()
                .sorted(Comparator.comparing(TransactionDTO :: getDatestamp).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    public Iterable<Transaction> getTransactionsBySenderId(Integer senderId){
        return transactionDAO.findBySenderId(senderId);
    }
    public Iterable<Transaction> getTransactionsByReceiverId(Integer receiverId){
        return transactionDAO.findByReceiverId(receiverId);
    }

    public Transaction addTransaction(Transaction addTransaction){
        userService.checkUserBalance(addTransaction.getSenderId(),addTransaction.getAmount());
        userService.withdrawMoneyFromBalance(addTransaction.getSenderId(), addTransaction.getAmount());
        userService.addMoneyToBalance(addTransaction.getReceiverId(),addTransaction.getAmount());
        addTransaction.setDatestamp(LocalDateTime.now());
        return transactionDAO.save(addTransaction);
    }

    public Transaction addCreditBankAccount(CreditBankAccountDTO creditBankAccountDTO){
        userService.addMoneyToBalance(creditBankAccountDTO.getUserId(), creditBankAccountDTO.getAmount());
        return transactionDAO.save(
                new Transaction(
                        0,
                        creditBankAccountDTO.getUserId(),
                        creditBankAccountDTO.getAmount(),
                        LocalDateTime.now(),
                        "Money added from my account N° " + creditBankAccountDTO.getAccountNumber()
                ));
    }

    public Transaction withdrawCreditBankAccount(CreditBankAccountDTO creditBankAccountDTO){
        userService.checkUserBalance(creditBankAccountDTO.getUserId(),creditBankAccountDTO.getAmount());
        userService.withdrawMoneyFromBalance(creditBankAccountDTO.getUserId(), creditBankAccountDTO.getAmount());
        return transactionDAO.save(
                new Transaction(
                        creditBankAccountDTO.getUserId(),
                        0,
                        creditBankAccountDTO.getAmount(),
                        LocalDateTime.now(),
                        "Money send to my account N° " + creditBankAccountDTO.getAccountNumber()
                ));
    }
}

