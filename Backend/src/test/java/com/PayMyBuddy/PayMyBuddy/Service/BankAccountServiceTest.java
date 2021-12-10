package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.BankAccountController;
import com.PayMyBuddy.PayMyBuddy.DTO.BankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.BankAccountNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Repository.BankAccountDAO;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import({TestConfig.class})
public class BankAccountServiceTest {
    @MockBean
    BankAccountDAO bankAccountDAO;

    @Autowired
    BankAccountService bankAccountService;

    @BeforeEach
    void setup(){
        bankAccountService.setBankAccountDAO(bankAccountDAO);
    }

    @Test
    public void getBankAccountIdByNumberTest(){
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        Integer expected = 0;
        Integer result;
        //When
        Mockito.when(bankAccountDAO.findByAccountNumber(bankAccount.getAccountNumber())).thenReturn(TestData.getSampleOptionnalBankAccount());
        result = bankAccountService.getBankAccountIdByNumber(bankAccount);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getBankAccountIdByNumberExceptionTest() {
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When & Then
        assertThrows(BankAccountNotFoundException.class, () -> bankAccountService.getBankAccountIdByNumber(bankAccount));
    }

    @Test
    public void getBankAccountsByUserIdTest(){
        //Given
        Integer userId = 1;
        List<BankAccountDTO> expected = TestData.getSampleBankAccountDTOList();
        List<BankAccountDTO> result;
        //When
        Mockito.when(bankAccountDAO.findByUserId(userId)).thenReturn(TestData.getSampleBankAccountList());
        result = bankAccountService.getBankAccountsByUserId(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    public void addBankAccountTest(){
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When
        bankAccountService.addBankAccount(bankAccount);
        //Then
        verify(bankAccountDAO,Mockito.times(1)).save(bankAccount);
    }

    @Test
    public void deleteBankAccountTest(){
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When
        Mockito.when(bankAccountDAO.findByAccountNumber(bankAccount.getAccountNumber())).thenReturn(TestData.getSampleOptionnalBankAccount());
        bankAccountService.deleteBankAccount(bankAccount);
        //Then
        verify(bankAccountDAO,Mockito.times(1)).delete(bankAccount);
    }

    @Disabled
    @Test
    public void convertToDtoTest(){
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When

        //Then
    }
}
