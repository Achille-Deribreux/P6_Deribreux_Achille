package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.BankAccountDTO;
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

    public Iterable<BankAccount> getAllBankAccounts(){
        return bankAccountDAO.findAll();
    }

    public BankAccount getBankAccountById(Integer id){
        return bankAccountDAO.findById(id).orElseThrow(()-> new BankAccountNotFoundException("for id "+id));
    }

    public Integer getBankAccountIdByNumber(BankAccount bankAccount){
        BankAccount b = bankAccountDAO.findByAccountNumber(bankAccount.getAccountNumber()).orElseThrow(()-> new BankAccountNotFoundException("for number "+bankAccount.getAccountNumber()));
        return b.getId();
    }

    public List<BankAccountDTO> getBankAccountsByUserId(Integer userId){
        List<BankAccountDTO> accountList = new ArrayList<>();
        for(BankAccount bankAccount : bankAccountDAO.findByUserId(userId)){
            accountList.add(convertToDto(bankAccount));
        }
        return accountList;
    }

    public BankAccount addBankAccount(BankAccount bankAccountToAdd){
        return bankAccountDAO.save(bankAccountToAdd);
    }

    public void deleteBankAccount(BankAccount bankAccountToDelete){
        bankAccountToDelete.setId(getBankAccountIdByNumber(bankAccountToDelete));
        bankAccountDAO.delete(bankAccountToDelete);
    }

    public void deleteBankAccountById(Integer bankAccountId){
        bankAccountDAO.deleteById(bankAccountId);
    }

    public BankAccountDTO convertToDto(BankAccount bankAccount){
        return modelMapper.map(bankAccount,BankAccountDTO.class);
    }
}
