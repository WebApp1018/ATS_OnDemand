package com.hr.pereless.adapter.helpTraning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hr.pereless.R;
import com.hr.pereless.model.NotificationModel;
import com.hr.pereless.model.TraningModel;

import java.util.ArrayList;
import java.util.List;

public class DochelpAdapter extends RecyclerView.Adapter<DochelpAdapter.MyViewHolder> {

    private List<TraningModel> moviesList = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_task;

        ImageView task_image;

        public MyViewHolder(View view) {
            super(view);
            text_task = (TextView) view.findViewById(R.id.text_task);
            task_image = (ImageView) view.findViewById(R.id.task_image);


        }
    }


    public DochelpAdapter( Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_task, parent, false);

        return new MyViewHolder(itemView);
    }
    public void addAll(List<TraningModel> moviesList){
        this.moviesList.clear();
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();

    }
    public List<TraningModel> getMovies() {
        return moviesList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TraningModel movie = moviesList.get(position);
        // holder.text_task.setText(movie.getPosition());

        Glide.with(context).load(R.drawable.doc2).into(holder.task_image);


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}