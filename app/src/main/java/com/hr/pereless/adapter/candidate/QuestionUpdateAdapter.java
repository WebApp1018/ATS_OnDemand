package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.QuestionModel;
import com.hr.pereless.model.candidate.eeo.EeoDisposstionModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class QuestionUpdateAdapter extends BaseAdapter {

    private Context _context;

    public ArrayList<QuestionModel> _roomDatas = new ArrayList<>();
    public QuestionUpdateAdapter(Context context) {

        super();
        this._context = context;
    }


    public void setRoomData(ArrayList<QuestionModel> data) {
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
            convertView = inflater.inflate(R.layout.question_item, parent, false);
            holder.txv_number = (TextView) convertView.findViewById(R.id.txv_number);
            holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        final QuestionModel questionModel = _roomDatas.get(position);
        int number = position+1;
        holder.txv_number.setText(String.valueOf(number)+": ");
        holder.txt_name.setText(questionModel.getQuestion());
        return convertView;
    }


    public class CustomHolder {
        TextView txv_number, txt_name;
    }

    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


