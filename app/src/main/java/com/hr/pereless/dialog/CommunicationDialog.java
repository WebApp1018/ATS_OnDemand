package com.hr.pereless.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.warkiz.widget.IndicatorSeekBar;


public class CommunicationDialog extends DialogFragment {

    private OnConfirmListener listener;
    String title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dailog_message, container, false);
    }

    public CommunicationDialog setOnConfirmListener(OnConfirmListener listener,String title) {
        this.listener = listener;
        this.title = title;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText edtmessge=(EditText)view.findViewById(R.id.notes_box);
        TextView send=(TextView)view.findViewById(R.id.send);
        TextView txttitletext=(TextView)view.findViewById(R.id.txttitletext);
        txttitletext.setText(title);
        ImageView close=(ImageView)view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtmessge.getText().toString().length()==0){
                    ((CommonActivity)getContext()).showAlertDialog("Please input message");
                    return;
                }
                listener.onConfirm(edtmessge.getText().toString());
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
        void onConfirm(String message);
    }
}

