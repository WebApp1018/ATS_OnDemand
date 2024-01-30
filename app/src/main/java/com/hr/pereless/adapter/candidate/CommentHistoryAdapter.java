package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CommunicationModel;
import com.hr.pereless.model.candidate.QuestionModel;

import java.util.ArrayList;

public class CommentHistoryAdapter extends BaseAdapter {

    private Context _context;

    public ArrayList<CommunicationModel> _roomDatas = new ArrayList<>();
    public CommentHistoryAdapter(Context context) {

        super();
        this._context = context;
    }


    public void setRoomData(ArrayList<CommunicationModel> data) {
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
            convertView = inflater.inflate(R.layout.adapter_quiknotes, parent, false);
            holder.txt_notestitle = (TextView) convertView.findViewById(R.id.txt_notestitle);
            holder.txv_content = (TextView) convertView.findViewById(R.id.lesstext);
            holder.txt_time = (TextView) convertView.findViewById(R.id.txt_time);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        final CommunicationModel communicationModel = _roomDatas.get(position);
        holder.txt_notestitle.setText(communicationModel.getCanName());
        holder.txv_content.setText(communicationModel.getComment());
        holder.txt_time.setText(Commons.TimelinedateTime(communicationModel.getDate()));
        return convertView;
    }


    public class CustomHolder {
        TextView txt_notestitle;
        TextView txv_content,txt_time;
    }

    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


