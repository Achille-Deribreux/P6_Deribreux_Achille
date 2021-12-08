package com.PayMyBuddy.PayMyBuddy.Data;

import com.PayMyBuddy.PayMyBuddy.Configuration.UserDetailsImpl;
import com.PayMyBuddy.PayMyBuddy.Model.User;

public class TestData {

    public static UserDetailsImpl getPrincipal() {
        return new UserDetailsImpl(1, "test@test.com", "abcd");
    }

    public static User getSampleUser(){
        return new User(123,"Achille","Deribreux","a@d.be",100,"xxx");
    }
}
