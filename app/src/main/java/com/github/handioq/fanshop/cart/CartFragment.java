package com.github.handioq.fanshop.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public class CartFragment extends BaseFragment implements CartView {

    private final static String TAG = "CartFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setProducts(List<ProductDTO> productDTOs) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onItemClicked(View view, int position) {

    }
}
