package com.github.handioq.fanshop.productinfo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;

public class DescriptionInfoFragment extends Fragment {

    private int selectedItem;

    private final static String TAG = "DescriptionFragment";

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            selectedItem = bundle.getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_info_description, container, false);

        readBundle(getArguments());

        return view;
    }
}