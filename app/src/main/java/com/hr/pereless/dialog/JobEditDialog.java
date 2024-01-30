package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.warkiz.widget.IndicatorSeekBar;


public class JobEditDialog extends DialogFragment {

    private OnConfirmListener listener;
    TextView tv_delete;
    TextView tv_cancel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dailog_edit, container, false);
    }

    public JobEditDialog setOnConfirmListener(OnConfirmListener listener) {
        this.listener = listener;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView save = view.findViewById(R.id.save);
        EditText txv_custom = view.findViewById(R.id.txv_custom);
        EditText txv_location = view.findViewById(R.id.txv_location);
        EditText edit_description = view.findViewById(R.id.edit_description);
        IndicatorSeekBar seekbar = view.findViewById(R.id.seekbar);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirm(txv_custom.getText().toString(),txv_location.getText().toString(),seekbar.getProgress(),edit_description.getText().toString());
                dismiss();
            }
        });
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
        void onConfirm(String custom_text, String location,int salary,String description);
    }
}

