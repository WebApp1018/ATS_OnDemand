package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.model.job.RecruiterModel;

import java.util.ArrayList;
import java.util.List;

public class EEoAdapter extends RecyclerView.Adapter<EEoAdapter.DataObjectHolder> {


    Context context;
    List<RecruiterModel> helpSupports = new ArrayList<>();
    OnSelectListener listener;
    int selectedPosition = 0;

    public EEoAdapter(List<RecruiterModel> helpSupportList, Context context, OnSelectListener listener) {

        this.context = context;
        this.helpSupports.clear();
        this.helpSupports.addAll(helpSupportList);
        this.listener = listener;

    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView txt_username;
        CheckBox checkBox;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txt_username = (TextView) itemView.findViewById(R.id.txt_username);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_eeo, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.txt_username.setText(helpSupports.get(position).getUserName());
        holder.checkBox.setOnCheckedChangeListener(null);
        if (helpSupports.get(position).getStatus().equals("1")) {
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    helpSupports.get(position).setStatus("1");
                } else {
                    helpSupports.get(position).setStatus("0");
                }
                if(selectedPosition!=position)
                    helpSupports.get(selectedPosition).setStatus("0");
                notifyItemChanged(position);
                notifyItemChanged(selectedPosition);
                selectedPosition = position;

                if(isChecked)
                    listener.onSelect(position);
                else
                    listener.onSelect(-1);
            }
        });


    }


    @Override
    public int getItemCount() {
        return helpSupports.size();
    }

    public interface OnSelectListener {
        void onSelect(int selectPosstion);
    }
}

