package com.github.handioq.fanshop.util.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.handioq.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutDialog extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    private NoticeDialogListener listener;
    private static final String TAG = "CheckoutDialog";
    private static final String KEY_MESSAGE = "message";

    @BindView(R.id.text_status)
    TextView textStatus;

    @BindView(R.id.text_info)
    TextView textInfo;

    public static CheckoutDialog newInstance(String statusMessage) {
        CheckoutDialog f = new CheckoutDialog();

        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, statusMessage);
        f.setArguments(args);

        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog");
        String statusMessage = getArguments().getString(KEY_MESSAGE);

        Builder builder = new Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_checkout, null);
        ButterKnife.bind(this, view);

        textStatus.setText(statusMessage);
        textInfo.setText(R.string.checkout_pay_info);

        /*TextView customTitle = new TextView(getContext());
        customTitle.setText(R.string.checkout_dialog_title);
        customTitle.setTextSize(18);
        customTitle.setPadding(10, 10, 10, 10);
        customTitle.setTextColor(ContextCompat.getColor(getContext().getApplicationContext(), R.color.colorAccent));
        customTitle.setGravity(Gravity.CENTER_HORIZONTAL);*/

        builder.setView(view)
                //.setCustomTitle(customTitle)
                .setTitle(R.string.checkout_dialog_title)
                .setPositiveButton(R.string.checkout_pay_for_order, (dialog, id) -> {
                    listener.onDialogPositiveClick(CheckoutDialog.this);
                })
                .setNegativeButton(R.string.checkout_close_dialog, (dialog, id) -> {
                    listener.onDialogNegativeClick(CheckoutDialog.this);
                });
        return builder.create();
    }

    public void attachListenter(NoticeDialogListener noticeDialogListener) {
        this.listener = noticeDialogListener;
    }
}
