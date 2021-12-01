package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserDTO checkLogin(User user){
        /*for(User u : userDAO.findAll()){
            if(u.getEmail().equals(user.getEmail())&&u.getPassword().equals(user.getPassword())){
                return new UserDTO(u.getId(),u.getFirstName(), u.getLastName(),u.getEmail(), u.getBalance());
            }
        }
        //TODO : return custom exception instead of null
        return null;*/



        return convertToDto(
                getAllUsers().stream()
                        .filter(u -> u.getEmail().equals(user.getEmail())&&u.getPassword().equals(user.getPassword()))
                        .findAny().orElseThrow(RuntimeException::new)
        );
        //TODO : custom exception
    }

    public User getUserById(Integer userId){
        return userDAO.findById(userId).orElse(null);
        //TODO : return custom exception instead of orElse
    }

    public String getUserNameById(Integer userId){
        User user = userDAO.findById(userId).orElse(null);
        assert user != null;
        return user.getFirstName() + " "+user.getLastName();
        //TODO : return custom exception assert and orElse
    }

    public User getUserByEmail(String email){
        return userDAO.findByEmail(email).orElse(null);
    }

    public User addUser (User userToAdd){
        return userDAO.save(userToAdd);
    }

    public void deleteUserById (Integer userId){
        userDAO.deleteById(userId);
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
        if(user.getBalance() > amount){
        }else {

        }
        //TODO : return custom exception bc not enought balance
    }
}

