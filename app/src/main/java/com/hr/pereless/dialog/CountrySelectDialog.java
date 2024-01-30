package com.hr.pereless.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.hr.pereless.commons.Constants;


public class CountrySelectDialog extends DialogFragment implements View.OnClickListener {

    private OnConfirmListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.country_select_dialog, container, false);
    }

    public CountrySelectDialog setOnConfirmListener(OnConfirmListener listener) {
        this.listener = listener;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LinearLayout lyt_french = view.findViewById(R.id.lyt_french);
        LinearLayout lyt_english = view.findViewById(R.id.lyt_english);
        LinearLayout lyt_spanish = view.findViewById(R.id.lyt_spanish);
        LinearLayout lyt_china = view.findViewById(R.id.lyt_china);
        lyt_french.setOnClickListener(this);
        lyt_english.setOnClickListener(this);
        lyt_spanish.setOnClickListener(this);
        lyt_china.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyt_french:
                listener.onConfirm(Constants.country[0],0);
                dismiss();
                break;
            case R.id.lyt_english:
                listener.onConfirm(Constants.country[1],1);
                dismiss();
                break;
            case R.id.lyt_spanish:
                listener.onConfirm(Constants.country[2],2);
                dismiss();
                break;
            case R.id.lyt_china:
                listener.onConfirm(Constants.country[3],3);
                dismiss();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public interface OnConfirmListener {
        void onConfirm(String str,int posstion);
    }
}

