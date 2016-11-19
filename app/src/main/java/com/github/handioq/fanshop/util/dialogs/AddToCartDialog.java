package com.github.handioq.fanshop.util.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.handioq.R;

import java.util.ArrayList;
import java.util.List;

public class AddToCartDialog extends Dialog { // todo change to dialogfragment the same as AddReviewDialog
    private List<String> mList;
    private Context mContext;
    private Spinner mSpinner;

    public interface DialogListener {
        public void onReady(int n);
        public void onCancelled();
    }

    private DialogListener mReadyListener;

    public AddToCartDialog(Context context, List<String> list, DialogListener readyListener) {
        super(context);
        mReadyListener = readyListener;
        mContext = context;
        mList = new ArrayList<String>();
        mList = list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_to_cart_dialog);
        mSpinner = (Spinner) findViewById (R.id.dialog_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, mList);
        mSpinner.setAdapter(adapter);

        Button buttonOK = (Button) findViewById(R.id.dialogOK);
        Button buttonCancel = (Button) findViewById(R.id.dialogCancel);
        buttonOK.setOnClickListener(v -> {
            int n = mSpinner.getSelectedItemPosition();
            mReadyListener.onReady(n);
            AddToCartDialog.this.dismiss();
        });
        buttonCancel.setOnClickListener(v -> {
            mReadyListener.onCancelled();
            AddToCartDialog.this.dismiss();
        });
    }
}