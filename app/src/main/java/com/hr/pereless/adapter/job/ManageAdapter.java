package com.hr.pereless.adapter.job;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.model.job.RecruiterModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.DataObjectHolder> {


    Context context;
    List<RecruiterModel> helpSupports;
    String value;
    int count;
    private OnSelectListener listener;




    public ManageAdapter( List<RecruiterModel> helpSupportList,Context context,OnSelectListener listener) {

        this.context = context;
        this.helpSupports = helpSupportList;
        this.listener = listener;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{



        TextView txt_username;

        CheckBox checkBox;
        CircleImageView imv_image;


        public DataObjectHolder(View itemView){
            super(itemView);


            txt_username=(TextView)itemView.findViewById(R.id.txt_username);

            checkBox=(CheckBox) itemView.findViewById(R.id.checkbox);
            imv_image = itemView.findViewById(R.id.imv_image);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_hiringmanager, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        final RecruiterModel recruiterModel = helpSupports.get(position);
        holder.txt_username.setText(helpSupports.get(position).getUserName());
        holder.checkBox.setChecked(recruiterModel.isSelected());
        Glide.with(context).load(recruiterModel.getAvatar()).placeholder(R.drawable.ic_dummy).dontAnimate().into(holder.imv_image);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onEmailSelect(position,isChecked);
            }
        });
    }
    public interface OnSelectListener{
        void onEmailSelect(int id,boolean state);
    }

    @Override
    public int getItemCount() {
        return helpSupports.size();
    }


}


