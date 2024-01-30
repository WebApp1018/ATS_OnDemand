package com.hr.pereless.adapter.candidate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hr.pereless.R;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.candidate.ScorecardModel;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.model.candidate.ScorecardUserModel;
import com.hr.pereless.util.RoundedCornersTransformation;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ScorecardCommentAdapter extends RecyclerView.Adapter<ScorecardCommentAdapter.MyViewHolder> {


    Context context;
    private List<ScorecardUserModel> sectionModels = new ArrayList<>();
    ScoreOptionModel scoreOptionModel = new ScoreOptionModel();
    int type ;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imv_image;
       TextView txv_content,txv_name;
        public MyViewHolder(View view) {
            super(view);
            imv_image =  view.findViewById(R.id.imv_image);
            txv_content = view.findViewById(R.id.txv_content);
            txv_name = view.findViewById(R.id.txv_name);
        }
    }


    public ScorecardCommentAdapter(Context context, ScoreOptionModel scoreOptionModel , int type) {
        this.context = context;
        this.scoreOptionModel = scoreOptionModel;
        this.type =type;
        if(type ==0 ){
            setDate(Commons._scoreUserModels);
        }else {
            ArrayList<ScorecardUserModel>_usermodels = new ArrayList<>();
            for(int i =0;i< Commons._scoreUserModels.size();i++){
                for(int j =0;j<Commons._scoreUserModels.get(i).getScorecardModel().getSectionModels().size();j++){
                    SectionModel sectionModel= Commons._scoreUserModels.get(i).getScorecardModel().getSectionModels().get(j);
                    if(sectionModel.getSCID() == scoreOptionModel.getFkSCID()){
                        for(int k =0;k<sectionModel.getScoreOptionModels().size();k++){
                            if (sectionModel.getScoreOptionModels().get(k).getPsSCOID().equals(scoreOptionModel.getPsSCOID())) {
                                ScorecardUserModel scorecardUserModel = new ScorecardUserModel();
                                scorecardUserModel.setFirstname(Commons._scoreUserModels.get(i).getFirstname());
                                scorecardUserModel.setLastname(Commons._scoreUserModels.get(i).getLastname());
                                scorecardUserModel.setUserlink(Commons._scoreUserModels.get(i).getUserlink());
                                ScorecardModel scorecardModel = new ScorecardModel();
                                scorecardModel.setNote(sectionModel.getScoreOptionModels().get(k).getNote());
                                scorecardUserModel.setScorecardModel(scorecardModel);
                                _usermodels.add(scorecardUserModel);
                            }
                        }
                    }
                }
            }
            setDate(_usermodels);
        }
    }

    public void setDate(List<ScorecardUserModel> moveResults) {
        sectionModels = moveResults;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorecard_comment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ScorecardUserModel scorecardUserModel = sectionModels.get(position);

        Glide.with(context).load(scorecardUserModel.getUserlink()).placeholder(R.drawable.ic_dummy).into(holder.imv_image);
        holder.txv_name.setText(scorecardUserModel.getFirstname()+ " " + scorecardUserModel.getLastname());
        holder.txv_content.setText(scorecardUserModel.getScorecardModel().getNote());
    }

    @Override
    public int getItemCount() {
        return sectionModels.size();
    }
}