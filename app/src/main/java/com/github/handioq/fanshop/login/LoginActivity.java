package com.github.handioq.fanshop.login;

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
import com.github.handioq.fanshop.net.model.LoginDTO;
import com.github.handioq.fanshop.signup.SignupActivity;
import com.github.handioq.fanshop.util.Validation;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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

        if (Validation.isEmailValid(email)) {
            //LoginDTO loginDTO = new LoginDTO(email, password);
            loginPresenter.loginValidate(email, password);
        } else {
            emailView.setError(getResources().getString(R.string.error_invalid_email));
        }
    }

    @OnClick(R.id.sign_up)
    void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.forgot_password)
    void onRestoreClick() {
        // TODO make restore activity
        Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(AuthResponse authResponse) {
        Log.i(TAG, authResponse.toString());
        Toast.makeText(this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show(); // test
        finish();
    }

    @Override
    public void loginFailure(Throwable e) {
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCompleted() {
        loginForm.setVisibility(View.VISIBLE);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        // EmailView.setError(getString(R.string.error_field_required));
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

