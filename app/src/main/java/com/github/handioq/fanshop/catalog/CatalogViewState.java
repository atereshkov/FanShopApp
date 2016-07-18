package com.github.handioq.fanshop.catalog;


import android.os.Bundle;

public class CatalogViewState {

    private final String KEY_STATE = getClass().getName().toString() + "currentState";

    private final int STATE_SHOWING_SEARCH_LIST = 0;
    private final int STATE_SHOWING_LOADING = 1;

    private int currentState = 0;

    public void saveInstanceState(Bundle out) {
        out.putInt(KEY_STATE, currentState);
    }

    public boolean restoreInstanceState(Bundle in) {
        currentState = in.getInt(KEY_STATE);
        return false;
    }

    public void apply(CatalogView catalogView, boolean retained) {
        if (currentState == STATE_SHOWING_SEARCH_LIST) {
            catalogView.hideProgress();
        } else {
            catalogView.showProgress();
        }
    }

    public void setStateShowSearchList() {
        currentState = STATE_SHOWING_SEARCH_LIST;
    }

    public void setStateShowLoading() {
        currentState = STATE_SHOWING_LOADING;
    }

}
