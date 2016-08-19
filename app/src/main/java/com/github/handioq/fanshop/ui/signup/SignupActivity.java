package com.github.handioq.fanshop.ui.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.util.LocaleUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements SignupMvp.View {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.signup_email)
    AutoCompleteTextView emailView;

    @BindView(R.id.signup_password)
    EditText passwordView;

    @BindView(R.id.login_progress)
    View progressBar;

    @BindView(R.id.login_form)
    View loginForm;

    @BindView(R.id.spinner_country)
    Spinner countriesSpinnerView;

    @BindView(R.id.signup_phone)
    AutoCompleteTextView phoneView;

    @BindView(R.id.signup_city)
    AutoCompleteTextView cityView;

    @BindView(R.id.signup_street)
    AutoCompleteTextView streetView;

    @BindView(R.id.signup_zipcode)
    AutoCompleteTextView zipcodeView;

    @BindView(R.id.signup_name)
    AutoCompleteTextView nameView;

    @Inject
    SignupMvp.Presenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ((FanShopApp) getApplicationContext()).getSignupComponent().inject(this);

        signupPresenter.setView(this);
        initCountrySpinner();
    }

    private void initCountrySpinner() {
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, LocaleUtils.getCountries());
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countriesSpinnerView.setAdapter(countryAdapter);
        countriesSpinnerView.setSelection(countryAdapter.getPosition(LocaleUtils.DEFAULT_LOCALE));
    }

    @OnClick(R.id.signup_button)
    void signUp() {
        String mail = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String contactPhone = phoneView.getText().toString();
        String name = nameView.getText().toString();
        String country = countriesSpinnerView.getSelectedItem().toString();
        String city = cityView.getText().toString();
        String street = streetView.getText().toString();
        long zipcode = Long.valueOf(zipcodeView.getText().toString());

        // TODO add validation

        RegisterDTO registerDTO = new RegisterDTO(mail, password, name, contactPhone, street, city, country, zipcode);

        signupPresenter.signupValidate(registerDTO);
    }

    @OnClick(R.id.have_account)
    void onHaveAccountClick() {
        finish();
    }

    @Override
    public void signupSuccess(Response response) {
        Log.i(TAG, response.toString());
        Toast.makeText(this, getResources().getString(R.string.success_register), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void signupFailure(Throwable e) {
        Log.i(TAG, e.toString());

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCompleted() {
        loginForm.setVisibility(View.VISIBLE);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        // mEmailView.setError(getString(R.string.error_field_required));
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

