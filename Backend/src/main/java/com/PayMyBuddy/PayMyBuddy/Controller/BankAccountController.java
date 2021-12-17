package com.PayMyBuddy.PayMyBuddy.Controller;

import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    public void setBankAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping(value="/addBankAccount")
    public ResponseEntity<BankAccount>addBankAccount(@RequestBody BankAccount bankAccount){
        if(bankAccount.getAccountNumber() == 0 || bankAccount.getBank() == null || bankAccount.getUserId() == 0 ){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(bankAccountService.addBankAccount(bankAccount), HttpStatus.CREATED);
        }
    }

    @GetMapping(value="/getAllBankAccountsByUserId")
    public ResponseEntity<List<BankAccount>>getAllBankAccountsByUserId(@RequestParam(value="userId") Integer userId){
        return new ResponseEntity<>(bankAccountService.getBankAccountsByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteBankAccount")
    public ResponseEntity<String> deleteBankAccount(@RequestBody BankAccount bankAccount){
        if(bankAccount.getAccountNumber() == 0){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            bankAccountService.deleteBankAccount(bankAccount);
            return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
        }
    }
}
