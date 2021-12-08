package com.PayMyBuddy.PayMyBuddy.Controllers;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.TransactionController;
import com.PayMyBuddy.PayMyBuddy.Controller.UserController;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Service.TransactionService;
import com.PayMyBuddy.PayMyBuddy.Service.UserService;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import com.PayMyBuddy.PayMyBuddy.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TransactionController.class)
@Import({TestConfig.class})
public class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionService transactionService;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    AuthenticationManager authenticationManager;

    @BeforeEach
    void setup(){
        SecurityContext securitycontext = new SecurityContextImpl();
        securitycontext.setAuthentication(new TestingAuthenticationToken(TestData.getPrincipal(), null, Collections.emptyList()));
        SecurityContextHolder.setContext(securitycontext);
    }

    @Test
    public void getAllTransactionsTest() throws Exception{
        //Given
        Integer id = 1;
        //When & Then
        mockMvc.perform(get("/getalltransactionsbyid").param("id", String.valueOf(1))).andExpect(status().isOk());
    }

    @Test
    public void addTransactionTest() throws Exception{
        //Given
        Transaction transactionToAdd = TestData.getSampleTransaction();
        //When & Then
        mockMvc.perform(post("/addtransaction").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(transactionToAdd))).andExpect(status().isCreated());
    }

    @Test
    public void addMoneyFromAccountTest() throws Exception{
        //Given
        CreditBankAccountDTO creditBankAccountDTO = TestData.getSampleCreditBankAccountDTO();
        //When & Then
        mockMvc.perform(post("/addMoneyFromAccount").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(creditBankAccountDTO))).andExpect(status().isCreated());
    }

    @Test
    public void sendMoneyToAccountTest() throws Exception{
        //Given
        CreditBankAccountDTO creditBankAccountDTO = TestData.getSampleCreditBankAccountDTO();
        //When & Then
        mockMvc.perform(post("/sendMoneyToAccount").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(creditBankAccountDTO))).andExpect(status().isCreated());
    }

}
