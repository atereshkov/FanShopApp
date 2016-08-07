package com.github.handioq.fanshop.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.signup.SignupActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginMvp.LoginView {

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.password)
    EditText passwordView;

    @BindView(R.id.login_form)
    View loginForm;

    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    @Inject
    LoginMvp.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((FanShopApp) getApplicationContext()).getLoginComponent().inject(this);

        loginPresenter.setView(this);
    }

    @OnClick(R.id.sign_in)
    void signIn() {
        String login = mEmailView.getText().toString();
        String password = passwordView.getText().toString();
        loginPresenter.loginValidate(login, password);
    }

    @OnClick(R.id.sign_up)
    void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccess(UserAuthState userAuthState) {
        Log.i("UserAuthState", userAuthState.toString());
    }

    @Override
    public void loginFailure(Throwable e) {
        //Log.i("UserAuthState", e.);
        Log.i("UserAuthState", e.toString());
        /*
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
         */
        e.printStackTrace();
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
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void onCompleted() {
        loginForm.setVisibility(View.VISIBLE);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

