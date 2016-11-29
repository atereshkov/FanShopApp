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
import com.github.handioq.fanshop.model.dto.AddressDTO;
import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.LocaleUtils;
import com.github.handioq.fanshop.util.Validation;

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
        countriesSpinnerView.setSelection(countryAdapter.getPosition(LocaleUtils.getDefaultLocale()));
        Log.e(TAG, LocaleUtils.getDefaultLocale());
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
        long postcode = Long.valueOf(zipcodeView.getText().toString());

        if (!Validation.isEmailValid(mail)) {
            emailView.setError(getResources().getString(R.string.error_invalid_email));
            return;
        } else if (!Validation.isPasswordValid(password)) {
            passwordView.setError(getResources().getString(R.string.error_invalid_password));
            return;
        }

        if (Validation.emptyFieldFound(contactPhone, name, city, street)) {
            Toast.makeText(this, R.string.fields_fill, Toast.LENGTH_SHORT).show();
            return;
        }

        AddressDTO address = new AddressDTO(street, city, country, postcode);
        RegisterDTO registerDTO = new RegisterDTO(mail, password, name, contactPhone, address);

        signupPresenter.signupValidate(registerDTO);
    }

    @OnClick(R.id.have_account)
    void onHaveAccountClick() {
        finish();
    }

    @Override
    public void signupSuccess(Response response) {
        Log.i(TAG, response.toString());
        if (response.getStatusCode() == 200) {
            Toast.makeText(this, getResources().getString(R.string.success_register), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, response.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void signupFailure(Throwable e) {
        Log.i(TAG, e.toString());
        Toast.makeText(this, ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        if (progressBar != null && loginForm != null) { // check for attaching to activity
            progressBar.setVisibility(View.GONE);
            loginForm.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCompleted() {
        loginForm.setVisibility(View.VISIBLE);
    }

}

