package com.hr.pereless.adapter.candidate;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.AttachmentModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttachmentAdpter  extends RecyclerView.Adapter<AttachmentAdpter.MyViewHolder> {


    private List<AttachmentModel> attachementList = new ArrayList<>();

    Context context;
    String file_name,url,type;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_attachment,txt_loc;

        LinearLayout linear_loc,linear_file;
        public MyViewHolder(View view) {
            super(view);

            txt_attachment = (TextView) view.findViewById(R.id.txt_attachment);
            txt_loc = (TextView) view.findViewById(R.id.txt_loc);
            linear_file = (LinearLayout) view.findViewById(R.id.linear_file);
            linear_loc = (LinearLayout) view.findViewById(R.id.linear_loc);



        }
    }


    public AttachmentAdpter(Context context) {
        this.context = context;

    }
    public void setData(List<AttachmentModel> attachementList){
        this.attachementList = attachementList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_attachments, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //JobList movie = quicknoteListList.get(position);
//
//
//        if (!attachementList.get(position).getLocation().equals("")){
//            holder.txt_loc.setText(attachementList.get(position).getLocation());
//
//            holder.linear_file.setVisibility(View.GONE);
//            holder.linear_loc.setVisibility(View.VISIBLE);
//        }
//
//        if (!attachementList.get(position).getAttachamnent().equals("")){
//            holder.txt_attachment.setText(attachementList.get(position).getAttachamnent());
//            holder.linear_loc.setVisibility(View.GONE);
//            holder.linear_file.setVisibility(View.VISIBLE);
//
//        }
//
//
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                file_name=attachementList.get(position).getFilename();
//                url=attachementList.get(position).getAttachamnent();
//                type=attachementList.get(position).getType();
//
//                //  url="index2.jpg";
//                //  new DownloadFile().execute(url);
//
//
//                openView(url,position,type);
//            }
//        });

    }

    private void openView(String url,int position,String type) {
//
//        try {
//            if (file_name.toString().contains(".jpg") || file_name.toString().contains(".jpeg") || file_name.toString().contains(".png")) {
//                // JPG file
//                Intent img=new Intent(context, ZoomImageActivity.class);
//                img.putExtra("url",url);
//                img.putExtra("file","img");
//                context.startActivity(img);
//            } else if (type.equals("location")) {
//
//                Intent img=new Intent(context, MapActivity.class);
//                img.putExtra("lat",attachementList.get(position).getLatitude());
//                img.putExtra("long",attachementList.get(position).getLongitude());
//                context.startActivity(img);
//            }
//            else {
//                Intent img=new Intent(context, ZoomImageActivity.class);
//                img.putExtra("url",url);
//                img.putExtra("file","file");
//                context.startActivity(img);
//            }
//            /*else if (file_name.toString().contains(".pdf")) {
//
//                // PDF file
//                File file = new File(Environment.getExternalStorageDirectory(),
//                        file_name);
//                Uri path = Uri.fromFile(file);
//                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
//                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pdfOpenintent.setDataAndType(path, "application/pdf");
//                try {
//                    context.startActivity(pdfOpenintent);
//                }
//                catch (ActivityNotFoundException e) {
//
//                }
//            }*/
//
//
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(context, "No application found which can open the file", Toast.LENGTH_SHORT).show();
//        }



    }
    private void openFile(String url) {

        try {

            Uri uri = Uri.fromFile(new File(url));

            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip")) {
                // ZIP file
                intent.setDataAndType(uri, "application/zip");
            } else if (url.toString().contains(".rar")){
                // RAR file
                intent.setDataAndType(uri, "application/x-rar-compressed");
            } else if (url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                    url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                // Video files
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No application found which can open the file", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = "Document" + timestamp + "_" + fileName;

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "HR Document/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d("download", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded

            progressDialog.dismiss();

            //  openFile(url);
            // Toast.makeText(context, getString(R.string.download_success) + getString(R.string.download_path), Toast.LENGTH_SHORT).show();
        }
    }
    private void openXLS(){
        File xls = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "prova.xlsx");
        Uri path = Uri.fromFile(xls);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/excel");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No Application available to view XLS", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        return attachementList.size();
    }
}




