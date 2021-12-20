package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Data.TestData;
import com.PayMyBuddy.PayMyBuddy.Model.Connection;
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
public class ConnectionRepositoryTest {

    @Autowired
    ConnectionDAO connectionDAO;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findAllByuseridTest() {
        //Given
        Integer userId = 1;
        testEntityManager.persist(new Connection(1,2));
        testEntityManager.persist(new Connection(1,3));
        testEntityManager.persist(new Connection(1,4));
        testEntityManager.flush();
        Iterable<Connection> expected = TestData.getSampleConnectionListForJPATest();
        Iterable<Connection> result;
        //When
        result = connectionDAO.findAllByuserid(userId);
        //Then
        assertEquals(expected, result);
    }
}
