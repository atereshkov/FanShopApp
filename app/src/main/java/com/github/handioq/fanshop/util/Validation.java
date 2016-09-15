package com.github.handioq.fanshop.util;

import java.util.ArrayList;
import java.util.List;
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

    public static boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    public static boolean emptyFieldFound(String phone, String name, String city, String street) {
        List<String> fields = new ArrayList<>();
        boolean emptyFound = false;

        fields.add(phone);
        fields.add(name);
        fields.add(city);
        fields.add(street);

        for (String field : fields) {
            if (field.isEmpty()) {
                emptyFound = true;
            }
        }

        return emptyFound;
    }

}
