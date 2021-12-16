package com.PayMyBuddy.PayMyBuddy.Repository;

import com.PayMyBuddy.PayMyBuddy.Model.BankAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BankAccountRepositoryTest {

    @Autowired
    BankAccountDAO bankAccountDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByUserIdTest(){
        testEntityManager.persist(new BankAccount(1, 123, "CBC"));
        testEntityManager.flush();
        Iterable<BankAccount> expected = new ArrayList<>(Arrays.asList(new BankAccount(1, 1, 123, "CBC")));
        Iterable<BankAccount> result = bankAccountDAO.findByUserId(1);
        assertEquals(expected,result);
    }

}
