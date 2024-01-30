package com.hr.pereless.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.pereless.R;
import com.hr.pereless.model.MenuModel;

import java.util.List;


public class LeftNavAdapter extends ArrayAdapter<MenuModel> {

    Context context;

    public LeftNavAdapter(Context context, int resourceId,
                          List<MenuModel> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    public class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtNotifcation;

        RelativeLayout layoutcircle;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        MenuModel rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_main_left_drawer_menu, null);
            holder = new ViewHolder();
             holder.txtTitle = (TextView) convertView.findViewById(R.id.txv_nave);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_nave);
            holder.txtNotifcation = (TextView)convertView.findViewById(R.id.txt_notification);

            holder.layoutcircle = (RelativeLayout) convertView.findViewById(R.id.layoutcircle);


            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getMenu_name());
        holder.imageView.setImageResource(rowItem.getMenu_img());
        if(position==0){
            holder.layoutcircle.setVisibility(View.VISIBLE);
        }else {
            holder.layoutcircle.setVisibility(View.GONE);
        }

        return convertView;
    }
}