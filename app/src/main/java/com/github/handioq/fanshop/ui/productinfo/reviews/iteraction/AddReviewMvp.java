package com.github.handioq.fanshop.ui.productinfo.reviews.iteraction;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.net.model.Response;

public interface AddReviewMvp {

    interface Model extends Mvp.Model {

        void addReview(int userId, int productId, ReviewDTO review);

        void setCallback(Callback callback);

        interface Callback {

            void onReviewAdded(Response response);

            void onReviewAddError(Throwable error);

            void onReviewCompleted();
        }
    }

    interface View extends Mvp.View {

        void onReviewAddSuccess(Response response);

        void onReviewAddError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <AddReviewMvp.View> {

        void addReview(int userId, int productId, ReviewDTO review);

    }

}
