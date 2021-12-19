package com.PayMyBuddy.PayMyBuddy.Integration;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.TransactionController;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.TransactionDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.TransactionDAO;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import com.PayMyBuddy.PayMyBuddy.Service.TransactionService;
import com.PayMyBuddy.PayMyBuddy.Service.UserService;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import com.PayMyBuddy.PayMyBuddy.TestUtils;
import com.PayMyBuddy.PayMyBuddy.Utils.Formatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionTestIT {

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    UserDAO userDAO;

    TransactionController transactionController = new TransactionController();

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    AuthenticationManager authenticationManager;

    @BeforeEach
    void setup() {
        SecurityContext securitycontext = new SecurityContextImpl();
        securitycontext.setAuthentication(new TestingAuthenticationToken(TestData.getPrincipal(), null, Collections.emptyList()));
        SecurityContextHolder.setContext(securitycontext);

        TransactionService transactionService = new TransactionService();
        UserService userService = new UserService();
        userService.setUserDAO(userDAO);
        transactionService.setUserService(userService);
        transactionService.setTransactionDAO(transactionDAO);
        transactionController.setTransactionService(transactionService);
    }

    @Test
    void getAllTransactionsTestIT() {
        //Given
        Integer id = 1;
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",100,"mdp"));
        testEntityManager.persist(new Transaction(1,2,10, LocalDateTime.now(),"hello"));
        testEntityManager.persist(new Transaction(2,1,5, LocalDateTime.now(),"hello"));
        List<TransactionDTO> expected = new ArrayList<>(Arrays.asList(new TransactionDTO("C D", "-10.0€", "hello", Formatter.convertDate(LocalDateTime.now())),new TransactionDTO("C D", "+5.0€", "hello", Formatter.convertDate(LocalDateTime.now()))));
        List<TransactionDTO> result;
        //When
        result = transactionController.getAllTransactions(id).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addMoneyFromAccountTestIT() {
        //Given
        CreditBankAccountDTO creditBankAccountDTO = new CreditBankAccountDTO(1,20,123);
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",200,"mdp"));
        testEntityManager.persist(new BankAccount(1,123,"cbc"));
        double expected = 120;
        double result;
        //When
        transactionController.addMoneyFromAccount(creditBankAccountDTO);
        User u = userDAO.findById(1).orElse(null);
        result = u.getBalance();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void sendMoneyToAccountTestIT() {
        //Given
        CreditBankAccountDTO creditBankAccountDTO = new CreditBankAccountDTO(1,1000,123);
        testEntityManager.persist(new User("A","B","a@a.be",10000,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",200,"mdp"));
        testEntityManager.persist(new BankAccount(1,123,"cbc"));
        double expected = 8995;
        double result;
        //When
        transactionController.sendMoneyToAccount(creditBankAccountDTO);
        User u = userDAO.findById(1).orElse(null);
        result = u.getBalance();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addTransactionTestIT() {
        //Given
        Transaction transaction = new Transaction(1,2,100,LocalDateTime.now(),"hello");
        testEntityManager.persist(new User("A","B","a@a.be",10000,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",200,"mdp"));
        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(new Transaction(1,1,0,0.5,LocalDateTime.now().plusMinutes(1),"Taxes for transaction of 100.0€"),new Transaction(2,1,2,100,LocalDateTime.now(),"hello")));
        Iterable<Transaction> result;
        //When
        transactionController.addTransaction(transaction);
        result = transactionDAO.findAll();
        //Then
        assertEquals(expected, result);
    }
}
