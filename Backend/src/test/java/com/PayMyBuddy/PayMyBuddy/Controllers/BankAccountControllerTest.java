package com.PayMyBuddy.PayMyBuddy.Controllers;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.BankAccountController;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Service.BankAccountService;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import com.PayMyBuddy.PayMyBuddy.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankAccountController.class)
@Import({TestConfig.class})
public class BankAccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BankAccountService bankAccountService;

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
    public void addBankAccountTest() throws Exception{
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When & Then
        mockMvc.perform(post("/addBankAccount").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(bankAccount))).andExpect(status().isCreated());
    }

    @Test
    public void getAllBankAccountsByUserIdTest() throws Exception{
        //Given
        int id = 3;
        //When & Then
        mockMvc.perform(get("/getAllBankAccountsByUserId").param("userId", String.valueOf(id))).andExpect(status().isOk());
    }

    @Test
    public void deleteBankAccountTest() throws Exception{
        //Given
        BankAccount bankAccount = TestData.getSampleBankAccount();
        //When & Then
        mockMvc.perform(delete("/deleteBankAccount").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(bankAccount))).andExpect(status().isOk());
    }

}
