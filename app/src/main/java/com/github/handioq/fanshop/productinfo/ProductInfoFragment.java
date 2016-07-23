package com.github.handioq.fanshop.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.Product;

public class ProductInfoFragment extends BaseFragment implements ProductInfoView {

    private final static String TAG = "ProductInfoFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void showProgress() {
        
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setProduct(Product product) {

    }
}
