package com.PayMyBuddy.PayMyBuddy.Controller;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value="/userById")
    public ResponseEntity<UserDTO> getUserById(@RequestParam(value = "id") int id){
        UserDTO user = userService.convertToDto(userService.getUserById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value="/userByEmail")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(value = "email") String email){
        UserDTO user = userService.convertToDto(userService.getUserByEmail(email));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user){
        if(user.getEmail() == null || user.getFirstName() == null || user.getLastName() == null || user.getPassword() == null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else {
            UserDTO userdto = userService.convertToDto(userService.addUser(user));
            return new ResponseEntity<>(userdto, HttpStatus.CREATED);
        }
    }
}
