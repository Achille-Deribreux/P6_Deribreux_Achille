package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.Configuration.ApplicationUserService;
import com.PayMyBuddy.PayMyBuddy.Controller.BankAccountController;
import com.PayMyBuddy.PayMyBuddy.DTO.BankAccountDTO;
import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.BankAccountDAO;
import com.PayMyBuddy.PayMyBuddy.Repository.ConnectionDAO;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import({TestConfig.class})
public class ConnectionServiceTest {
    @MockBean
    ConnectionDAO connectionDAO;

    @MockBean
    UserService userService;

    @Autowired
    ConnectionService connectionService;

    @BeforeEach
    void setup(){
        connectionService.setConnectionDAO(connectionDAO);
        connectionService.setUserService(userService);
    }

    @Test
    public void findConnectionsIdByIdTest() {
       //Given
        Integer userId = 1;
        List<Integer> expected = new ArrayList<>(Arrays.asList(2,3,4));
        List<Integer> result;
        //When
        Mockito.when(connectionDAO.findAllByuserid(userId)).thenReturn(TestData.getSampleConnectionList());
        result = connectionService.findConnectionsIdById(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    public void findConnectionsByIdTest(){
        //Given
        Integer userId = 1;
        List<User> expected = TestData.getUserListForSampleConnectionList();
        List<User> result;
        //When
        Mockito.when(connectionDAO.findAllByuserid(userId)).thenReturn(TestData.getSampleConnectionList());
        Mockito.when(userService.getUserById(2)).thenReturn(new User(2,"A","A","A",100,"mdp"));
        Mockito.when(userService.getUserById(3)).thenReturn(new User(3,"B","B","B",100,"mdp"));
        Mockito.when(userService.getUserById(4)).thenReturn(new User(4,"C","C","C",100,"mdp"));
        result = connectionService.findConnectionsById(userId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    public void addConnectionTest(){
        //Given
        Connection connection = TestData.getSampleConnection();
        AddConnection addConnection = new AddConnection("a@d.be",1);
        //When
        Mockito.when(userService.getUserByEmail(addConnection.getFriendemail())).thenReturn(TestData.getSampleUserForConnection());
        connectionService.addConnection(addConnection);
        //Then
        verify(connectionDAO,Mockito.times(1)).save(TestData.getSampleConnection());
        verify(connectionDAO,Mockito.times(1)).save(TestData.getSampleInverseConnection());
    }

    @Test
    public void getAllNonConnectionUsersTest(){
        //Given
        Integer userId = 1;
        List<UserDTO> expected = TestData.getNonConnectionUser();
        List<UserDTO> result;
        //When
        Mockito.when(userService.convertListToDTOList(TestData.getNonConnectionUserOutDTO())).thenReturn(TestData.getNonConnectionUser());
        Mockito.when(connectionDAO.findAllByuserid(userId)).thenReturn(TestData.getSampleConnectionList());
        Mockito.when(userService.getAllUsers()).thenReturn(TestData.getUserListForSampleNonConnectionList());
        result = connectionService.getAllNonConnectionUsers(userId);
        //Then
        assertEquals(expected, result);
    }

}
