package com.waverley.model;


import lombok.Data;

/**
 * This is User class of our model package.
 */

@Data
public class User {

    private final String username;
    private final String password;

    public static User fake() {
        return new User("fakeusername", "fakepassword");
    }
}
