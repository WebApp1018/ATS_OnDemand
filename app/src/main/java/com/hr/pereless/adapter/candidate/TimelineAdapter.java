package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {


    Context context;
    private List<TimeLineModel> timelinedatumList= new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtend, lessdescription, moredescription,seemore,seeless,txt_name,txt_msg,txt_loc,txt_time,txt_position;
        LinearLayout lyt_type3,lyt_type2,lyt_type1;
        ImageView imageplace,img;
        TextView txt_post_time,txt_owner;
        CardView cardview;
        public MyViewHolder(View view) {
            super(view);


            txtend= (TextView) view.findViewById(R.id.text_end);
            lessdescription= (TextView) view.findViewById(R.id.lessdescription);
            moredescription= (TextView) view.findViewById(R.id.moredescription);
            seeless= (TextView) view.findViewById(R.id.seelesss);

            seemore= (TextView) view.findViewById(R.id.seemore);

            txt_name= (TextView) view.findViewById(R.id.txt_name);
            txt_msg= (TextView) view.findViewById(R.id.txt_msg);
            txt_loc= (TextView) view.findViewById(R.id.txt_loc);
            txt_time= (TextView) view.findViewById(R.id.txt_time);
            txt_position= (TextView) view.findViewById(R.id.txt_position);
            img=view.findViewById(R.id.img);
            lyt_type3 = view.findViewById(R.id.lyt_type3);
            lyt_type2 = view.findViewById(R.id.lyt_type2);
            lyt_type1 = view.findViewById(R.id.lyt_type1);
            txt_post_time = view.findViewById(R.id.txt_post_time);
            txt_owner = view.findViewById(R.id.txt_owner);
            cardview = view.findViewById(R.id.cardview);




        }
    }


    public TimelineAdapter(Context context) {
        this.context=context;
    }

    public void setDate(List<TimeLineModel> timelinedatumList){
        this.timelinedatumList.addAll(timelinedatumList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_timeline_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final TimeLineModel timeLineModel = timelinedatumList.get(position);
        holder.lyt_type1.setVisibility(View.GONE);
        holder.lyt_type2.setVisibility(View.GONE);
        holder.lyt_type3.setVisibility(View.GONE);
        if(timeLineModel.getType()==1){
            holder.lyt_type1.setVisibility(View.VISIBLE);
        }else if(timeLineModel.getType()==2){
            holder.lyt_type2.setVisibility(View.VISIBLE);

        }else {
            holder.lyt_type3.setVisibility(View.VISIBLE);
        }

        holder.txt_name.setText(timeLineModel.getAction());
        holder.txt_msg.setText(Html.fromHtml(timeLineModel.getNotes()));
        holder.txt_post_time.setText(Commons.TimelinedateTime(timeLineModel.getEnterdate()));
        holder.txt_owner.setText("~~"+ Html.fromHtml(timeLineModel.getCanname()));
        holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.color_icon));
        if(timeLineModel.getIcon().equals("fa-quote-right"))
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icons_quote));
        else if(timeLineModel.getIcon().equals("fa-envelope-o"))
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_envelope));
        else if(timeLineModel.getIcon().equals("fa-file-text-o"))
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icons_file));
        else if(timeLineModel.getIcon().equals("fa-comment"))
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icons_topic));
        else if(timeLineModel.getIcon().equals("fa-minus-circle")) {
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.color_icon1));
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icons_minus));
        } else if(timeLineModel.getIcon().equals("fa-plus-circle")) {
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.icons_plus));
        }

        holder.seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.seemore.setVisibility(View.VISIBLE);
                holder.seeless.setVisibility(View.GONE);

                holder.moredescription.setVisibility(View.GONE);

                holder.lessdescription.setVisibility(View.VISIBLE);

            }
        });



        holder.seeless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.seemore.setVisibility(View.GONE);
                holder.seeless.setVisibility(View.VISIBLE);

                holder.moredescription.setVisibility(View.VISIBLE);

                holder.lessdescription.setVisibility(View.GONE);

            }
        });


    }

    @Override
    public int getItemCount() {
        return timelinedatumList.size();
    }
}
