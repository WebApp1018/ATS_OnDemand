package com.hr.pereless.activities.candidate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.ScorecardAdapter;
import com.hr.pereless.adapter.candidate.ScorecarddetailAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.candidate.ScorecardModel;
import com.hr.pereless.fragment.candidate.SectionModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.candidate.ScoreOptionModel;
import com.hr.pereless.model.candidate.ScorecardUserModel;
import com.hr.pereless.model.onboarding.CurrencyModel;
import com.hr.pereless.util.RoundedCornersTransformation;
import com.hr.pereless.view.OverlappingImageView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreCardActivity extends CommonActivity {
    int status;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.imageView1)
    RoundedImageView imageView1;
    @BindView(R.id.img_double_dislike)
    ImageView imgDoubleDislike;
    @BindView(R.id.img_like)
    ImageView imgLike;
    @BindView(R.id.img_dislike)
    ImageView imgDislike;
    @BindView(R.id.img_none)
    ImageView img_none;
    @BindView(R.id.img_double_like)
    ImageView imgDoubleLike;
    @BindView(R.id.img_back)
    ImageView imgBack;
    Context context;
    @BindView(R.id.tick)
    ImageView tick;
    @BindView(R.id.save_btn)
    Button save_btn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.spinner_jobselection)
    MaterialSpinner spinner_jobselection;
    @BindView(R.id.overlay_view)
    OverlappingImageView overlay_view;
    @BindView(R.id.lyt_team_scoreccard)
    LinearLayout lyt_team_scoreccard;
    @BindView(R.id.lyt_scorecard)
    LinearLayout lyt_scorecard;
    @BindView(R.id.edit_score)
    EditText edit_score;
    @BindView(R.id.txv_total_score)
    TextView txv_total_score;
    @BindView(R.id.txv_name)
    TextView txv_name;

    CandidateModel candidateModel = new CandidateModel();
    int select_job=0,api_count =0;
    ScorecardModel select_scorecardModel = new ScorecardModel();
    ArrayList<ScorecardUserModel>scorecardUserModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        ButterKnife.bind(this);
        txtTitle.setText("Score Card");
        context = ScoreCardActivity.this;
        tick.setVisibility(View.VISIBLE);

        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }
        Glide.with(_context).load(candidateModel.getCandidateUserModel().getImage()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, "#FFFECC53", Commons.glide_boder))).into(imageView1);
        txv_name.setText(candidateModel.getCandidateUserModel().getName());
        ArrayList<String>appliedjob_title = new ArrayList<>();
        for(int i =0;i< Commons.appliedJobModelList.size();i++){
            appliedjob_title.add(Commons.appliedJobModelList.get(i).getJob_title());
        }
        spinner_jobselection.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                select_job = position;
                api_count = 0 ;
                showProgress();
                loadAllscorecard();

            }
        });
        spinner_jobselection.setItems(appliedjob_title);
        showProgress();
        loadAllscorecard();

    }
    void initLayout(){
        if(select_scorecardModel.getSGID().equals("")){
            showAlertDialog(getResources().getString(R.string.no_scorecard));
            lyt_scorecard.setVisibility(View.GONE);
            return;
        }else
            lyt_scorecard.setVisibility(View.VISIBLE);
        int total_mark =0;
        ArrayList<Integer>arrayList = new ArrayList<>();
        for(int i =0;i<scorecardUserModels.size();i++){
            ScorecardUserModel scorecardUserModel = scorecardUserModels.get(i);
            total_mark +=scorecardUserModel.getScorecardModel().getScore();
            arrayList.add(R.drawable.placeholder_1);
            if(scorecardUserModel.getUid() == Commons.g_user.getId()){
                changeDefaultModel(scorecardUserModel.getScorecardModel());
            }
        }
        overlay_view.setThumbnailDrawableRes(arrayList, false);
        txv_total_score.setText(String.valueOf(total_mark));
        edit_score.setText(select_scorecardModel.getNote());
        status = select_scorecardModel.getScore();
        setScore();
        ScorecardAdapter scorecardAdapter = new ScorecardAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(scorecardAdapter);
        scorecardAdapter.setDate(select_scorecardModel.getSectionModels());
    }
    void changeDefaultModel(ScorecardModel scorecardModel){
        select_scorecardModel.setScore(scorecardModel.getScore());
        select_scorecardModel.setNote(scorecardModel.getNote());
        for(int i =0;i<scorecardModel.getSectionModels().size();i++){
            int kk=-1;
            for(int j=0;j<select_scorecardModel.getSectionModels().size();j++){
                if(scorecardModel.getSectionModels().get(i).getSCID() == select_scorecardModel.getSectionModels().get(j).getSCID()){
                    kk=j;
                    break;
                }
            }
            if(kk==-1)continue;
            for(int j =0;j<scorecardModel.getSectionModels().get(i).getScoreOptionModels().size();j++){
                ScoreOptionModel scoreOptionModel = scorecardModel.getSectionModels().get(i).getScoreOptionModels().get(j);
                for(int k =0;k<select_scorecardModel.getSectionModels().get(kk).getScoreOptionModels().size();k++){
                    if(scoreOptionModel.getPsSCOID().equals(select_scorecardModel.getSectionModels().get(kk).getScoreOptionModels().get(k).getPsSCOID())){
                        select_scorecardModel.getSectionModels().get(kk).getScoreOptionModels().get(k).setNote(scoreOptionModel.getNote());
                        select_scorecardModel.getSectionModels().get(kk).getScoreOptionModels().get(k).setScore(scoreOptionModel.getScore());
                        break;
                    }
                }
            }
        }
    }

    void setScore(){
        Log.e("TAG","STATUS"+status);
        imgDoubleDislike.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        img_none.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        imgLike.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        imgDoubleLike.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        imgDislike.setBackgroundResource(R.drawable.btn_layout_red_unselect);
        imgDoubleDislike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        imgLike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        imgDoubleLike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        imgDislike.setColorFilter(ContextCompat.getColor(context, R.color.colorlightred), PorterDuff.Mode.MULTIPLY);
        switch (status) {
            case -2:
                imgDoubleDislike.setBackgroundResource(R.drawable.btn_layout_select_red);
                imgDoubleDislike.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.MULTIPLY);
                break;
            case -1:
                imgDislike.setBackgroundResource(R.drawable.btn_layout_select_red);
                imgDislike.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.MULTIPLY);
                break;
            case 0:
                img_none.setBackgroundResource(R.drawable.btn_layout_select_red);
                break;
            case 1:
                imgLike.setBackgroundResource(R.drawable.btn_layout_select_red);
                imgLike.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                imgDoubleLike.setBackgroundResource(R.drawable.btn_layout_select_red_right);
                imgDoubleLike.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.MULTIPLY);
                break;

        }
    }
    void loadAllscorecard(){
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        select_scorecardModel = new ScorecardModel();
        if(Commons.appliedJobModelList.get(select_job).getSGID().equals("") || Commons.appliedJobModelList.get(select_job).getSGID().equals("null")){
            closeProgress();
            initLayout();
            return;
        }
        apiConnection(API.GET_SCORECARD + "/" + Commons.appliedJobModelList.get(select_job).getSGID(),params, Request.Method.GET);
        loadJobscorecards();
    }
    void loadJobscorecards(){
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
      //  String api_link = API.GET_PAGE_SCORECARD + "/" + String.valueOf(candidateModel.getRid())+"/" + String.valueOf(Commons.appliedJobModelList.get(select_job).getJid());
       String api_link = API.GET_PAGE_SCORECARD + "/" + String.valueOf(85315)+"/" + String.valueOf(Commons.appliedJobModelList.get(select_job).getJid());
        apiConnection(api_link,params, Request.Method.GET);
    }


    @Override
    public void parseResponse(String api_link,String json){
        closeProgress();
        try {
            api_count++;
            if(api_link.equals(API.GET_SCORECARD + "/" + Commons.appliedJobModelList.get(select_job).getSGID())) {
                JSONObject jsonObject = new JSONObject(json);
                select_scorecardModel.initModel(jsonObject);
            }else {
                JSONArray jsonArray= new JSONArray(json);
                scorecardUserModels.clear();
                for(int i =0;i<jsonArray.length();i++){
                    ScorecardUserModel scorecardUserModel = new ScorecardUserModel();
                    scorecardUserModel.initModel(jsonArray.getJSONObject(i));
                    scorecardUserModels.add(scorecardUserModel);
                }
            }
            if(api_count==2){
                initLayout();
            }
        }catch (Exception e){

        }
    }
    void updateScorecard(int type){
        String api_link = API.PUT_UPDTESCORECARD;
        showProgress();
        try {
            JSONArray jsonArray =  new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("JID",Commons.appliedJobModelList.get(select_job).getJid());
            jsonObject.put("RID",candidateModel.getRid());
            jsonObject.put("fkSCID","");
            jsonObject.put("note",edit_score.getText().toString());
            jsonObject.put("recruiterID",Commons.g_user.getId());
            jsonObject.put("score",String.valueOf(status));
            jsonObject.put("scoreorder",0);
            jsonObject.put("scoretype",0);
            int k =0;
            jsonArray.put(jsonObject);
            for(int i =0;i<select_scorecardModel.getSectionModels().size();i++){
                SectionModel sectionModel = select_scorecardModel.getSectionModels().get(i);
                for(int j =0;j<sectionModel.getScoreOptionModels().size();j++){
                    k++;
                    ScoreOptionModel scoreOptionModel = sectionModel.getScoreOptionModels().get(j);
                    jsonObject = new JSONObject();
                    jsonObject.put("JID",Commons.appliedJobModelList.get(select_job).getJid());
                    jsonObject.put("RID",candidateModel.getRid());
                    jsonObject.put("fkSCID",scoreOptionModel.getFkSCID());
                    jsonObject.put("note",scoreOptionModel.getNote());
                    jsonObject.put("recruiterID",Commons.g_user.getId());
                    jsonObject.put("score",String.valueOf(scoreOptionModel.getScore()));
                    jsonObject.put("scoreorder",k);
                    jsonObject.put("scoretype",1);
                    jsonArray.put(jsonObject);
                }
            }
            Log.d("aaaaa",jsonArray.toString());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PUT, api_link, jsonArray, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    closeProgress();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    closeProgress();
                    if(error.toString().contains("Success")){
                        showToast(getResources().getString(R.string.success_update));
                        if(type == 1)
                            finish(ScoreCardActivity.this);
                    }

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", Commons.token);
                    return headers;
                }
            };
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(jsonArrayRequest, "tag");

        } catch (Exception e) {
            closeProgress();
        }
    }

    @OnClick({R.id.lyt_team_scoreccard,R.id.save_btn,R.id.tick,R.id.img_double_dislike, R.id.img_none,R.id.img_like, R.id.img_dislike, R.id.img_double_like, R.id.img_back})
            public void onViewClicked(View view) {
                switch (view.getId()) {
                    case R.id.img_double_dislike:
                        status = -2;
                        setScore();
                        break;
            case R.id.img_like:
                status = 1;
                setScore();
                break;
            case R.id.img_none:
                status = 0;
                setScore();
                break;
            case R.id.img_dislike:
                status = -1;
                setScore();
                break;
            case R.id.img_double_like:
                status = 2;
                setScore();
                break;

            case R.id.img_back:
                finish(this);
                break;

            case R.id.tick:
                updateScorecard(1);
                break;
            case R.id.save_btn:
                updateScorecard(0);
                break;
            case R.id.lyt_team_scoreccard:
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                String candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                String scoremodel = gson.toJson(select_scorecardModel);
                bundle.putString("ScoreModel",scoremodel);
                Commons._scoreUserModels = scorecardUserModels;
                goTo(this,TeamScorecardDetailActivity.class,false,bundle);
                break;
        }
    }
}