package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.NotEnoughBalanceException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import com.PayMyBuddy.PayMyBuddy.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import({TestConfig.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserDAO userDAO;

    @BeforeEach
    void setup(){
        userService.setUserDAO(userDAO);
    }

    @Test
    public void addMoneyToBalanceTest(){
        //Given
        Integer userId = 1;
        Integer moneyToAdd = 100;
        Integer expected = 200;
        Integer result ;
        //When
        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(TestData.getSampleUser()));
        userService.addMoneyToBalance(userId,moneyToAdd);
        result = userService.getUserById(userId).getBalance();
        //Then
        assertEquals(expected,result);
    }

    @Test
    public void withdrawMoneyFromBalanceTest(){
        //Given
        Integer userId = 1;
        Integer moneyToWithdraw = 100;
        Integer expected = 0;
        Integer result ;
        //When
        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(TestData.getSampleUser()));
        userService.withdrawMoneyFromBalance(userId,moneyToWithdraw);
        result = userService.getUserById(userId).getBalance();
        //Then
        assertEquals(expected,result);
    }

    @Test
    public void getAllUsersTest(){
        //Given
        List<User> expected = TestData.getAllUsersList();
        List<User> result;
        //When
        Mockito.when(userDAO.findAll()).thenReturn(TestData.getAllUsersIterable());
        result = userService.getAllUsers();
        //Then
        assertEquals(expected,result);
    }

    @Test
    void getUserByIdTest() {
        //Given
        Integer userId = 1;
        User expected = TestData.getSampleUser();
        User result;
        //When
        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(TestData.getSampleUser()));
        result = userService.getUserById(userId);
        //Then
        assertEquals(expected,result);
    }

    @Test
    void getUserByIdExceptionTest() {
        //Given
        Integer userId = 1;
        //When & Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void getUserNameByIdTest() {
        //Given
        Integer userId = 1;
        String expected = "Achille Deribreux";
        String result;
        //When
        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(TestData.getSampleUser()));
        result = userService.getUserNameById(userId);
        //Then
        assertEquals(expected,result);
    }

    @Test
    void getUserNameByIdExceptionTest() {
        //Given
        Integer userId = 1;
        //When & Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserNameById(userId));
    }

    @Test
    void getUserByEmailTest() {
        //Given
        String email = "a@d.be";
        User expected = TestData.getSampleUser();
        User result;
        //When
        Mockito.when(userDAO.findByEmail(email)).thenReturn(Optional.of(TestData.getSampleUser()));
        result = userService.getUserByEmail(email);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void getUserByEmailExceptionTest() {
        //Given
        String email = "a@d.be";
        //When & Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(email));
    }

    @Test
    void addUserTest() {
        //Given
        User user = TestData.getSampleUser();
        //When
        userService.addUser(user);
        //Then
        verify(userDAO,Mockito.times(1)).save(user);
    }

    @Test
    void convertToDtoTest() {
        //Given
        UserDTO expected = TestData.getSampleUserDTO();
        UserDTO result;
        //When
        result = userService.convertToDto(TestData.getSampleUser());
        //Then
        assertEquals(expected, result);
    }

    @Test
    void convertListToDTOListTest() {
        //Given
        List<User> startList = TestData.getSampleUserList();
        List<UserDTO> expected = TestData.getSampleUserDTOList();
        List<UserDTO> result;
        //When
        result = userService.convertListToDTOList(startList);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void checkUserBalanceTest() {
        //Given
        Integer userId = 1;
        Integer amount = 10000;
        //When
        Mockito.when(userDAO.findById(userId)).thenReturn(Optional.of(TestData.getSampleUser()));
        //Then
        assertThrows(NotEnoughBalanceException.class, () -> userService.checkUserBalance(userId, amount));
    }
}
