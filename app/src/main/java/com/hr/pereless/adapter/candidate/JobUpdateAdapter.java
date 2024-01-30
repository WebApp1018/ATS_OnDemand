package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hr.pereless.R;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.eeo.EeoDisposstionModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class JobUpdateAdapter extends BaseAdapter {

    private Context _context;

    public ArrayList<AppliedJobModel> _roomDatas = new ArrayList<>();
    ArrayList<EeoDisposstionModel>eeoDisposstionModels = new ArrayList<>();
    ArrayList<String >disposstionTitle = new ArrayList<>();
    public JobUpdateAdapter(Context context) {

        super();
        this._context = context;
    }


    public void setRoomData(ArrayList<AppliedJobModel> data,ArrayList<EeoDisposstionModel>eeoDisposstionModels,ArrayList<String >disposstionTitle ) {
        _roomDatas = data;
        this.eeoDisposstionModels = eeoDisposstionModels;
        this.disposstionTitle = disposstionTitle;
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
            convertView = inflater.inflate(R.layout.job_item_update, parent, false);
            holder.txv_number = (TextView) convertView.findViewById(R.id.txv_number);
            holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            holder.spinner_disposition =  convertView.findViewById(R.id.spinner_disposition);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        final AppliedJobModel appliedJobModel = _roomDatas.get(position);
        int number = position+1;
        holder.txv_number.setText(String.valueOf(number)+".");
        holder.txt_name.setText(appliedJobModel.getDname() + ": " + appliedJobModel.getJob_title() + "-" +
                appliedJobModel.getCity()+", " +  appliedJobModel.getState() + "[" + String.valueOf(appliedJobModel.getJid()+ "]"+
                " in" + appliedJobModel.getFlowname()));
        holder.spinner_disposition.setItems(disposstionTitle);
        return convertView;
    }


    public class CustomHolder {
        TextView txv_number, txt_name;
       MaterialSpinner spinner_disposition;
    }

    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


