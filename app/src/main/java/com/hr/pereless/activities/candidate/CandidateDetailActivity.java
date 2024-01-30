package com.hr.pereless.activities.candidate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.diffey.view.progressview.ProgressView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.email.EmailSendActivity;
import com.hr.pereless.activities.schedule.ScheduleEditActivity;
import com.hr.pereless.adapter.ViewPagerAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.CommunicationDialog;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.fragment.candidate.AttachmentFragment;
import com.hr.pereless.fragment.candidate.CandidateEmailsFragment;
import com.hr.pereless.fragment.candidate.CommunicationFragment;
import com.hr.pereless.fragment.candidate.ExperiencekFragment;
import com.hr.pereless.fragment.candidate.QuestionFragment;
import com.hr.pereless.fragment.candidate.QuickNotesFragment;
import com.hr.pereless.fragment.candidate.ReferenceFragment;
import com.hr.pereless.fragment.candidate.TimeLineFragment;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.CandidateUserModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.model.job.RecruiterModel;
import com.hr.pereless.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CandidateDetailActivity extends CommonActivity {
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_like_unlike)
    Button btnLikeUnlike;
    @BindView(R.id.btn_menu)
    Button btnMenu;
    @BindView(R.id.progressBarthree)
    ProgressView progressBarthree;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.relativLikeunlike)
    RelativeLayout relativLikeunlike;
    @BindView(R.id.relativDots)
    RelativeLayout relativDots;
    @BindView(R.id.img_unfilled_star)
    ImageView imgUnfilledStar;
    @BindView(R.id.img_menu)
    ImageView imgMenu;

    Dialog dots;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.smslayout)
    LinearLayout smslayout;
    @BindView(R.id.emaillayout)
    LinearLayout emaillayout;
    @BindView(R.id.schedulelayout)
    LinearLayout schedulelayout;
    @BindView(R.id.dots_layout)
    RelativeLayout dotsLayout;

    int dotstatus = 0;
    Dialog communication;
    @BindView(R.id.arclayout_ly)
    RelativeLayout arclayoutLy;
    @BindView(R.id.center_icon)
    ImageView centerIcon;
    @BindView(R.id.img_filled_star)
    ImageView imgFilledStar;


    @BindView(R.id.txt_candidatename)
    TextView txtCandidatename;
    @BindView(R.id.txt_email)
    TextView txtEmail;
    @BindView(R.id.txt_mobilenumber)
    TextView txtMobilenumber;

    @BindView(R.id.img_cndidateicon)
    CircleImageView imgCndidateicon;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.calendar)
    RelativeLayout calendar;
    @BindView(R.id.eeo)
    RelativeLayout eeo;
    @BindView(R.id.chat)
    RelativeLayout chat;
    @BindView(R.id.notepad)
    RelativeLayout notepad;
    @BindView(R.id.email)
    RelativeLayout email;
    @BindView(R.id.share)
    RelativeLayout share;
    @BindView(R.id.man)
    RelativeLayout man;
    @BindView(R.id.path)
    RelativeLayout path;
    @BindView(R.id.linear_mobile_number)
    LinearLayout linearMobileNumber;
    @BindView(R.id.lyt_jobapplied)
    LinearLayout lyt_jobapplied;
    @BindView(R.id.imv_left)
    ImageView imv_left;
    @BindView(R.id.txt_job)
    TextView txt_job;
    @BindView(R.id.imv_right)
    ImageView imv_right;
    @BindView(R.id.txv_currentstage)
    TextView txv_currentstage;
    @BindView(R.id.lyt_stage)
    LinearLayout lyt_stage;
    String candidate_mobile_number = "";
    String CandidateId, like;
    int progress = 0;
    int count =0;
    CandidateModel candidateModel= new CandidateModel();
    int selected_job = 0,select_stage,past_select_stoage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_detail);
        ButterKnife.bind(this);
        tabs.setupWithViewPager(viewpager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#FFFECC53"));
        tabs.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        tabs.setTabTextColors(Color.parseColor("#C0C0C0"), Color.parseColor("#FFFECC53"));

        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }

        showProgress();
        count=0;
        togetData(0);
        togetData(1);
    }
    void initLayout(){
        setupViewPager();
        txtCandidatename.setText(candidateModel.getCandidateUserModel().getName());
        txtEmail.setText(candidateModel.getCandidateUserModel().getEmails());
        txtMobilenumber.setText(candidateModel.getCandidateUserModel().getMobile());
        Glide.with(_context).load(candidateModel.getCandidateUserModel().getImage()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, "#FFFECC53", Commons.glide_boder))).into(imgCndidateicon);
        if(Commons.appliedJobModelList.size()==0){
            lyt_jobapplied.setVisibility(View.GONE);
        }else
            initStoage();
    }

    void initStoage(){
        txt_job.setText(Commons.appliedJobModelList.get(selected_job).getJob_title());
        for(int i =0;i<Commons.recruitflowModels.size();i++){
            if(Commons.appliedJobModelList.get(selected_job).getRecruitFlowID().equals(Commons.recruitflowModels.get(i).getRecruitFlowID())){
                progressBarthree.setProgressColor(Color.parseColor(Commons.recruitflowModels.get(i).getColor()));
                txv_currentstage.setText(Commons.recruitflowModels.get(i).getRecruitFlowName());
                progressBarthree.setProgress((i+1)*100/Commons.recruitflowModels.size());
                select_stage = i;
                break;
            }
        }
    }
    void togetData(int type){
        String api_link = API.GET_CANDIDATEUSER + String.valueOf(candidateModel.getRid());
        if(type ==1){
            api_link = API.GET_JOBAPPLIED + String.valueOf(candidateModel.getRid());
        }

        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        count++;
                        try {
                            if(type ==0) {
                                JSONObject jsonObject = new JSONObject(json);
                                Log.d("aaaaaaaa",json);
                                CandidateUserModel candidateUserModel = new CandidateUserModel();
                                candidateUserModel.initModel(jsonObject);
                                candidateModel.setCandidateUserModel(candidateUserModel);
                                if(jsonObject.getString("message").contains("Invalid candidate Id")){
                                    showAlertDialog(jsonObject.getString("message"));
                                }
                            }else if(type ==1){
                                JSONArray jsonArray = new JSONArray(json);
                                Commons.appliedJobModelList.clear();
                                for(int i =0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    AppliedJobModel appliedJobModel = new AppliedJobModel();
                                    appliedJobModel.initModel(jsonObject);
                                    Commons.appliedJobModelList.add(appliedJobModel);
                                }
                            }
                            if(count == 2){
                                closeProgress();
                                initLayout();
                            }
                        }catch (Exception e){
                            closeProgress();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        closeProgress();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", Commons.token);
                return params;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(myRequest, "tag");

    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TimeLineFragment.newInstance(candidateModel), "TimeLine");
        adapter.addFragment(ExperiencekFragment.newInstance(candidateModel), "Experience");
        //adapter.addFragment(CommunicationFragment.newInstance(), "Communication");
        adapter.addFragment(CandidateEmailsFragment.newInstance(candidateModel), "Emails");

        adapter.addFragment(AttachmentFragment.newInstance(), "Uploaded");
        adapter.addFragment(ReferenceFragment.newInstance(candidateModel), "Reference");
        adapter.addFragment(QuickNotesFragment.newInstance(candidateModel), "Quick Notes");
       // adapter.addFragment(QuestionFragment.newInstance(), "Questions");
        viewpager.setAdapter(adapter);
    }


    void moveCandidate(int posstion){
        int page_count =0;
        RecruitflowModel recruitflowModel = Commons.recruitflowModels.get(posstion);
        if(recruitflowModel.isRFEEO()){
            page_count+=9;
        }
        if(  recruitflowModel.isRFHMD() || recruitflowModel.isRFcommentlog()||
                recruitflowModel.isRFDocument()){
            page_count+=5;
        }
        if(recruitflowModel.isRFQuickNote() ){
            page_count+=3;
        }
        if(page_count>0) {
            AppliedJobModel jobModel = Commons.appliedJobModelList.get(selected_job);
            Bundle bundle = new Bundle();
            Gson gson = new Gson();
            String candidate = gson.toJson(candidateModel);
            String recruitModel = gson.toJson(recruitflowModel);
            String job_model = gson.toJson(jobModel);
            bundle.putString("CandidateModel", candidate);
            bundle.putString("RecruitModel", recruitModel);
            bundle.putString("AppliedJobModel", job_model);
            bundle.putInt("pastStage",select_stage);
            bundle.putInt("currentStage",posstion);
            startActivityForResult(new Intent(this, UpdateStoageActivity.class).putExtra("data", bundle), 1);
            arclayoutLy.setVisibility(View.GONE);
        }else {
            assignJob(posstion);
        }
    }

    void assignJob(int posstion){
        showProgress();
        String api_link = API.PUT_MOVE_STOAGE ;
        JSONObject params = new JSONObject();
        try {
            params.put("flownum",select_stage+1);
            params.put("jid",Commons.appliedJobModelList.get(selected_job).getJid());
            params.put("rid",candidateModel.getRid());
            params.put("fkjobowner",Integer.parseInt(Commons.appliedJobModelList.get(selected_job).getJobowner()));
            params.put("toflownum",posstion+1);
            params.put("abpostoffice",1); // select letter

        } catch (JSONException e) {

        }
        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    closeProgress();
                    Toast.makeText(this, "Recruiter Flow Moved", Toast.LENGTH_SHORT).show();
                    select_stage = posstion;
                    showProgress();
                    count =1;
                    togetData(1);
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @OnClick({R.id.linear_mobile_number, R.id.man, R.id.path, R.id.email, R.id.share, R.id.notepad, R.id.calendar, R.id.chat, R.id.eeo, R.id.img_filled_star, R.id.img_unfilled_star, R.id.center_icon, R.id.img_menu, R.id.emaillayout, R.id.schedulelayout, R.id.smslayout, R.id.relativDots, R.id.btn_back, R.id.btn_like_unlike, R.id.btn_menu
            ,R.id.imv_left,R.id.imv_right,R.id.lyt_stage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_mobile_number:
                Intent intent_call = new Intent(Intent.ACTION_DIAL);
                intent_call.setData(Uri.parse("tel:" + candidate_mobile_number));
                startActivity(intent_call);
                break;
            case R.id.btn_back:
                onBackPressed();
                break;

            case R.id.emaillayout:
//                Intent intent = new Intent(CandidateDetailActivity.this, MainActivity.class);
//                intent.putExtra("mainactivitystatus", "3");
//                startActivity(intent);
//               /* Intent emailactivity=new Intent(CandidateDetailActivity.this,EmailsActivity.class);
//                startActivity(emailactivity);*/
                break;

            case R.id.eeo:
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                String candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                goTo(this,EEOActivity.class,false,bundle);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.email:
                goTo(this,EmailSendActivity.class,false);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.calendar:
                //scheudle relative
                goTo(this,CandidateScheduleone.class,false);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.path:
//                Intent mainactivity = new Intent(CandidateDetailActivity.this, MainActivity.class);
//                mainactivity.putExtra("mainactivitystatus", "2");
//                startActivity(mainactivity);
                arclayoutLy.setVisibility(View.GONE);
                Intent resultInt = new Intent();
                resultInt.putExtra("mainactivitystatus", 2);
                setResult(RESULT_OK,resultInt);
                finish();
                break;

            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, "kjfkhgfkjfkhfkfffddshgcmbckhjvkmflkj");
                startActivity(Intent.createChooser(share, "Share link!"));
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.notepad:
                bundle = new Bundle();
                gson = new Gson();
                candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                goTo(this,Questions.class,false,bundle);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.man:
                bundle = new Bundle();
                gson = new Gson();
                candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                goTo(this,AssignjobActivity.class,false,bundle);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.chat:
                bundle = new Bundle();
                gson = new Gson();
                candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                goTo(this,CommentActivity.class,false,bundle);
                arclayoutLy.setVisibility(View.GONE);
                break;

            case R.id.center_icon:
                arclayoutLy.setVisibility(View.GONE);

                break;

            case R.id.img_unfilled_star:
                imgFilledStar.setVisibility(View.VISIBLE);
                imgUnfilledStar.setVisibility(View.GONE);

                like = "like";

                break;

            case R.id.img_filled_star:
                imgFilledStar.setVisibility(View.GONE);
                imgUnfilledStar.setVisibility(View.VISIBLE);
                like = "unlike";

                break;

            case R.id.img_menu:
                arclayoutLy.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_like_unlike:
                bundle = new Bundle();
                gson = new Gson();
                candidate = gson.toJson(candidateModel);
                bundle.putString("CandidateModel",candidate);
                goTo(this,ScoreCardActivity.class,false,bundle);
                break;

            case R.id.btn_menu:
                Log.e("TAG", "Button Menu");
                if (dotstatus == 0) {
                    dotsLayout.setVisibility(View.VISIBLE);
                    dotstatus = 1;
                } else {
                    dotsLayout.setVisibility(View.GONE);
                    dotstatus = 0;
                }
                break;

            case R.id.smslayout:
                dotsLayout.setVisibility(View.GONE);
                goTo(this,SmsActivity.class,false);
                break;

            case R.id.schedulelayout:
                dotsLayout.setVisibility(View.GONE);
                goTo(this,ScheduleEditActivity.class,false);
                break;

            case R.id.relativDots:
                if (dotstatus == 0) {
                    dotsLayout.setVisibility(View.VISIBLE);
                    dotstatus = 1;
                } else {
                    dotsLayout.setVisibility(View.GONE);
                    dotstatus = 0;
                }
                break;
            case R.id.imv_left:
                selected_job  = (Commons.appliedJobModelList.size()+selected_job-1)% Commons.appliedJobModelList.size();
                initStoage();
                break;
            case R.id.imv_right:
                selected_job  = (Commons.appliedJobModelList.size()+selected_job+1)% Commons.appliedJobModelList.size();
                initStoage();
                break;
            case R.id.lyt_stage:
                ArrayList<String>arrayList = new ArrayList<>();
                for(int i =0;i<Commons.recruitflowModels.size();i++)
                    arrayList.add(Commons.recruitflowModels.get(i).getRecruitFlowName());
                SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
                selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        moveCandidate(selectPosstion);
                    }
                },arrayList);
                selectJobOneDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            onResume();
            count = 1;
            showProgress();
            togetData(1);
        }
    }
}