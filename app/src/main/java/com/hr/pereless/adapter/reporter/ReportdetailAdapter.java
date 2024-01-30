package com.hr.pereless.adapter.reporter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.report.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportdetailAdapter extends RecyclerView.Adapter<ReportdetailAdapter.MyViewHolder> {

    private List<ReportModel> jobListList = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, genre;

        LinearLayout report_detail_layout;

        public MyViewHolder(View view) {
            super(view);

            report_detail_layout = (LinearLayout) view.findViewById(R.id.report_detail_layout);

        }
    }

    public void addAll(List<ReportModel> jobListList){
        this.jobListList.addAll(jobListList);
        notifyDataSetChanged();

    }

    public ReportdetailAdapter( Context context) {
        this.jobListList = jobListList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_reportname, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReportModel movie = jobListList.get(position);

        holder.report_detail_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent report = new Intent(context, TableviewActivity.class);
                context.startActivity(report);

            }
        });


    }

    @Override
    public int getItemCount() {
        return jobListList.size();
    }
}
