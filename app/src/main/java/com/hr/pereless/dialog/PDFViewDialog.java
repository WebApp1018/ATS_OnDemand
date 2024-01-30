package com.hr.pereless.dialog;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.onboarding.DocumentModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class PDFViewDialog extends DialogFragment {

    private OnConfirmListener listener;
    DocumentModel documentModel = new DocumentModel();
    //PDFView pdfView;
    ImageView imv_content;
    int type ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.insurance_view_dialog, container, false);
    }

    public ConfirmDialog setOnConfirmListener(OnConfirmListener listener,DocumentModel documentModel,int type) {
        this.listener = listener;
        this.documentModel = documentModel;
        this.type = type;
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView txv_title = view.findViewById(R.id.txv_title);
        TextView txv_time = view.findViewById(R.id.txv_time);
        imv_content = view.findViewById(R.id.imv_content);
        TextView txv_download = view.findViewById(R.id.txv_download);
        TextView txv_ok = view.findViewById(R.id.txv_ok);
     //   pdfView = view.findViewById(R.id.pdfView);
//
//        String file_name = qualifications.get(0).getFile();
//        if(qualifications.get(0).getFile().contains(".pdf")){
//            new DownloadFileFromURL().execute(file_name);
//
//        }
//        else {
//            Glide.with(getActivity()).load(file_name).placeholder(R.drawable.image_thumnail).into(imv_content);
//        }
//        txv_download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                listener.onDownload();
////                Toast.makeText(getActivity(), "Repost Successfully", Toast.LENGTH_SHORT).show();
//            }
//        });
        txv_ok.setOnClickListener(new View.OnClickListener() {
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
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public interface OnConfirmListener {
        void onDownload();
    }

    public class DownloadFileFromURL extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            ((CommonActivity)getContext()).showProgress();
            super.onPreExecute();

        }

        /**
         * Downloading file in background thread
         * */
        File file;
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String[] str = f_url[0].split("/");
                file = new File(path, str[str.length-1]);
                if(file.exists()) {
                    Log.d("bbbbbbbb","File exist");
                    return null;
                }

                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream

                OutputStream output = new FileOutputStream(file);

                byte[] data = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                ((CommonActivity)getContext()).closeProgress();
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
//            ((CommonActivity)getContext()).closeProgress();
//            Bitmap bitmap = ((CommonActivity)getContext()).generateImageFromPdf(Uri.fromFile(file));
//            Glide.with(getActivity()).load(bitmap).placeholder(R.drawable.image_thumnail).into(imv_content);
        }

    }
}

