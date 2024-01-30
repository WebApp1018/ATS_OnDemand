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
import com.hr.pereless.activities.report.ReportActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.report.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private List<ReportModel> reportLists = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, genre;

        LinearLayout mainlayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txttitle);
            count = (TextView) view.findViewById(R.id.txt_count);
            mainlayout=(LinearLayout)view.findViewById(R.id.main_layout);


        }
    }
    public List<ReportModel> getMovies() {
        return reportLists;
    }
    public void addAll(List<ReportModel> reportLists){
        this.reportLists.addAll(reportLists);
        notifyDataSetChanged();
    }

    public ReportAdapter( Context context) {
        this.reportLists = reportLists;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_report, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReportModel movie = reportLists.get(position);
//        holder.title.setText(movie.getTittle());
//        holder.count.setText(movie.getCount());

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CommonActivity)context).goTo(context,ReportActivity.class,false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reportLists.size();
    }
}