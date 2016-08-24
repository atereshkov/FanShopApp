package com.github.handioq.fanshop.ui.account.orders;

import android.os.Bundle;

import com.github.handioq.fanshop.base.BaseFragment;

public class OrderDetailsFragment extends BaseFragment {

    private final static String TAG = "OrderDetailsFragment";
    private final static String ARGUMENT_ORDER_ID = "id";

    public static OrderDetailsFragment newInstance(int id) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ORDER_ID, id);
        fragment.setArguments(args);

        return fragment;
    }



}
