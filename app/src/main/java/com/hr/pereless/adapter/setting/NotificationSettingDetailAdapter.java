package com.hr.pereless.adapter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.ScorecarddetailAdapter;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.setting.GeneralModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettingDetailAdapter extends RecyclerView.Adapter<NotificationSettingDetailAdapter.MyViewHolder> {


    Context context;
    private List<GeneralModel> sectionModels = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView txv_content;
       Switch switch_on;
        public MyViewHolder(View view) {
            super(view);
            switch_on = view.findViewById(R.id.switch_on);
            txv_content = view.findViewById(R.id.txv_content);
        }
    }


    public NotificationSettingDetailAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<GeneralModel> moveResults) {
        sectionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_setting_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final GeneralModel sectionModel = sectionModels.get(position);
        holder.txv_content.setText(sectionModel.getTitle());
        holder.switch_on.setChecked(sectionModel.isFlag());
        holder.switch_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sectionModel.setFlag(!sectionModel.isFlag());

            }
        });

    }

    @Override
    public int getItemCount() {
        return sectionModels.size();
    }
}