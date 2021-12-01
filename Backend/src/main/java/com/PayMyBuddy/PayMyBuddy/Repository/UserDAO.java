package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}