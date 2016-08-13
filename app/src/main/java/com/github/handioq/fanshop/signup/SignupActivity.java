package com.github.handioq.fanshop.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.model.Response;

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

    @Inject
    SignupMvp.Presenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ((FanShopApp) getApplicationContext()).getSignupComponent().inject(this);

        signupPresenter.setView(this);
    }

    @OnClick(R.id.signup_button)
    void signUp() {
        String login = emailView.getText().toString();
        String password = passwordView.getText().toString();
        RegisterDTO registerDTO = new RegisterDTO(login, password);
        signupPresenter.signupValidate(registerDTO);
    }

    @OnClick(R.id.have_account)
    void onHaveAccountClick() {
        finish();
    }

    @Override
    public void signupSuccess(Response response) {
        Log.i(TAG, response.toString());
        Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show(); // test
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

