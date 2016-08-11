package com.github.handioq.fanshop.util;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.github.handioq.R;

public class ScreenDimensionsHelper {

    private Context context;
    private Integer screenWidth;
    private Integer screenHeight;
    private float densityDpi;

    private final static String TAG = "ScreenDimensionsHelper";

    public ScreenDimensionsHelper(Context context) {
        this.context = context;

        init();
    }

    public void init() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);

        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        densityDpi = metrics.density;
        // densityDpi = (int) (metrics.density * 160f);
    }

    public Integer getCardsCount() {
        Integer cardsCount = 1;

        int cardWidth = (int) (context.getResources().getDimension(R.dimen.card_width) / context.getResources().getDisplayMetrics().density);
        cardsCount = (int) ((screenWidth - 32) / (cardWidth * densityDpi));

        Log.i(TAG, cardsCount + " -> " + densityDpi + " " + screenWidth);

        return cardsCount;
    }

    public Integer getCategoriesCount() {
        Integer cardsCount = 1;

        int cardWidth = (int) (context.getResources().getDimension(R.dimen.card_width_category) / context.getResources().getDisplayMetrics().density);
        cardsCount = (int) ((screenWidth - 32) / (cardWidth * densityDpi));

        Log.i(TAG, cardsCount + " -> " + densityDpi + " " + screenWidth);

        return cardsCount;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }
}
