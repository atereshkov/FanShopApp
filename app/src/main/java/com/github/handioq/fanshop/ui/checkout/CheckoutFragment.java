package com.github.handioq.fanshop.ui.checkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.PassOrderDTO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.ui.account.orders.OrderDetailsActivity;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.dialogs.CheckoutDialog;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class CheckoutFragment extends BaseFragment implements CheckoutMvp.View, CheckoutDialog.NoticeDialogListener {

    private final static String TAG = "CheckoutFragment";
    private static final String ORDER_KEY = "order";
    private static final String CHECKOUT_DIALOG = "CheckoutDialog";
    private PassOrderDTO passOrder;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    CheckoutMvp.Presenter checkoutPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static CheckoutFragment newInstance(PassOrderDTO passOrder) {
        CheckoutFragment fragment = new CheckoutFragment();

        Bundle args = new Bundle();
        args.putSerializable(ORDER_KEY, passOrder);
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
        passOrder = (PassOrderDTO) this.getArguments().getSerializable(ORDER_KEY);

        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCheckoutComponent().inject(this);

        checkoutPresenter.setView(this);
        checkoutPresenter.createOrder(authPreferences.getUserId(), passOrder.getProducts());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setResponse(Response response) {
        Timber.i("Response: %s - %d", response.getStatusMessage(), response.getStatusCode());

        if (response.getStatusCode() == 201) {
            CheckoutDialog dialog = CheckoutDialog.newInstance(response.getStatusMessage());
            dialog.attachListenter(this);
            dialog.show(getFragmentManager(), CHECKOUT_DIALOG);
        } else {
            Toast.makeText(getContext(), response.getStatusMessage(), Toast.LENGTH_SHORT).show(); // error
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
        getActivity().finish();
        startActivity(OrderDetailsActivity.makeIntent(getContext(), 0));
        // TODO get order id from response
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getContext(), R.string.checkout_remember_to_pay, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
