package com.github.handioq.fanshop.productinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.database.IProductRepository;
import com.github.handioq.fanshop.database.ProductRepository;
import com.github.handioq.fanshop.model.Image;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.model.dbo.ProductDBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ProductInfoFragment extends BaseFragment implements ProductInfoView {

    private final static String TAG = "ProductInfoFragment";

    private int selectedItemId;
    private ProductInfoPresenter productInfoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectedItemId = this.getArguments().getInt("id");

        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");

        productInfoPresenter = new ProductInfoPresenterImpl(this, ((FanShopApp) getActivity().getApplication()).getNetworkService());
        productInfoPresenter.getProduct(selectedItemId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    private void initSlider(List<Image> images) {



    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setProduct(Product product) {
        Log.e(TAG, "PRODUCT  ---> " + product.getId() + product.getName());

        initSlider(product.getImages());
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
