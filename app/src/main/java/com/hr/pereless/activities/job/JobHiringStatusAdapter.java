package com.hr.pereless.activities.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hr.pereless.R;
import com.hr.pereless.model.job.JobFlowModel;

import java.util.ArrayList;

public class JobHiringStatusAdapter extends BaseAdapter {

    private Context _context;

    public ArrayList<JobFlowModel> _roomDatas = new ArrayList<>();

    public JobHiringStatusAdapter(Context context) {

        super();
        this._context = context;
    }


    public void setRoomData(ArrayList<JobFlowModel> data) {
        _roomDatas = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return _roomDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return _roomDatas.get(position);
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
            convertView = inflater.inflate(R.layout.job_recruiterflow_item, parent, false);
            holder.txv_title = (TextView) convertView.findViewById(R.id.txv_title);
            holder.txv_value = (TextView) convertView.findViewById(R.id.txv_value);
            holder.progress =  convertView.findViewById(R.id.progressBarone);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        final JobFlowModel noti_item = _roomDatas.get(position);
        holder.txv_title.setText(noti_item.getFlowname());;
        holder.txv_value.setText(String.valueOf(noti_item.getFlowcount()));
        holder.progress.setProgress(noti_item.getFlowcount());
        return convertView;
    }


    public class CustomHolder {
        TextView txv_title, txv_value;
        ProgressBar progress;
    }


    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


