package com.github.handioq.fanshop.account;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.UserDTO;

public interface UserMvp {

    interface Model extends Mvp.Model {

        void gerUser(int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onUserLoaded(UserDTO user);

            void onUserLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setUser(UserDTO user);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<UserMvp.View> {

        void getUser(int userId);

    }
}