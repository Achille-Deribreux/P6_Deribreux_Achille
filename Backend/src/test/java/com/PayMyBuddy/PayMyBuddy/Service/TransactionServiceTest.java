package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Repository.TransactionDAO;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import({TestConfig.class})
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @MockBean
    TransactionDAO transactionDAO;

    @MockBean
    UserService userService;

    @BeforeEach
    void setUp() {
    transactionService.setTransactionDAO(transactionDAO);
    transactionService.setUserService(userService);
    }

    @Test
    void getAllTransactionByIdTest() {
        //Given
        Integer userId = 1;
        List<TransactionDTO> expected = TestData.getSampleForTransactionDTO();
        List<TransactionDTO> result;
        //When
        Mockito.when(transactionDAO.findBySenderId(userId)).thenReturn(TestData.getSampleForfindBySenderId());
        Mockito.when(transactionDAO.findByReceiverId(userId)).thenReturn(TestData.getSampleForfindByReceiverId());
        Mockito.when(userService.getUserNameById(1)).thenReturn("X X");
        Mockito.when(userService.getUserNameById(2)).thenReturn("X X");
        result = transactionService.getAllTransactionById(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getTransactionsBySenderIdTest() {
        //Given
        Integer userId = 1;
        Iterable<Transaction> expected =  TestData.getSampleForfindBySenderId();
        Iterable<Transaction> result;
        //When
        Mockito.when(transactionDAO.findBySenderId(userId)).thenReturn(TestData.getSampleForfindBySenderId());
        result = transactionService.getTransactionsBySenderId(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getTransactionsByReceiverIdTest() {
        //Given
        Integer userId = 1;
        Iterable<Transaction> expected =  TestData.getSampleForfindByReceiverId();
        Iterable<Transaction> result;
        //When
        Mockito.when(transactionDAO.findByReceiverId(userId)).thenReturn(TestData.getSampleForfindByReceiverId());
        result = transactionService.getTransactionsByReceiverId(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addTransactionTest() {
        //Given
        Integer senderId = 1;
        Integer receiverId = 2;
        Transaction transaction = new Transaction(senderId,receiverId,100,null,"hl");
        //When
        transactionService.addTransaction(transaction);
        //Then

        verify(userService,Mockito.times(1)).checkUserBalance(transaction.getSenderId(),transaction.getAmount());
        verify(userService,Mockito.times(1)).withdrawMoneyFromBalance(transaction.getSenderId(), transaction.getAmount());
        verify(userService,Mockito.times(1)).addMoneyToBalance(transaction.getReceiverId(),transaction.getAmount());

        verify(transactionDAO,Mockito.times(1)).save(new Transaction(
                transaction.getSenderId(),
                transaction.getReceiverId(),
                transaction.getAmount(),
                LocalDateTime.now(),
                transaction.getDescription()
        ));
    }

    @Test
    void addCreditBankAccountTest() {
        //Given
        Integer userId = 1;
        Integer amount = 100;
        Integer accountNumber = 123456789;
        CreditBankAccountDTO creditBankAccountDTO = new CreditBankAccountDTO(userId,amount,accountNumber);
        //When
        transactionService.addCreditBankAccount(creditBankAccountDTO);
        //Then
        verify(userService,Mockito.times(1)).addMoneyToBalance(creditBankAccountDTO.getUserId(), creditBankAccountDTO.getAmount());
        verify(transactionDAO,Mockito.times(1)).save(new Transaction(
                0,
                creditBankAccountDTO.getUserId(),
                creditBankAccountDTO.getAmount(),
                LocalDateTime.now(),
                "Money added from my account N° " + creditBankAccountDTO.getAccountNumber()
        ));
    }

    @Test
    void withdrawCreditBankAccountTest() {
        //Given
        Integer userId = 1;
        Integer amount = 100;
        Integer accountNumber = 123456789;
        CreditBankAccountDTO creditBankAccountDTO = new CreditBankAccountDTO(userId,amount,accountNumber);
        double amountWithTaxes = amount+amount*0.005;
        //When
        transactionService.withdrawCreditBankAccount(creditBankAccountDTO);
        //Then
        verify(userService,Mockito.times(1)).checkUserBalance(creditBankAccountDTO.getUserId(),creditBankAccountDTO.getAmount());
        verify(userService,Mockito.times(1)).withdrawMoneyFromBalance(creditBankAccountDTO.getUserId(), creditBankAccountDTO.getAmount());
        verify(transactionDAO,Mockito.times(1)).save(new Transaction(
                creditBankAccountDTO.getUserId(),
                0,
                creditBankAccountDTO.getAmount(),
                LocalDateTime.now(),
                "Money send to my account N° " + creditBankAccountDTO.getAccountNumber()
        ));
    }
}
