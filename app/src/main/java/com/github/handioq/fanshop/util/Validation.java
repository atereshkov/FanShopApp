package com.github.handioq.fanshop.util;

import java.util.regex.Pattern;

public class Validation {

    private Validation() { }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isEmailValid(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        return pattern.matcher(email).matches();
    }



}
