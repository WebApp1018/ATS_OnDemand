package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;


public class ConfirmDialog extends DialogFragment {

    private OnConfirmListener listener;
    TextView tv_delete;
    TextView tv_cancel;
    TextView tv_title;
    String txt_title,okay = "OK",cancel = "Cancel";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_confirm, container, false);
    }

    public ConfirmDialog setOnConfirmListener(OnConfirmListener listener, String string, String deactivate, String cancel) {
        this.listener = listener;
        txt_title = string;
        this.okay = deactivate;
        this.cancel = cancel;
        return null;
    }
    public ConfirmDialog setOnConfirmListener(OnConfirmListener listener, String title) {
        this.listener = listener;
        this.txt_title = title;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tv_delete = view.findViewById(R.id.tv_delete);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(txt_title);
        tv_delete.setText(okay);
        tv_cancel.setText(cancel);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onConfirm();
                dismiss();

//                Toast.makeText(getActivity(), "Repost Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }




    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public interface OnConfirmListener {
        void onConfirm();
    }
}

