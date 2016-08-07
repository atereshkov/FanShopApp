package com.github.handioq.fanshop.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.model.dto.UserDTO;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends BaseActivity implements SignupMvp.SignupView {

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
        UserDTO userDTO = new UserDTO(login, password);
        signupPresenter.signupValidate(userDTO);
    }

    @Override
    public void signupSuccess(UserDTO userDTO) {
        Log.i("UserDTO", userDTO.toString());
    }

    @Override
    public void signupFailure(Throwable e) {
        Log.i("UserDTO", e.toString());
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

