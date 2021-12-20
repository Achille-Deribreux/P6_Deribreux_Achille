package com.PayMyBuddy.PayMyBuddy.Integration;


import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.ConnectionController;
import com.PayMyBuddy.PayMyBuddy.Controller.UserController;
import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import com.PayMyBuddy.PayMyBuddy.Service.BankAccountService;
import com.PayMyBuddy.PayMyBuddy.Service.ConnectionService;
import com.PayMyBuddy.PayMyBuddy.Service.UserService;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserTestIT {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    AuthenticationManager authenticationManager;

    UserController userController = new UserController();

    @BeforeEach
    void setup(){
        SecurityContext securitycontext = new SecurityContextImpl();
        securitycontext.setAuthentication(new TestingAuthenticationToken(TestData.getPrincipal(), null, Collections.emptyList()));
        SecurityContextHolder.setContext(securitycontext);

        UserService userService = new UserService();
        userService.setPasswordEncoder(passwordEncoder);
        userService.setModelMapper(modelMapper);
        userService.setUserDAO(userDAO);
        userController.setUserService(userService);
    }

    @Test
    void getUserByIdTestIT() {
        //Given
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        Integer id = 1;
        UserDTO expected = new UserDTO(1,"A","B","a@a.be",100);
        UserDTO result;
        //When
        result = userController.getUserById(id).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getUserByEmailTestIT() {
        //Given
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        String email = "a@a.be";
        UserDTO expected = new UserDTO(1,"A","B","a@a.be",100);
        UserDTO result;
        //When
        result = userController.getUserByEmail(email).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addUserTestIT() {
        //Given
        User userToAdd = new User("A","B","a@a.be",100,"mdp");
        Iterable<User> expected = new ArrayList<>(Arrays.asList(new User(1,"A","B","a@a.be",100,"mdp")));
        Iterable<User> result;
        //When
        userController.addUser(userToAdd);
        result = userDAO.findAll();
        //Then
        assertEquals(expected, result);
    }
}
