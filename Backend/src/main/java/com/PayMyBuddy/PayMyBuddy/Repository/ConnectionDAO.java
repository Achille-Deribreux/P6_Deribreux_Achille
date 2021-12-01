package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionDAO extends CrudRepository<Connection, Integer> {
    Iterable<Connection> findAllByuserid(Integer userid);
}
