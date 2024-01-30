package com.hr.pereless.activities.candidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.ScorecardAdapter;
import com.hr.pereless.adapter.candidate.TeamScoreCardAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ScoreCardCommentDialog;
import com.hr.pereless.fragment.candidate.ScorecardModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.model.candidate.ScorecardUserModel;
import com.hr.pereless.view.OverlappingImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamScorecardDetailActivity extends CommonActivity {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txv_total_score)
    TextView txv_total_score;
    @BindView(R.id.overlay_view)
    OverlappingImageView overlay_view;

    @BindView(R.id.txt_verygood)
    TextView txt_verygood;
    @BindView(R.id.progress_very_good)
    ProgressBar progress_very_good;
    @BindView(R.id.txt_good)
    TextView txt_good;
    @BindView(R.id.progress_good)
    ProgressBar progress_good;
    @BindView(R.id.txt_neutral)
    TextView txt_neutral;
    @BindView(R.id.progress_neutral)
    ProgressBar progress_neutral;
    @BindView(R.id.txt_poor)
    TextView txt_poor;
    @BindView(R.id.progress_poor)
    ProgressBar progress_poor;
    @BindView(R.id.txt_verypoor)
    TextView txt_verypoor;
    @BindView(R.id.progress_very_poor)
    ProgressBar progress_very_poor;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.txt_showfeedback)
    TextView txt_showfeedback;
    ScorecardModel scorecardModel = new ScorecardModel();
    CandidateModel candidateModel = new CandidateModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scorecard_detail);
        ButterKnife.bind(this);
        txtTitle.setText(getResources().getString(R.string.team_score_card));
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
                feed = bundle.getString("ScoreModel");
                scorecardModel = gson.fromJson(feed,ScorecardModel.class);
            }
        }
        initLayout();

    }

    void initLayout(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        int total_mark = 0,verygood =0,good =0, neutral= 0 , poor =0 , verypoor = 0;
        for(int i =0;i<Commons._scoreUserModels.size();i++){
            ScorecardUserModel scorecardUserModel = Commons._scoreUserModels.get(i);
            total_mark +=scorecardUserModel.getScorecardModel().getScore();
            arrayList.add(R.drawable.placeholder_1);
            if(scorecardUserModel.getScorecardModel().getScore()== -2)
                verypoor ++;
            else if(scorecardUserModel.getScorecardModel().getScore()==-1)
                poor++;
            else if(scorecardUserModel.getScorecardModel().getScore()==0)
                neutral++;
            else if(scorecardUserModel.getScorecardModel().getScore()==1)
                good++;
            else if(scorecardUserModel.getScorecardModel().getScore()==2)
                verygood++;
        }
        overlay_view.setThumbnailDrawableRes(arrayList, false);
        txv_total_score.setText(String.valueOf(total_mark));
        txt_verygood.setText(String.valueOf(verygood));
        txt_good.setText(String.valueOf(good));
        txt_neutral.setText(String.valueOf(neutral));
        txt_poor.setText(String.valueOf(poor));
        txt_verypoor.setText(String.valueOf(verypoor));
        progress_very_good.setProgress(verygood*100/Commons._scoreUserModels.size());
        progress_good.setProgress(good*100/Commons._scoreUserModels.size());
        progress_neutral.setProgress(neutral*100/Commons._scoreUserModels.size());
        progress_poor.setProgress(poor*100/Commons._scoreUserModels.size());
        progress_very_poor.setProgress(verypoor*100/Commons._scoreUserModels.size());

        TeamScoreCardAdapter teamScoreCardAdapter = new TeamScoreCardAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(teamScoreCardAdapter);
        teamScoreCardAdapter.setDate(scorecardModel.getSectionModels());
    }
    @OnClick({R.id.img_back,R.id.txt_showfeedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish(this);
                break;
            case R.id.txt_showfeedback:
                ScoreCardCommentDialog scoreCardCommentDialog = new ScoreCardCommentDialog();
                scoreCardCommentDialog.setOnConfirmListener(new ScoreCardCommentDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {

                    }
                },new ScoreOptionModel(),0);
                scoreCardCommentDialog.show(getSupportFragmentManager(),"title");
                break;
        }
    }
}