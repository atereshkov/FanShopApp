package com.github.handioq.fanshop.ui.checkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.util.AuthPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class CheckoutFragment extends BaseFragment implements CheckoutMvp.View {

    private final static String TAG = "CheckoutFragment";

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    CheckoutMvp.Presenter checkoutPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static CheckoutFragment newInstance(List<ProductDTO> products) {
        CheckoutFragment fragment = new CheckoutFragment();

        Bundle args = new Bundle();
        // put list
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCheckoutComponent().inject(this);

        checkoutPresenter.setView(this);
        checkoutPresenter.createOrder(authPreferences.getUserId(), new OrderDTO(new ArrayList<ProductDTO>())); // test
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setResponse(Response response) {
        Timber.i("Response: %s", response.getStatusMessage());
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
