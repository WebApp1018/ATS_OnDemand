package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;

import java.util.ArrayList;
import java.util.List;

public class TeamScoredetailitemAdapter extends RecyclerView.Adapter<TeamScoredetailitemAdapter.MyViewHolder>{


    Context context;
    private List<Integer> scoreOptionModels = new ArrayList<>();




    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        public MyViewHolder(View view) {
            super(view);
            imageView1 = view.findViewById(R.id.imageView1);

        }
    }


    public TeamScoredetailitemAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<Integer> moveResults) {
        scoreOptionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Integer image = scoreOptionModels.get(position);
        Glide.with(context).load(image).placeholder(R.drawable.ic_dummy).dontAnimate().into(holder.imageView1);
        if(image == R.drawable.dislike_one || image == R.drawable.doubledislike){
            holder.imageView1.setColorFilter(context.getResources().getColor(R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        }else if(image == R.drawable.like_one || image == R.drawable.doublelike)
            holder.imageView1.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.MULTIPLY);
        else
            holder.imageView1.setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public int getItemCount() {
        return scoreOptionModels.size();
    }
}