package com.hr.pereless.adapter.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.candidate.CandidateDetailActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.scheduleNew.ScheduleViewFragment;
import com.hr.pereless.model.schedule.ScheduleModel;
import com.hr.pereless.model.candidate.CandidateModel;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {

    private Context _context;
    ArrayList<ScheduleModel> schedules = new ArrayList<>();
    ScheduleViewFragment scheduleViewFragment;
    public ScheduleAdapter(Context context  , ScheduleViewFragment scheduleViewFragment) {

        super();
        this._context = context;
        this.scheduleViewFragment = scheduleViewFragment;
    }


    public void setRoomData(ArrayList<ScheduleModel> schedules ) {
        this.schedules.clear();
        this.schedules.addAll(schedules);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final CustomHolder holder;
        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schedule_item, parent, false);
            holder.txt_title = (TextView) convertView.findViewById(R.id.txv_title);
            holder.txv_start_time = (TextView) convertView.findViewById(R.id.txv_start_time);
            holder.txv_end_time = (TextView) convertView.findViewById(R.id.txv_end_time);
            holder.txv_date = (TextView) convertView.findViewById(R.id.txv_date);
            holder.view_candidate = (TextView) convertView.findViewById(R.id.view_candidate);
            holder.btn_update = (TextView) convertView.findViewById(R.id.btn_update);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        final ScheduleModel scheduleModel = schedules.get(position);
        holder.txt_title.setText(scheduleModel.getSubject());
        holder.txv_date.setText(Commons.dateTime(scheduleModel.getStart_time()));
        holder.txv_start_time.setText(Commons.HoursTodateTime(scheduleModel.getStart_time()));
        holder.txv_end_time.setText(Commons.HoursTodateTime(scheduleModel.getEnd_time()));
        if(scheduleModel.getStatus().equals("1")){
            holder.btn_update.setBackground( _context.getResources().getDrawable(R.drawable.button_bg_green));
        }else{
            holder.btn_update.setBackground(_context.getResources().getDrawable(R.drawable.button_bg_yellow));
        }

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scheduleModel.getStatus().equals("0"))
                    scheduleViewFragment.confirmSchedule(scheduleModel.getSch_id());
            }
        });
        holder.view_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scheduleModel.getRid() == null || scheduleModel.getRid().length() == 0 ){
                    ((CommonActivity)(_context)).showAlertDialog("The candidate is unavailable" );
                    return;
                }
                CandidateModel candidateModel = new CandidateModel();
                candidateModel.setRid(Integer.parseInt(scheduleModel.getRid()));
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                String newfeedentity = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",newfeedentity);
                ((CommonActivity)_context).startActivityForResult(new Intent(_context, CandidateDetailActivity.class).putExtra("data",bundle),1);
                ((CommonActivity)_context).overridePendingTransition(0, 0);
            }
        });


        return convertView;
    }


    public class CustomHolder {
        TextView txt_title,txv_start_time,txv_end_time,txv_date,view_candidate,btn_update;
    }

    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


