package com.github.handioq.fanshop.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dvo.UserDVO;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.ErrorUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class UserFragment extends BaseFragment implements UserMvp.View {

    public static final String TAG = "UserFragment";

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.name_text)
    TextView nameTextView;

    @BindView(R.id.email_text)
    TextView emailTextView;

    @BindView(R.id.phone_text)
    TextView phoneTextView;

    @BindView(R.id.street_text)
    TextView streetTextView;

    @BindView(R.id.city_text)
    TextView cityTextView;

    @BindView(R.id.country_text)
    TextView countryTextView;

    @BindView(R.id.zipcode_text)
    TextView zipcodeTextView;

    @BindView(R.id.user_info_layout)
    LinearLayout userInfoLayout;

    @Inject
    UserMvp.Presenter userPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getAccountComponent().inject(this);

        userPresenter.setView(this);
        userPresenter.getUser(authPreferences.getUserId());
    }

    private void bindUserInfo(UserDVO user) {
        nameTextView.setText(getString(R.string.account_name, user.getName()));
        emailTextView.setText(getString(R.string.account_email, user.getEmail()));
        phoneTextView.setText(getString(R.string.account_phone, user.getPhone()));
        streetTextView.setText(getString(R.string.account_street, user.getAddress().getStreet()));
        cityTextView.setText(getString(R.string.account_city, user.getAddress().getCity()));
        countryTextView.setText(getString(R.string.account_country, user.getAddress().getCountry()));
        zipcodeTextView.setText(getString(R.string.account_zipcode, user.getAddress().getZipcode()));
    }

    @Override
    public void setUser(UserDVO user) {
        bindUserInfo(user);
        Log.i(TAG, user.toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        userInfoLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        userInfoLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
