package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.fragment.candidate.SectionModel;

import java.util.ArrayList;
import java.util.List;

public class ScorecardAdapter extends RecyclerView.Adapter<ScorecardAdapter.MyViewHolder> {


    Context context;
    private List<SectionModel> sectionModels = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
       RecyclerView recyclerView;
       TextView txv_title;
        public MyViewHolder(View view) {
            super(view);

            recyclerView =  view.findViewById(R.id.recyclerview);
            txv_title = view.findViewById(R.id.txv_title);
        }
    }


    public ScorecardAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<SectionModel> moveResults) {
        sectionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorecard_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final SectionModel sectionModel = sectionModels.get(position);
        holder.txv_title.setText(sectionModel.getScoreName());
        ScorecarddetailAdapter scorecarddetailAdapter = new ScorecarddetailAdapter(context);
        holder.recyclerView.setLayoutManager( new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        scorecarddetailAdapter.setHasStableIds(true);
        holder.recyclerView.setAdapter(scorecarddetailAdapter);
        scorecarddetailAdapter.setDate(sectionModel.getScoreOptionModels());


    }

    @Override
    public int getItemCount() {
        return sectionModels.size();
    }
}