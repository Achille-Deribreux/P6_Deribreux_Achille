package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddTransaction;
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

    public Iterable<Transaction> getAllTransactions(){
        return transactionDAO.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer transactionId){
        return transactionDAO.findById(transactionId);
    }

    //GetAllTransactionsById in a new DTO
    public List<TransactionDTO> getAllTransactionById(Integer userId){
        //TODO : IF param==0 then trhow not found exception
        //TODO : cleanup
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

    public Transaction addTransaction(AddTransaction addTransaction){
        userService.checkUserBalance(addTransaction.getUserId(),addTransaction.getAmount());
        userService.withdrawMoneyFromBalance(addTransaction.getUserId(), addTransaction.getAmount());
        userService.addMoneyToBalance(addTransaction.getReceiverId(),addTransaction.getAmount());
        return transactionDAO.save(
                new Transaction(
                        addTransaction.getUserId(),
                        addTransaction.getReceiverId(),
                        addTransaction.getAmount(),
                        LocalDateTime.now(),
                        addTransaction.getDescription()
                ));
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

    public void deleteTransactionById(Integer transactionId){
        transactionDAO.deleteById(transactionId);
    }


}

