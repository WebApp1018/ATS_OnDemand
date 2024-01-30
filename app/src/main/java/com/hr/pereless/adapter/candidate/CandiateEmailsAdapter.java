package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.email.EmailModel;

import java.util.ArrayList;
import java.util.List;

public class CandiateEmailsAdapter extends RecyclerView.Adapter<CandiateEmailsAdapter.MyViewHolder> {


    Context context;
    private List<EmailModel> emaildatumList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_description, txt_email,txv_time;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);


            txt_name = (TextView) view.findViewById(R.id.txt_name);
            txt_description = (TextView) view.findViewById(R.id.txt_description);
            txt_email = (TextView) view.findViewById(R.id.txt_email);
            img = (ImageView) view.findViewById(R.id.img);
            txv_time = view.findViewById(R.id.txv_time);

        }
    }


    public CandiateEmailsAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<EmailModel> moveResults) {
        emaildatumList = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_email, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final EmailModel emailModel = emaildatumList.get(position);
        holder.txt_name.setText(emailModel.getAction());
        holder.txt_description.setText(emailModel.getNotes());
       // holder.txt_email.setText(emaildatumList.get(position).getEmail());
        holder.txv_time.setText(Commons.TimelinedateTime(emailModel.getEnterdate()));
       // Glide.with(context).load(emaildatumList.get(position).getSenderimage()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return emaildatumList.size();
    }
}