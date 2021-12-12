package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.BankAccountNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Repository.BankAccountDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    BankAccountDAO bankAccountDAO;

    @Autowired
    private ModelMapper modelMapper;

    public void setBankAccountDAO(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }


    public Integer getBankAccountIdByNumber(BankAccount bankAccount){
        BankAccount b = bankAccountDAO.findByAccountNumber(bankAccount.getAccountNumber()).orElseThrow(()-> new BankAccountNotFoundException("for number "+bankAccount.getAccountNumber()));
        return b.getId();
    }

    public List<BankAccount> getBankAccountsByUserId(Integer userId){
        List<BankAccount> accountList = new ArrayList<>();
        for(BankAccount bankAccount : bankAccountDAO.findByUserId(userId)){
            accountList.add(bankAccount);
        }
        return accountList;
    }

    public BankAccount addBankAccount(BankAccount bankAccountToAdd){
        //TODO : ALREADY EXISTS ? + TEST
        return bankAccountDAO.save(bankAccountToAdd);
    }

    public void deleteBankAccount(BankAccount bankAccountToDelete){
        bankAccountToDelete.setId(getBankAccountIdByNumber(bankAccountToDelete));
        bankAccountDAO.delete(bankAccountToDelete);
    }
}

