package com.PayMyBuddy.PayMyBuddy.Controllers;



import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.UserController;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.User;
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

@WebMvcTest(UserController.class)
@Import({TestConfig.class})
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

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
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUserByIdTest() throws Exception{
        //Given
        int id = 1;
        //When & Then
        mockMvc.perform(get("/userById").param("id", String.valueOf(id))).andExpect(status().isOk());
    }

    @Test
    public void userByEmailTest() throws Exception{
        //Given
        String email = "a@d.be";
        //When & Then
        mockMvc.perform(get("/userByEmail").param("email", email)).andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception{
        //Given
        User userToAdd = TestData.getSampleUser();
        //When & Then
        mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(userToAdd))).andExpect(status().isCreated());
    }

    @Test
    public void addUserNoContentTest() throws Exception{
        //Given
        User userToAdd = new User();
        //When & Then
        mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(userToAdd))).andExpect(status().isNoContent());
    }
}
