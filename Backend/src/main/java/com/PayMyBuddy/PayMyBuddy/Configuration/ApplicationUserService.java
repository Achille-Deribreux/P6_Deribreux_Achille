package com.PayMyBuddy.PayMyBuddy.Configuration;

import com.PayMyBuddy.PayMyBuddy.Exceptions.CustomExceptions.UserNotFoundException;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) {
        User user = userDAO.findByEmail(email).orElseThrow(()->new UserNotFoundException(email));
        return UserDetailsImpl.build(user);
    }

}
