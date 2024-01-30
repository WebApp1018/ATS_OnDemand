package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.EducationModel;

import java.util.ArrayList;
import java.util.List;

public class EducationAdpter extends RecyclerView.Adapter<EducationAdpter.MyViewHolder> {


    private List<EducationModel> educationList = new ArrayList<>();

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_date,txt_des,txt_type;

        public MyViewHolder(View view) {
            super(view);

            txt_date = (TextView) view.findViewById(R.id.txt_date);
            txt_des = (TextView) view.findViewById(R.id.txt_des);
            txt_type = (TextView) view.findViewById(R.id.txt_type);

        }
    }


    public EducationAdpter(Context context) {
        this.context = context;

    }

    public void setData(List<EducationModel> educationList ){
        this.educationList = educationList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_education, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //JobList movie = quicknoteListList.get(position);

//        holder.txt_des.setText(educationList.get(position).getDescription());
//        holder.txt_date.setText(educationList.get(position).getYears());
//        holder.txt_type.setText(educationList.get(position).getType());


    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }
}

