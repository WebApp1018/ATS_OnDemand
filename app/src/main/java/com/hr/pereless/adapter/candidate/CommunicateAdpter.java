package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.CommunicationModel;

import java.util.ArrayList;
import java.util.List;

public class CommunicateAdpter extends RecyclerView.Adapter<CommunicateAdpter.MyViewHolder> {


    private List<CommunicationModel> commmessageList = new ArrayList<>();

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_msg;

        public MyViewHolder(View view) {
            super(view);

            txt_msg = (TextView) view.findViewById(R.id.txt_msg);

        }
    }


    public CommunicateAdpter( Context context) {
        this.context = context;

    }
    public void setDate(List<CommunicationModel> commmessageList ){
        this.commmessageList = commmessageList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_comunication, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //JobList movie = quicknoteListList.get(position);

        //holder.txt_msg.setText(commmessageList.get(position).getMessage());


    }

    @Override
    public int getItemCount() {
        return commmessageList.size();
    }
}

