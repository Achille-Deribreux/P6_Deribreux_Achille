package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionRepositoryTest {

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findBySenderIdTest() {
        //Given
        Integer senderId = 1;
        testEntityManager.persist(new Transaction(1,4,100, LocalDateTime.now(),"hello"));
        testEntityManager.flush();
        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(new Transaction(1,1,4,100, LocalDateTime.now(),"hello")));
        Iterable<Transaction> result;
        //When
        result = transactionDAO.findBySenderId(senderId);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void findByReceiverIdTest() {
        //Given
        Integer receiverId = 4;
        testEntityManager.persist(new Transaction(1,4,100, LocalDateTime.now(),"hello"));
        testEntityManager.flush();
        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(new Transaction(1,1,4,100, LocalDateTime.now(),"hello")));
        Iterable<Transaction> result;
        //When
        result = transactionDAO.findByReceiverId(receiverId);
        //Then
        assertEquals(expected, result);
    }
}
