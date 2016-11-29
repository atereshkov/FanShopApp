package com.github.handioq.fanshop.ui.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.model.dvo.ReviewListDVO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.ui.productinfo.adapter.ReviewAdapter;
import com.github.handioq.fanshop.ui.productinfo.reviews.iteraction.AddReviewMvp;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.dialogs.AddReviewDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ReviewsFragment extends BaseFragment implements ReviewsMvp.View,
        AddReviewDialog.NoticeDialogListener, AddReviewMvp.View {

    @BindView(R.id.reviews_recycler_view)
    RecyclerView recyclerView;

    private ReviewAdapter reviewAdapter;
    private int productId;

    private final static String TAG = "ReviewsFragment";
    public static final String ADD_REVIEW_DIALOG = "AddReviewDialog";
    private final static String ARGUMENT_ID = "id";

    @Inject
    ReviewsMvp.Presenter reviewsPresenter;

    @Inject
    AddReviewMvp.Presenter addReviewPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static ReviewsFragment newInstance(int id) {
        ReviewsFragment fragment = new ReviewsFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        ((FanShopApp) getContext().getApplicationContext()).getProductInfoComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBundle(getArguments());

        addReviewPresenter.setView(this);
        reviewsPresenter.setView(this);
        reviewsPresenter.getReviews(productId);
    }

    @OnClick(R.id.add_review)
    public void onAddReviewClick() {
        Log.i(TAG, "onAddReviewClick");

        if (authPreferences.isUserLoggedIn()) {
            AddReviewDialog dialog = new AddReviewDialog();
            dialog.attachListenter(this);
            dialog.show(getFragmentManager(), ADD_REVIEW_DIALOG);
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.add_review_not_logged), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String message, int stars) {
        Log.i(TAG, "onDialogPositiveClick");

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setMessage(message);
        reviewDTO.setStars(stars);

        addReviewPresenter.addReview(authPreferences.getUserId(), productId, reviewDTO);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    @Override
    public void onReviewAddSuccess(Response response) {
        Log.i(TAG, "onReviewAddSuccess");

        if (response.getStatusCode() == 200) {
            Toast.makeText(getContext(), response.getStatusMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error: " + response.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReviewAddError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            productId = bundle.getInt(ARGUMENT_ID);
        }
    }

    private void initRecyclerView(ReviewListDVO reviews) {
        Log.i(TAG, "initRecyclerView()");

        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter(reviews.getReviews());
        recyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setReviews(ReviewListDVO reviews) {
        if (getActivity() != null) { // check for attaching to activity
            initRecyclerView(reviews);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
