package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ScoreCardCommentDialog;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.view.OverlappingImageView;

import java.util.ArrayList;
import java.util.List;

public class TeamScoredetailAdapter extends RecyclerView.Adapter<TeamScoredetailAdapter.MyViewHolder>{


    Context context;
    private List<ScoreOptionModel> scoreOptionModels = new ArrayList<>();




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txv_title,txv_feedback;
        RecyclerView recyclerview;
        public MyViewHolder(View view) {
            super(view);
            txv_title = view.findViewById(R.id.txv_title);
            txv_feedback = view.findViewById(R.id.txv_feedback);
            recyclerview = view.findViewById(R.id.recyclerview);

        }
    }


    public TeamScoredetailAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<ScoreOptionModel> moveResults) {
        scoreOptionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teamscorecard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ScoreOptionModel scoreOptionModel = scoreOptionModels.get(position);
        holder.txv_title.setText(scoreOptionModel.getOptionName());
        ArrayList<Integer>arrayList = new ArrayList<>();
        boolean commment_flag = false;
        for(int i =0;i< Commons._scoreUserModels.size();i++){
            for(int j =0;j<Commons._scoreUserModels.get(i).getScorecardModel().getSectionModels().size();j++){
                SectionModel sectionModel= Commons._scoreUserModels.get(i).getScorecardModel().getSectionModels().get(j);
                if(sectionModel.getSCID() == scoreOptionModel.getFkSCID()){
                    for(int k =0;k<sectionModel.getScoreOptionModels().size();k++){
                        if (sectionModel.getScoreOptionModels().get(k).getPsSCOID().equals(scoreOptionModel.getPsSCOID())) {
                            if(sectionModel.getScoreOptionModels().get(k).getScore()==-2)
                                arrayList.add(R.drawable.doubledislike);
                            else if(sectionModel.getScoreOptionModels().get(k).getScore()==-1)
                                arrayList.add(R.drawable.dislike_one);
                            else if(sectionModel.getScoreOptionModels().get(k).getScore()==0)
                                arrayList.add(R.drawable.gray_circle);

                            else if(sectionModel.getScoreOptionModels().get(k).getScore()==1)
                                arrayList.add(R.drawable.like_one);
                            else if(sectionModel.getScoreOptionModels().get(k).getScore()==2)
                                arrayList.add(R.drawable.doublelike);
                            if(sectionModel.getScoreOptionModels().get(k).getNote().length()>0){
                                commment_flag =true;
                            }
                        }
                    }
                }
            }
        }
        if(commment_flag)holder.txv_feedback.setVisibility(View.VISIBLE);
        else holder.txv_feedback.setVisibility(View.GONE);
        TeamScoredetailitemAdapter teamScoredetailAdapter = new TeamScoredetailitemAdapter(context);
        holder.recyclerview.setLayoutManager( new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        teamScoredetailAdapter.setHasStableIds(true);
        holder.recyclerview.setAdapter(teamScoredetailAdapter);
        teamScoredetailAdapter.setDate(arrayList);

        holder.txv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreCardCommentDialog scoreCardCommentDialog = new ScoreCardCommentDialog();
                scoreCardCommentDialog.setOnConfirmListener(new ScoreCardCommentDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {

                    }
                },scoreOptionModel,1);
                scoreCardCommentDialog.show(((CommonActivity)context).getSupportFragmentManager(),"title");
            }
        });

    }

    @Override
    public int getItemCount() {
        return scoreOptionModels.size();
    }
}