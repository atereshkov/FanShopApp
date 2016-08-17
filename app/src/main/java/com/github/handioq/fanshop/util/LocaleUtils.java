package com.github.handioq.fanshop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class LocaleUtils {

    public static final String DEFAULT_LOCALE = "Belarus";

    public static ArrayList<String> getCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();

        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }

        Collections.sort(countries);
        return countries;
    }

}
