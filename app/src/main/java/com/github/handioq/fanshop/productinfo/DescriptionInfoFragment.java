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
    private final static String ARGUMENT_ID = "id";

    public static DescriptionInfoFragment newInstance(int id) {
        DescriptionInfoFragment fragment = new DescriptionInfoFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            selectedItem = bundle.getInt(ARGUMENT_ID);
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