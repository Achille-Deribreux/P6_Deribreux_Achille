package com.PayMyBuddy.PayMyBuddy.Controller;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ConnectionController {
    @Autowired
    ConnectionService connectionService;

    @PostMapping(value="/addconnection")
    public ResponseEntity<Connection> addConnection(@RequestBody AddConnection addConnection){
        Connection connectionToReturn = connectionService.addConnection(addConnection);
        return new ResponseEntity<>(connectionToReturn, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getconnectionsbyid")
    public ResponseEntity<List<Integer>> getConnectionsIdById(@RequestParam(value="id") int id){
        return new ResponseEntity<>(connectionService.findConnectionsIdById(id),HttpStatus.OK);
    }

    @GetMapping(value="/getconnectionusersbyid")
    public ResponseEntity<List<User>>getConnectionsById(@RequestParam(value="id") int id){
        return new ResponseEntity<>(connectionService.findConnectionsById(id),HttpStatus.OK);
    }

    @GetMapping(value="/getAllNonConnectionUsers")
    public ResponseEntity<List<UserDTO>> getAllNonConnectionUsers(@RequestParam(value="userId") Integer userId){
        return new ResponseEntity<>(connectionService.getAllNonConnectionUsers(userId),HttpStatus.OK);
    }
}
