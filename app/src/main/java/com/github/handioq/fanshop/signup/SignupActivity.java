package com.github.handioq.fanshop.signup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseActivity;
import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.User;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends BaseActivity implements SignupView {

    private SignupPresenter signupPresenter;

    // UI references.
    @BindView(R.id.signup_email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.signup_password)
    EditText mPasswordView;

    @BindView(R.id.login_progress)
    View progressBar;

    @BindView(R.id.login_form)
    View loginForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupPresenter = new SignupPresenterImpl(this, ((FanShopApp) getApplication()).getNetworkService());
    }

    @OnClick(R.id.signup_button)
    void signUp() {
        String login = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        User user = new User(login, password);
        signupPresenter.signupValidate(user);
    }

    @Override
    public void signupSuccess(User user) {
        Log.e("User", user.toString());
    }

    @Override
    public void signupFailure(Throwable e) {
        Log.e("User", e.toString());
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

