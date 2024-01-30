package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.PointModel;

import java.util.List;

public class PointAdpter extends RecyclerView.Adapter<PointAdpter.MyViewHolder> {


    private List<PointModel> pointList;

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_point;

        public MyViewHolder(View view) {
            super(view);

            txt_point = (TextView) view.findViewById(R.id.txt_point);

        }
    }


    public PointAdpter(List<PointModel> pointList, Context context) {
        this.pointList = pointList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_point, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //JobList movie = quicknoteListList.get(position);

        //holder.txt_point.setText(pointList.get(position).getPoint());


    }

    @Override
    public int getItemCount() {
        return pointList.size();
    }
}



