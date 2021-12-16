package com.PayMyBuddy.PayMyBuddy.Configuration;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityTest {

    @Test
    public void BCryptPasswordEncoder() {
        // ARRANGE
        String password = "myPassword";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16); // Create an encoder with strength 16
        // ACT
        String result = encoder.encode(password);
        // ASSERT
        assertTrue(encoder.matches("myPassword", result));
        assertFalse(encoder.matches("Password", result));
    }
}
