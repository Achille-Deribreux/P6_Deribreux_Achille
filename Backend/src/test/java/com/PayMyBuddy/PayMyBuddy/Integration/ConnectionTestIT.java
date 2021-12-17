package com.PayMyBuddy.PayMyBuddy.Integration;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.BankAccountController;
import com.PayMyBuddy.PayMyBuddy.Controller.ConnectionController;
import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.BankAccountDAO;
import com.PayMyBuddy.PayMyBuddy.Repository.ConnectionDAO;
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
public class ConnectionTestIT {

    @Autowired
    ConnectionDAO connectionDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    AuthenticationManager authenticationManager;

    ConnectionController connectionController = new ConnectionController();

    @BeforeEach
    void setup(){
        SecurityContext securitycontext = new SecurityContextImpl();
        securitycontext.setAuthentication(new TestingAuthenticationToken(TestData.getPrincipal(), null, Collections.emptyList()));
        SecurityContextHolder.setContext(securitycontext);

        ConnectionService connectionService = new ConnectionService();
        UserService userService = new UserService();
        userService.setUserDAO(userDAO);
        userService.setModelMapper(modelMapper);
        connectionService.setUserService(userService);
        connectionService.setConnectionDAO(connectionDAO);
        connectionController.setConnectionService(connectionService);
    }

    @Test
    void addConnectionTestIT() {
        //Given
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",100,"mdp"));
        AddConnection addConnection = new AddConnection("a@d.be", 1);
        Iterable<Connection> expected = new ArrayList<>(Arrays.asList(new Connection(2, 1,2)));
        Iterable<Connection> result;
        //When
        connectionController.addConnection(addConnection);
        result = connectionDAO.findAllByuserid(1);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getConnectionsIdByIdTestIT() {
        //Given
        Integer id = 1;
        testEntityManager.persist(new Connection(1,2));
        List<Integer> expected = new ArrayList<>(Arrays.asList(2));
        List<Integer> result;
        //When
        result = connectionController.getConnectionsIdById(1).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getConnectionsByIdTestIT() {
        //Given
        Integer id = 1;
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",100,"mdp"));
        testEntityManager.persist(new Connection(1,2));
        List<User> expected = new ArrayList<>(Arrays.asList(new User(2,"C","D","a@d.be",100,"mdp")));
        List<User> result;
        //When
        result = connectionController.getConnectionsById(1).getBody();
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getAllNonConnectionUsersTestIT() {
        //Given
        Integer id = 1;
        testEntityManager.persist(new User("A","B","a@a.be",100,"mdp"));
        testEntityManager.persist(new User("C","D","a@d.be",100,"mdp"));
        testEntityManager.persist(new User("X","Y","a@x.be",100,"mdp"));
        testEntityManager.persist(new Connection(1,2));
        List<UserDTO> expected = new ArrayList<>(Arrays.asList(new UserDTO(3,"X","Y","a@x.be",100)));
        List<UserDTO> result;
        //When
        result = connectionController.getAllNonConnectionUsers(1).getBody();
        //Then
        assertEquals(expected, result);
    }
}
