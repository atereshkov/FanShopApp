package com.github.handioq.fanshop.util;

import java.net.UnknownHostException;
import retrofit2.adapter.rxjava.HttpException;

public class ErrorUtils {

    private static final String ERROR_NO_INTERNET = "No internet connection";
    private static final String ERROR_SERVER_UNAVAILABLE = "Server unavailable";

    public static String getMessage(Throwable t) {
        String error = "";

        if (t instanceof UnknownHostException) {
            error = ERROR_NO_INTERNET;
        } else if (t instanceof HttpException) {
            error = ERROR_SERVER_UNAVAILABLE;
        } else {
            error = t.getMessage();
        }

        return error;
    }

}
