package com.github.handioq.fanshop.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPreferences {

    private static final String AUTH_PREFERENCES = "auth";
    private static final String TOKEN = "token";
    private static final String TOKEN_NULL = "null";

    SharedPreferences sharedPreferences;
    Context context;

    public AuthPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setUserToken(String token) {
        sharedPreferences.edit()
                .putString(TOKEN, token)
                .apply();
    }

    public String getUserToken() {
        String token = TOKEN_NULL;

        if (sharedPreferences.contains(TOKEN)) {
            token = sharedPreferences.getString(TOKEN, TOKEN_NULL);
        }

        return token;
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getString(TOKEN, TOKEN_NULL) != TOKEN_NULL;
    }

    public void logout() {
        sharedPreferences.edit()
                .putString(TOKEN, TOKEN_NULL)
                .apply();
    }
}
