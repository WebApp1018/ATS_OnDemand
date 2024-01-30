package com.hr.pereless.adapter.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CommunicationModel;

import java.util.ArrayList;

public class JobChartAdapter extends BaseAdapter {

    private Context _context;

    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public JobChartAdapter(Context context) {

        super();
        this._context = context;
    }


    public void setRoomData(ArrayList<Integer> values, ArrayList<String> titles ) {
        this.values = values;
        this.titles = titles;

        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
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
            convertView = inflater.inflate(R.layout.adapter_jobchart_item, parent, false);
            holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            holder.txtagencycanditate = (TextView) convertView.findViewById(R.id.txtagencycanditate);
            holder.progressbar = convertView.findViewById(R.id.progressbar);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }
        holder.txt_title.setText(titles.get(position));
        holder.txtagencycanditate.setText(values.get(position).toString());
        holder.progressbar.setProgress(values.get(position));

        return convertView;
    }


    public class CustomHolder {
        TextView txt_title,txtagencycanditate;
        ProgressBar progressbar;
    }

    protected boolean isItemSelected(int position) {
        //return !selectedItems.isEmpty() && selectedItems.contains(getItem(position));
        return true;
    }
}


