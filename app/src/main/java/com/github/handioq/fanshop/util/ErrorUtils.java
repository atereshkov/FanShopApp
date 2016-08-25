package com.github.handioq.fanshop.util;

import java.net.UnknownHostException;

public class ErrorUtils {

    private static final String ERROR_NO_INTERNET = "No internet connection";

    public static String getMessage(Throwable t) {
        String error = "";

        if (t instanceof UnknownHostException) {
            error = ERROR_NO_INTERNET;
        } else {
            error = t.getMessage();
        }

        return error;
    }

}
