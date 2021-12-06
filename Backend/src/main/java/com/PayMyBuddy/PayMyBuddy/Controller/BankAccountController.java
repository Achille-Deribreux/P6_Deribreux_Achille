package com.PayMyBuddy.PayMyBuddy.Controller;

import com.PayMyBuddy.PayMyBuddy.DTO.BankAccountDTO;
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

    @PostMapping(value="/addBankAccount")
    public ResponseEntity<BankAccount>addBankAccount(@RequestBody BankAccount bankAccount){
        return new ResponseEntity<>(bankAccountService.addBankAccount(bankAccount), HttpStatus.CREATED);
        //TODO: Check les autres Post car pq utiliser les modèles adds ??
    }

    @GetMapping(value="/getAllBankAccountsByUserId")
    public ResponseEntity<List<BankAccountDTO>>getAllBankAccountsByUserId(@RequestParam(value="userId") Integer userId){
        return new ResponseEntity<>(bankAccountService.getBankAccountsByUserId(userId), HttpStatus.OK);
        //TODO : add vérifications not null http 204
    }

    @DeleteMapping(value="/deleteBankAccount")
    public ResponseEntity<String> deleteBankAccount(@RequestBody BankAccount bankAccount){
        bankAccountService.deleteBankAccount(bankAccount);
        return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
    }
}