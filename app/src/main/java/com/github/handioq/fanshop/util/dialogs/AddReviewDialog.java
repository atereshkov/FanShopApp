package com.github.handioq.fanshop.util.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.github.handioq.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReviewDialog extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String message, int stars);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    private NoticeDialogListener listener;
    private static final String TAG = "AddReviewDialog";

    @BindView(R.id.review_message)
    EditText reviewMessageView;

    @BindView(R.id.review_rating_bar)
    RatingBar reviewRatingBar;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog");

        Builder builder = new Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_review, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(R.string.add_review_dialog_title)
                .setPositiveButton(R.string.add_review_submit, (dialog, id) -> {
                    String reviewMessage = reviewMessageView.getText().toString();
                    int stars = reviewRatingBar.getProgress();
                    listener.onDialogPositiveClick(AddReviewDialog.this, reviewMessage, stars);
                })
                .setNegativeButton(R.string.add_review_close_dialog, (dialog, id) -> {
                    listener.onDialogNegativeClick(AddReviewDialog.this);
                });
        return builder.create();
    }

    public void attachListenter(NoticeDialogListener noticeDialogListener) {
        this.listener = noticeDialogListener;
    }
}
