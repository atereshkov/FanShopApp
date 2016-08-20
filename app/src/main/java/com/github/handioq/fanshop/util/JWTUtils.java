package com.github.handioq.fanshop.util;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import timber.log.Timber;

public class JWTUtils {

    private static final String SPLITT = "\\.";
    private static final String TAG = "JWTUtils";
    private static final int INVALID_USER_ID = -1;
    private static final String USER_ID = "userId";

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.NO_WRAP);
        return new String(decodedBytes, "UTF-8");
    }

    public static int getUserIdByToken(String JWTEncoded) throws Exception {
        int userId = INVALID_USER_ID;
        try {
            String[] split = JWTEncoded.split(SPLITT);
            String body = getJson(split[1]);
            JSONObject jsonObject = new JSONObject(body);
            userId = jsonObject.getInt(USER_ID);
            Timber.i("User ID decoded: %d", userId);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }

        return userId;
    }

}
