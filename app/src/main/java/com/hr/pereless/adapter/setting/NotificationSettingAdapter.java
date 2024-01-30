package com.hr.pereless.adapter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.ScorecarddetailAdapter;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.setting.GeneralModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettingAdapter extends RecyclerView.Adapter<NotificationSettingAdapter.MyViewHolder> {


    Context context;
    ArrayList<ArrayList<GeneralModel>>generalModels = new ArrayList<>();
    ArrayList<String>arrayList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
       RecyclerView recyclerView;
       TextView txv_title;
        public MyViewHolder(View view) {
            super(view);

            recyclerView =  view.findViewById(R.id.recyclerview);
            txv_title = view.findViewById(R.id.txv_title);
        }
    }


    public NotificationSettingAdapter(Context context) {
        this.context = context;
    }

    public void setDate(ArrayList<String> arrayList,ArrayList<ArrayList<GeneralModel>>generalModels ) {
        this.arrayList = arrayList;
        this.generalModels = generalModels;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorecard_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.txv_title.setText(arrayList.get(position));
        NotificationSettingDetailAdapter notificationSettingDetailAdapter = new NotificationSettingDetailAdapter(context);
        holder.recyclerView.setLayoutManager( new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView.setAdapter(notificationSettingDetailAdapter);
        notificationSettingDetailAdapter.setDate(generalModels.get(position));


    }

    @Override
    public int getItemCount() {
        return generalModels.size();
    }
}