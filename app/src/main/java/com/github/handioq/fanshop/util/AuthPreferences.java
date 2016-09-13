package com.github.handioq.fanshop.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import timber.log.Timber;

public class AuthPreferences {

    private static final String TAG = "AuthPreferences";
    public static String token;

    private static final String AUTH_PREFERENCES = "auth";
    private static final String TOKEN = "token";
    private static final String TOKEN_NULL = "null";
    private static final String USER_ID = "userId";
    private static final int USER_ID_NULL = -1;

    private static SharedPreferences sharedPreferences;
    private Context context;

    public AuthPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setUserToken(String token) {
        sharedPreferences.edit()
                .putString(TOKEN, token)
                .apply();
        AuthPreferences.token = token;
    }

    public static String getUserToken() {
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
        //Timber.i("token: %s", sharedPreferences.getString(TOKEN, TOKEN_NULL));

        boolean isLogged = true;

        if (sharedPreferences.getString(TOKEN, TOKEN_NULL).equals(TOKEN_NULL)) {
            isLogged = false;
        }

        Timber.i("isUserLoggedIn %b", isLogged);
        return isLogged;
        //return sharedPreferences.getString(TOKEN, TOKEN_NULL) != TOKEN_NULL;
    }

    public void logout() {
        Log.i(TAG, "logout");
        sharedPreferences.edit()
                .putString(TOKEN, TOKEN_NULL)
                .putInt(USER_ID, USER_ID_NULL)
                .apply();
    }
}
