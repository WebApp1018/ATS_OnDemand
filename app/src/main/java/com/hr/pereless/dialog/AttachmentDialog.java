package com.hr.pereless.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;


public class AttachmentDialog extends DialogFragment {

    private OnConfirmListener listener;
    String title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_upload_option, container, false);
    }

    public AttachmentDialog setOnConfirmListener(OnConfirmListener listener) {
        this.listener = listener;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final LinearLayout linear_gallery = (LinearLayout) view.findViewById(R.id.linear_gallery);
        final LinearLayout linear_camera = (LinearLayout) view.findViewById(R.id.linear_camera);
        final LinearLayout linear_word = (LinearLayout) view.findViewById(R.id.linear_word);
        final LinearLayout linear_pdf = (LinearLayout) view.findViewById(R.id.linear_pdf);
        final LinearLayout linear_doc = (LinearLayout) view.findViewById(R.id.linear_doc);
        final LinearLayout linear_loc = (LinearLayout) view.findViewById(R.id.linear_location);

        final TextView gallery = (TextView) view.findViewById(R.id.txt_one);
        final TextView camera = (TextView) view.findViewById(R.id.txt_two);
        final TextView word = (TextView) view.findViewById(R.id.txt_three);
        final TextView pdf = (TextView) view.findViewById(R.id.txt_four);
        final TextView loc = (TextView) view.findViewById(R.id.txt_six);
        final TextView doc = (TextView) view.findViewById(R.id.txt_five);

        linear_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.galleryIntent();
                dismiss();
            }
        });

        linear_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectLocation();
                dismiss();
            }
        });

        linear_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.galleryIntent();
                dismiss();
            }
        });

        linear_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getPdf();
                dismiss();
            }
        });

        linear_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getWord();
                dismiss();

            }
        });

        linear_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getCamera();

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
        void galleryIntent();
        void selectLocation();
        void getWord();
        void getPdf();
        void getCamera();
    }
}

