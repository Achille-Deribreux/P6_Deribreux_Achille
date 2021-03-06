package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.NotEnoughBalanceException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserAlreadyExistsException;
import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void addMoneyToBalance(Integer userId, double moneyToAdd){
        User user = getUserById(userId);
        user.setBalance(user.getBalance() + moneyToAdd);
    }

    public void withdrawMoneyFromBalance(Integer userId, double moneyToWithdraw){
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
        try {
            getUserByEmail(userToAdd.getEmail());
            throw new UserAlreadyExistsException();
        }catch (UserNotFoundException e){
            userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
            return userDAO.save(userToAdd);
        }
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

    public void checkUserBalance(Integer userId, double amount){
        User user = getUserById(userId);
        double amountWithTaxes = amount+(amount*0.005);
        if(user.getBalance() < amountWithTaxes){
            throw new NotEnoughBalanceException();
        }
    }
}

