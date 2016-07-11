package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.User;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observer;
import rx.Subscription;

public class SignupPresenterImpl implements SignupPresenter{

    private NetworkService networkService;
    private Subscription subscription;
    private SignupView signupView;
    private SignupModel signupModel;

    public SignupPresenterImpl(SignupView signupView, NetworkService networkService) {
        this.signupView = signupView;
        this.networkService = networkService;
    }

    @Override
    public void signupValidate(User user) {

        if (signupView != null) {
            signupView.showProgress();
        }

        signupModel = new SignupModelImpl(networkService);

        subscription = signupModel.getSignupState(user)
                .subscribe(new Observer<User>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        signupView.signupFailure(e);
                        signupView.hideProgress();
                    }

                    @Override
                    public void onNext(User user) { // TODO: check for wrong data or
                        signupView.signupSuccess(user);
                        signupView.hideProgress();
                    }
                });

    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
