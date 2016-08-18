package com.github.handioq.fanshop.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPreferences {

    private static final String AUTH_PREFERENCES = "auth";
    private static final String TOKEN = "token";
    private static final String TOKEN_NULL = "null";
    private static final String USER_ID = "userId";
    private static final int USER_ID_NULL = -1;

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

    public void setUserId(int id) {
        sharedPreferences.edit()
                .putInt(USER_ID, id)
                .apply();
    }

    public int getUserId() {
        int userId = USER_ID_NULL;

        if (sharedPreferences.contains(USER_ID)) {
            userId = sharedPreferences.getInt(USER_ID, USER_ID_NULL);
        }

        return userId;
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
