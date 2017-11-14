package com.waverley.utils;

import com.waverley.model.User;

/**
 * Custom assert class will perform assertions with object of User and return assert of it.
 */

public final class CustomAssertions {

    private CustomAssertions() {
        throw new UnsupportedOperationException("Illegal access to private constructor.");
    }

    public static UserAssert assertThat(final User user) {
        return new UserAssert(user);
    }
}
