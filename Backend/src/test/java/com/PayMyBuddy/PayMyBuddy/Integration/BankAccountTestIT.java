package com.PayMyBuddy.PayMyBuddy.Integration;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.BankAccountController;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Repository.BankAccountDAO;
import com.PayMyBuddy.PayMyBuddy.Service.BankAccountService;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
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
public class BankAccountTestIT {

    @Autowired
    BankAccountDAO bankAccountDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    AuthenticationManager authenticationManager;

    BankAccountController bankAccountController = new BankAccountController();

    @BeforeEach
    void setup(){
        SecurityContext securitycontext = new SecurityContextImpl();
        securitycontext.setAuthentication(new TestingAuthenticationToken(TestData.getPrincipal(), null, Collections.emptyList()));
        SecurityContextHolder.setContext(securitycontext);

        BankAccountService bankAccountService = new BankAccountService();
        bankAccountService.setBankAccountDAO(bankAccountDAO);
        bankAccountController.setBankAccountService(bankAccountService);
    }

    @Test
    void getAllBankAccountsByUserIdTestIT() throws Exception {
        //Given
        Integer userId = 1;
        List<BankAccount> expected = TestData.getSampleBankAccountListWithDbId();
        List<BankAccount> result;

        testEntityManager.persist( new BankAccount(1, 123, "CBC"));
        testEntityManager.persist( new BankAccount(1, 12345, "CBC"));
        testEntityManager.persist( new BankAccount(1, 12346, "CBC"));

        //When
        result = bankAccountController.getAllBankAccountsByUserId(userId).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addBankAccountTestIT() {
        //Given
        Integer userId = 1;
        BankAccount bankAccountToAdd = new BankAccount(userId, 123, "CBC");
        List<BankAccount> expected = new ArrayList<>(Arrays.asList(new BankAccount(1,userId, 123, "CBC")));
        List<BankAccount> result;
        //When
        bankAccountController.addBankAccount(bankAccountToAdd);
        result = (List<BankAccount>) bankAccountDAO.findByUserId(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void deleteBankAccountTestIT() {
        //Given
        BankAccount bankAccount = new BankAccount(1, 123, "CBC");
        testEntityManager.persist(bankAccount);
        Iterable<BankAccount> expected = Collections.emptyList();
        Iterable<BankAccount> result;
        //When
        bankAccountController.deleteBankAccount(bankAccount);
        result = bankAccountDAO.findAll();
        //Then
        assertEquals(expected, result);
    }
}
