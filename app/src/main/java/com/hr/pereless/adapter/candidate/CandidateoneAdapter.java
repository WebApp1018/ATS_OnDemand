package com.hr.pereless.adapter.candidate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;

public class CandidateoneAdapter extends RecyclerView.Adapter<CandidateoneAdapter.MyViewHolder> {


    Context context;
    Dialog dialogdelete;
    int size;
    OnDeleteListener listener;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView delete;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_name);
            delete = (ImageView) view.findViewById(R.id.img_delete);

        }
    }


    public CandidateoneAdapter(Context contextm,OnDeleteListener listener) {
        this.context = context;
        this.listener = listener;
    }
    public void setData(int size){
        this.size = size;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adptercandidate, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(position);
            }
        });

    }

    public interface OnDeleteListener {
        void onDelete(int possition);
    }
    @Override
    public int getItemCount() {
        return size;
    }
}