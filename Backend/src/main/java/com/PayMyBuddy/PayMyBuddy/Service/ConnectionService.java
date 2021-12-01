package com.PayMyBuddy.PayMyBuddy.Service;

import com.PayMyBuddy.PayMyBuddy.DTO.UserDTO;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import com.PayMyBuddy.PayMyBuddy.Model.Specific.AddConnection;
import com.PayMyBuddy.PayMyBuddy.Model.User;
import com.PayMyBuddy.PayMyBuddy.Repository.ConnectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {

    @Autowired
    UserService userService;

    @Autowired
    ConnectionDAO connectionDAO;

    public List<Integer> findConnectionsIdById(Integer userid){
        List<Integer>connectionIdList = new ArrayList<>();
        for(Connection connection : connectionDAO.findAllByuserid(userid)){
            connectionIdList.add(connection.getFriendid());
        }
        return connectionIdList;
    }

    public List<User> findConnectionsById(Integer userid){
        List<User> userList = new ArrayList<>();
        List<Integer> friendIdList = findConnectionsIdById(userid);
        for(Integer friendId : friendIdList){
            User user = userService.getUserById(friendId);
            userList.add(user);
        }
        return userList;
    }

    public Connection addConnection(AddConnection addConnection){
        User friend = userService.getUserByEmail(addConnection.getFriendemail());
        Connection firstconnection = new Connection(addConnection.getUserid(),friend.getId());
        Connection secondconnection = new Connection(friend.getId(),addConnection.getUserid());
        connectionDAO.save(secondconnection);
        return connectionDAO.save(firstconnection);
        //TODO : optimise with save All ?
        //TODO: CUSTOM EXCEPTION IF EXISTS
    }

    public List<UserDTO> getAllNonConnectionUsers(Integer userId){
        /*List<UserDTO> nonConnectionUserList = new ArrayList<>();
        for(User u : getAllUsers()){
            if(u.getId() != userId && !connectionService.findConnectionsIdById(userId).contains(u.getId())){
                nonConnectionUserList.add(new UserDTO(u.getId(),u.getFirstName(), u.getLastName(),u.getEmail(), u.getBalance()));
            }
        }
        return nonConnectionUserList;*/

        return userService.convertListToDTOList(
                userService.getAllUsers().stream()
                        .filter(u -> u.getId() != userId && !findConnectionsIdById(userId).contains(u.getId()))
                        .collect(Collectors.toList()));
    }
}

