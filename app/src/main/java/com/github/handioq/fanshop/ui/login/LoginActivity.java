package com.github.handioq.fanshop.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.net.model.AuthResponse;
import com.github.handioq.fanshop.ui.signup.SignupActivity;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.JWTUtils;
import com.github.handioq.fanshop.util.Validation;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginMvp.View {

    public static final String TAG = "LoginActivity";

    @BindView(R.id.email)
    AutoCompleteTextView emailView;

    @BindView(R.id.password)
    EditText passwordView;

    @BindView(R.id.login_form)
    View loginForm;

    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    @BindView(R.id.forgot_password)
    TextView forgotPasswordView;

    @Inject
    LoginMvp.Presenter loginPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((FanShopApp) getApplicationContext()).getLoginComponent().inject(this);

        loginPresenter.setView(this);
    }

    @OnClick(R.id.sign_in)
    void signIn() {
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if (!Validation.isPasswordValid(password)) { // TODO do it in presenter
            passwordView.setError(getResources().getString(R.string.error_invalid_password));
            return;
        } else if (!Validation.isEmailValid(email)) {
            emailView.setError(getResources().getString(R.string.error_invalid_email));
            return;
        }

        loginPresenter.loginValidate(email, password);
    }

    @OnClick(R.id.sign_up)
    void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.forgot_password)
    void onRestoreClick() {
        // TODO make restore activity
        Toast.makeText(this, R.string.password_recovery_not_impl, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(AuthResponse authResponse) {
        Log.i(TAG, authResponse.toString());

        try {
            authPreferences.setUserToken(authResponse.getResponseData().getToken());
            authPreferences.setUserId(JWTUtils.getUserIdByToken(authResponse.getResponseData().getToken())); // get userId from JWT
            //authPreferences.setUserId(authResponse.getResponseData().getUserId()); // without JWT
            Toast.makeText(this, getString(R.string.success_auth), Toast.LENGTH_SHORT).show();
            Timber.i("Auth success, userID: %d", authPreferences.getUserId());
            finish();
        } catch (Exception e) {
            Toast.makeText(this, authResponse.getStatus(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void loginFailure(Throwable e) {
        Log.e(TAG, e.toString());
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCompleted() {
        loginForm.setVisibility(View.VISIBLE);
    }

}

