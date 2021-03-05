package com.example.util;

import com.example.util.exception.NotFoundException;

public class Valid {
    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found " + msg);
        }
    }
}
