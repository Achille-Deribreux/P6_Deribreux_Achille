package com.PayMyBuddy.PayMyBuddy.Controller;

import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value="/addtransaction")
    public ResponseEntity<Transaction>addTransaction(@RequestBody Transaction addTransaction){
        if(addTransaction.getSenderId() == 0 || addTransaction.getReceiverId() == 0 || addTransaction.getAmount() == 0 ){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(transactionService.addTransaction(addTransaction), HttpStatus.CREATED);
        }
    }

    @GetMapping(value="/getalltransactionsbyid")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@RequestParam(value="id")Integer id){
        return new ResponseEntity<>(transactionService.getAllTransactionById(id),HttpStatus.OK);
    }

    @PostMapping(value="addMoneyFromAccount")
    public ResponseEntity<Transaction>addMoneyFromAccount(@RequestBody CreditBankAccountDTO creditBankAccountDTO){
        if(creditBankAccountDTO.getUserId() == null || creditBankAccountDTO.getAccountNumber() == null || creditBankAccountDTO.getAmount() == null ){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(transactionService.addCreditBankAccount(creditBankAccountDTO), HttpStatus.CREATED);
        }
    }

    @PostMapping(value="sendMoneyToAccount")
    public ResponseEntity<Transaction>sendMoneyToAccount(@RequestBody CreditBankAccountDTO creditBankAccountDTO){
        if(creditBankAccountDTO.getUserId() == null || creditBankAccountDTO.getAccountNumber() == null || creditBankAccountDTO.getAmount() == null ){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(transactionService.withdrawCreditBankAccount(creditBankAccountDTO), HttpStatus.CREATED);
        }
    }
}
