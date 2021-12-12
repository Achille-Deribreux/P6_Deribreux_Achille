package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.IncorrectLoginException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.NotEnoughBalanceException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addMoneyToBalance(Integer userId, Integer moneyToAdd){
        User user = getUserById(userId);
        user.setBalance(user.getBalance() + moneyToAdd);
    }

    public void withdrawMoneyFromBalance(Integer userId, Integer moneyToWithdraw){
        User user = getUserById(userId);
        user.setBalance(user.getBalance() - moneyToWithdraw);
    }

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        userDAO.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public User getUserById(Integer userId){
        return userDAO.findById(userId).orElseThrow(()->new UserNotFoundException("For id " + userId));
    }

    public String getUserNameById(Integer userId){
        User user = userDAO.findById(userId).orElseThrow(()->new UserNotFoundException("For id " + userId));
        return user.getFirstName() + " "+user.getLastName();
    }

    public User getUserByEmail(String email){
        return userDAO.findByEmail(email).orElseThrow(()->new UserNotFoundException("For email " + email));
    }

    public User addUser (User userToAdd){
        userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
        return userDAO.save(userToAdd);
    }

    public UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convertListToDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User u : userList){
            userDTOList.add(convertToDto(u));
        }
        return userDTOList;
    }

    public void checkUserBalance(Integer userId, Integer amount){
        User user = getUserById(userId);
        if(user.getBalance() < amount){
            throw new NotEnoughBalanceException();
        }
    }
}

