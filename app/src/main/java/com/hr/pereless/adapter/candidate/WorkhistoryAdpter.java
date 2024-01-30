package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.PointModel;
import com.hr.pereless.model.candidate.WorkHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class WorkhistoryAdpter  extends RecyclerView.Adapter<WorkhistoryAdpter.MyViewHolder> {


    private List<WorkHistoryModel> workHistoryList =new ArrayList<>();

    Context context;
    PointAdpter pointAdpter;

    List<PointModel>pointList=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_product,txt_start_date,txt_end_date,txt_service;
        public  RecyclerView recycle;

        public MyViewHolder(View view) {
            super(view);

            txt_product = (TextView) view.findViewById(R.id.txt_product);
            txt_start_date = (TextView) view.findViewById(R.id.txt_start_date);
            txt_end_date = (TextView) view.findViewById(R.id.txt_end_date);
            txt_service = (TextView) view.findViewById(R.id.txt_service);

            recycle = (RecyclerView) view.findViewById(R.id.recycle);


        }
    }


    public WorkhistoryAdpter( Context context) {
        this.context = context;

    }

    public void setData(List<WorkHistoryModel> workHistoryList){
        this.workHistoryList = workHistoryList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_workhistory, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //JobList movie = quicknoteListList.get(position);

//        holder.txt_product.setText(workHistoryList.get(position).getTittle());
//        holder.txt_service.setText(workHistoryList.get(position).getSubtitle());
//        holder.txt_start_date.setText(workHistoryList.get(position).getStartDate());
//        holder.txt_end_date.setText(workHistoryList.get(position).getEndDate());

        pointList=workHistoryList.get(position).getPointModels();
        pointAdpter = new PointAdpter(pointList, context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        holder.recycle.setLayoutManager(mLayoutManager);
        holder.recycle.setItemAnimator(new DefaultItemAnimator());
        holder.recycle.setAdapter(pointAdpter);

    }

    @Override
    public int getItemCount() {
        return workHistoryList.size();
    }
}


