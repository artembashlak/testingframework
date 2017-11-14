package com.waverley.utils;

import com.waverley.model.User;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.Objects;

/**
 * Example of Assert for object.
 */
public class UserAssert extends AbstractAssert<UserAssert, User> {
    private static final String ERROR_MSG = "\nExpecting <%s> of:\n<%s>to be: <%s>\n but was:<%s>";
    public UserAssert(final User user) {
        super(user, UserAssert.class);
    }

    public UserAssert hasUsername(final String username) {
        isNotNull();

        final String actualUsername = actual.getUsername();

        if (!Objects.areEqual(actualUsername, username)) {
            failWithMessage(ERROR_MSG, "Username", actual, username, actualUsername);
        }
        return this;
    }

    public UserAssert hasPassword(final String password) {
        isNotNull();

        final String actualPassword = actual.getPassword();

        if (!Objects.areEqual(actualPassword, password)) {
            failWithMessage(ERROR_MSG, "Password", actual, password, actualPassword);
        }
        return this;
    }
}
