package com.PayMyBuddy.PayMyBuddy.Controllers;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.ConnectionController;
import com.PayMyBuddy.PayMyBuddy.Controller.TransactionController;
import com.PayMyBuddy.PayMyBuddy.DTO.CreditBankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Service.ConnectionService;
import com.PayMyBuddy.PayMyBuddy.Service.TransactionService;
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

@WebMvcTest(ConnectionController.class)
@Import({TestConfig.class})
public class ConnectionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConnectionService connectionService;

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
    public void addconnectionTest() throws Exception{
        //Given
        AddConnection addConnection = TestData.getSampleAddConnection();
        //When & Then
        mockMvc.perform(post("/addconnection").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(addConnection))).andExpect(status().isCreated());
    }

    @Test
    public void getConnectionsIdByIdTest() throws Exception{
        //Given
        int id = 3;
        //When & Then
        mockMvc.perform(get("/getconnectionsbyid").param("id", String.valueOf(id))).andExpect(status().isOk());
    }

    @Test
    public void getConnectionsByIdTest() throws Exception{
        //Given
        int id = 3;
        //When & Then
        mockMvc.perform(get("/getconnectionusersbyid").param("id", String.valueOf(id))).andExpect(status().isOk());
    }

    @Test
    public void getAllNonConnectionUsersTest() throws Exception{
        //Given
        Integer userid = 1;
        //When & Then
        mockMvc.perform(get("/getAllNonConnectionUsers").param("userId", String.valueOf(userid))).andExpect(status().isOk());
    }




}
