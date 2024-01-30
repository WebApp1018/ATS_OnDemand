package com.hr.pereless.activities.job;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.ConfirmDialog;
import com.hr.pereless.dialog.JobEditDialog;
import com.hr.pereless.dialog.JobHiringDetailDialog;
import com.hr.pereless.dialog.JobHiringDialog;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.model.job.RecruiterModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.next.tagview.TagCloudView;

public class JobDetailActivity extends CommonActivity {
    @BindView(R.id.onlinestatus)
    LinearLayout onlinestatus;
    @BindView(R.id.candidate_layout)
    LinearLayout candidateLayout;
    @BindView(R.id.star)
    ImageView star;
    @BindView(R.id.starunfilled)
    ImageView starunfilled;
    @BindView(R.id.usericon)
    ImageView usericon;
    @BindView(R.id.edit_icon)
    ImageView editIcon;
    @BindView(R.id.ziprecuriter)
    ImageView ziprecuriter;
    @BindView(R.id.glassdorr)
    ImageView glassdorr;
    @BindView(R.id.linkedin)
    ImageView linkedin;
    @BindView(R.id.clone)
    Button clone;
    @BindView(R.id.deactivate)
    Button deactivate;
    @BindView(R.id.close)
    Button close;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.jobhistory_layout)
    LinearLayout jobhistoryLayout;
    @BindView(R.id.jobdescription_layout)
    LinearLayout jobdescriptionLayout;
    @BindView(R.id.custom_layout)
    LinearLayout customLayout;
    @BindView(R.id.save_btn)
    Button saveBtn;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.edit_description)
    EditText editDescription;
    static List<RecruiterModel>recruiterModels = new ArrayList<>();
    static List<RecruiterModel>hireModels = new ArrayList<>();
    int customstatus = 0, status = 0,requirement_jobStatus = 0,aboutjob_status = 0;
    @BindView(R.id.backarrow)
    ImageView backarrow;
    @BindView(R.id.customsublayout)
    LinearLayout customsublayout;
    @BindView(R.id.job_history_down)
    ImageView jobHistoryDown;
    @BindView(R.id.jobhistory_up)
    ImageView jobhistoryUp;
    @BindView(R.id.jobdescription_down)
    ImageView jobdescriptionDown;
    @BindView(R.id.jobdescription_up)
    ImageView jobdescriptionUp;
    @BindView(R.id.custom_down)
    ImageView customDown;
    @BindView(R.id.custom_up)
    ImageView customUp;
    ProgressDialog progressDialog;
    String token, userId;
    @BindView(R.id.txtcandidatecounts)
    TextView txtcandidatecounts;
    @BindView(R.id.txtjobtitile)
    TextView txtjobtitile;
    @BindView(R.id.txtlocation)
    TextView txtlocation;
    @BindView(R.id.txtdate)
    TextView txtdate;

    @BindView(R.id.txtjobtype)
    TextView txtjobtype;
    @BindView(R.id.txtsalery)
    TextView txtsalery;
    @BindView(R.id.txthmname)
    TextView txthmname;
    String Jobid;
    @BindView(R.id.txtexp)
    TextView txtexp;
    @BindView(R.id.edtflsa)
    EditText edtflsa;
    @BindView(R.id.edtreplacename)
    EditText edtreplacename;
    @BindView(R.id.edtbudged)
    EditText edtbudged;
    @BindView(R.id.edtnotes)
    EditText edtnotes;
    JobModel jobModel = new JobModel();
    @BindView(R.id.tag_cloud_view)
    TagCloudView tag_cloud_view;

    @BindView(R.id.required_experience_layout)
    LinearLayout required_experience_layout;
    @BindView(R.id.required_experience_down)
    ImageView required_experience_down;
    @BindView(R.id.required_experience_up)
    ImageView required_experience_up;
    @BindView(R.id.edit_required_experience)
    EditText edit_required_experience;
    @BindView(R.id.about_job_layout)
    LinearLayout about_job_layout;
    @BindView(R.id.about_job_down)
    ImageView about_job_down;
    @BindView(R.id.about_job_up)
    ImageView about_job_up;
    @BindView(R.id.edit_about_job)
    EditText edit_about_job;
    @BindView(R.id.job_recruit_layout)
    LinearLayout job_recruit_layout;
    Boolean tag_single = false;
    int apiCount = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        ButterKnife.bind(this);
        onlinestatus.setVisibility(View.GONE);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("JobModel");
                Gson gson = new Gson();
                jobModel = gson.fromJson(feed, JobModel.class);
            }
        }

        initlayout();

        showProgress();
        recruiterModels.clear();
        hireModels.clear();
        loadAssignManagers(0);
        loadAssignManagers(1);
    }
    void initlayout(){
        txtjobtitile.setText(jobModel.getJob_title());
        txtlocation.setText(jobModel.getLocation());
        txtdate.setText(Commons.dateTime(jobModel.getStatusdate()));
        txtcandidatecounts.setText(String.valueOf(jobModel.getCandidatecount()));
        txtexp.setText(jobModel.getDname());
        txtsalery.setText("$"+jobModel.getSalary());
        txtjobtype.setText(jobModel.getJobtype());
        List<String> tags = new ArrayList<>();
        if(jobModel.getJobkeyword() !=null)
            Collections.addAll(tags, jobModel.getJobkeyword().split(","));
        tag_cloud_view.setTags(tags);
        tag_cloud_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag_cloud_view.singleLine(tag_single);
                tag_single = !tag_single;
            }
        });
        editDescription.setText(Html.fromHtml(jobModel.getDescription()));
        editDescription.setKeyListener(null);
        edit_required_experience.setText(Html.fromHtml(jobModel.getRequiredExperience()));
        edit_required_experience.setKeyListener(null);
        edit_about_job.setText(Html.fromHtml(jobModel.getRequiredExperience()));
        edit_about_job.setKeyListener(null);


    }
    void loadAssignManagers(int type){
        String api_link = API.GET_RECRUITER;
        if(type == 1) api_link = API.GET_HMMANGERS;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            for(int i =0;i<jsonArray.length();i++){
                                RecruiterModel recruiterModel = new RecruiterModel();
                                recruiterModel.initModel(jsonArray.getJSONObject(i));
                                if(type == 0){
                                    recruiterModels.add(recruiterModel);
                                }else {
                                    hireModels.add(recruiterModel);
                                }
                            }
                            apiCount++;
                            if(apiCount == 2){
                                closeProgress();
                                for(int j =0;j<recruiterModels.size();j++){
                                    for(int i =0;i<jobModel.getRecruiterModels().size();i++){
                                            if(recruiterModels.get(j).getUid() == jobModel.getRecruiterModels().get(i).getUid()){
                                            recruiterModels.get(j).setSelected(true);
                                            break;
                                        }
                                    }
                                }
                                for(int j =0;j<hireModels.size();j++){
                                    for(int i =0;i<jobModel.getHm().size();i++){
                                        if(hireModels.get(j).getUid() == jobModel.getHm().get(i).getUid()){
                                            hireModels.get(j).setSelected(true);
                                            break;
                                        }
                                    }
                                }

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

    @OnClick({R.id.candidate_layout, R.id.custom_layout, R.id.jobhistory_layout, R.id.backarrow, R.id.jobdescription_layout, R.id.clone, R.id.deactivate, R.id.close,R.id.delete, R.id.glassdorr, R.id.linkedin, R.id.ziprecuriter, R.id.imv_edit, R.id.save_btn, R.id.usericon,
            R.id.starunfilled, R.id.star,R.id.required_experience_layout,R.id.about_job_layout,R.id.job_recruit_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_btn:
                savedJob();
                break;

            case R.id.ziprecuriter:
                ConfirmDialog confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(JobDetailActivity.this, "Job Posted over Zip Recuriter", Toast.LENGTH_SHORT).show();
                    }
                },getString(R.string.post_ziprecruiter),"Deactivate","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;

            case R.id.candidate_layout:

                break;
            case R.id.job_recruit_layout:
                Bundle bundle = new Bundle();
                bundle.putString("JobID",String.valueOf(jobModel.getJobID()));
                goTo(this,JobHiringStatusActivity.class,false,bundle);
                break;


            case R.id.custom_layout:

                if (customstatus == 0) {
                    customUp.setVisibility(View.VISIBLE);
                    customDown.setVisibility(View.GONE);
                    customsublayout.setVisibility(View.VISIBLE);
                    customstatus = 1;
                } else {

                    customUp.setVisibility(View.GONE);
                    customDown.setVisibility(View.VISIBLE);
                    customsublayout.setVisibility(View.GONE);
                    customstatus = 0;
                }
                break;


            case R.id.jobhistory_layout:
                //Toast.makeText(jobdetailActivity.this, "No Jobs found", Toast.LENGTH_SHORT).show();
                break;


            case R.id.backarrow:
                onBackPressed();
                break;
            case R.id.jobdescription_layout:
                if (status == 0) {
                    jobdescriptionDown.setVisibility(View.VISIBLE);
                    jobdescriptionUp.setVisibility(View.GONE);
                    editDescription.setVisibility(View.VISIBLE);
                    status = 1;
                } else {
                    editDescription.setVisibility(View.GONE);
                    status = 0;
                    jobdescriptionDown.setVisibility(View.GONE);
                    jobdescriptionUp.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.required_experience_layout:
                if (requirement_jobStatus == 0) {
                    required_experience_up.setVisibility(View.VISIBLE);
                    required_experience_down.setVisibility(View.GONE);
                    edit_required_experience.setVisibility(View.VISIBLE);
                    requirement_jobStatus = 1;
                } else {

                    required_experience_up.setVisibility(View.GONE);
                    required_experience_down.setVisibility(View.VISIBLE);
                    edit_required_experience.setVisibility(View.GONE);
                    requirement_jobStatus = 0;
                }
                break;
            case R.id.about_job_layout:
                if (aboutjob_status == 0) {
                    about_job_up.setVisibility(View.VISIBLE);
                    about_job_down.setVisibility(View.GONE);
                    edit_about_job.setVisibility(View.VISIBLE);
                    aboutjob_status = 1;
                } else {
                    about_job_up.setVisibility(View.GONE);
                    about_job_down.setVisibility(View.VISIBLE);
                    edit_about_job.setVisibility(View.GONE);
                    aboutjob_status = 0;
                }
                break;
            case R.id.linkedin:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(JobDetailActivity.this, "Job Posted over Indeed", Toast.LENGTH_SHORT).show();
                    }
                },getString(R.string.post_indeed),"Deactivate","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;
            case R.id.glassdorr:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(JobDetailActivity.this, "Job Posted over Recruiter", Toast.LENGTH_SHORT).show();
                    }
                },getString(R.string.post_recruiter),"Deactivate","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;


            case R.id.clone:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        controlJob(88);
                    }
                },getString(R.string.clone_job),"Clone","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;

            case R.id.deactivate:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        controlJob(0);
                    }
                },getString(R.string.deactivate_job),"Deactivate","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");

                break;
            case R.id.close:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        controlJob(8);
                    }
                },getString(R.string.close_job),"Close","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;
            case R.id.delete:
                confirmDialog = new ConfirmDialog();
                confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        controlJob(9);
                    }
                },getString(R.string.remove_job),"Remove","Cancel");
                confirmDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;


            case R.id.star:
                star.setVisibility(View.GONE);
                starunfilled.setVisibility(View.VISIBLE);
                break;

            case R.id.starunfilled:
                star.setVisibility(View.VISIBLE);
                starunfilled.setVisibility(View.GONE);
                break;
            case R.id.imv_edit:
                JobEditDialog jobEditDialog = new JobEditDialog();

                jobEditDialog.setOnConfirmListener(new JobEditDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(String custom_text, String location, int salary, String description) {

                    }
                });
                jobEditDialog.show(this.getSupportFragmentManager(), "DeleteMessage");

                break;
            case R.id.usericon:
                JobHiringDialog jobHiringDialog = new JobHiringDialog();
                jobHiringDialog.setOnConfirmListener(new JobHiringDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int type) {
                        JobHiringDetailDialog jobHiringDetailDialog = new JobHiringDetailDialog();
                        jobHiringDetailDialog.setOnConfirmListener(new JobHiringDetailDialog.OnConfirmListener() {
                            @Override
                            public void onConfirm(List<RecruiterModel> selectModels) {
                                putManagers(type,selectModels);
                            }
                        },type,recruiterModels,hireModels);
                        jobHiringDetailDialog.show(getSupportFragmentManager(), "DeleteMessage");
                    }
                });
                jobHiringDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;


        }
    }

    void putManagers(int type,List<RecruiterModel> selectModels){
        String api_link = "";
        if(type ==1)
            api_link = API.PUT_RECRUITERGMANGER;
        else if(type == 0)
            api_link = API.PUT_HIRINGMANGER;
        api_link = api_link + String.valueOf(jobModel.getJobID());
        showProgress();
        try {
            JSONArray jsonArray =  new JSONArray();
            for(int i =0;i<selectModels.size();i++){
                JSONObject jsonObject = new JSONObject();
                if(selectModels.get(i).isSelected()) {
                    if (type == 0)
                        jsonObject.put("recruiter_id", String.valueOf(selectModels.get(i).getUid()));
                    else
                        jsonObject.put("uid", String.valueOf(selectModels.get(i).getUid()));
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
                    if(error.toString().contains("Update Success!")){
                        if(type==0){
                            hireModels = selectModels;
                        }else if(type ==1){
                            recruiterModels = selectModels;
                        }
                        showToast("Update Success!");
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

    void controlJob(int controlType){
        showProgress();
        String apilink = API.PUT_CONTROLJOB + String.valueOf(jobModel.getJobID());

        JSONObject params = new JSONObject();
        try {

            params.put("action",String.valueOf(controlType));

        } catch (JSONException e) {

        }
        new BaseJsonObjectRequest(
                Request.Method.PUT, apilink, params,
                response -> {
                    closeProgress();
                    Log.d("aaaaaa", response.toString());
                    if(controlType ==88)
                        Toast.makeText(JobDetailActivity.this, "The Job have been cloned", Toast.LENGTH_SHORT).show();
                    else if(controlType ==0)
                        Toast.makeText(JobDetailActivity.this, "The Job have been deactivated", Toast.LENGTH_SHORT).show();
                    else if(controlType==8){
                        Toast.makeText(JobDetailActivity.this, "Job have been closed", Toast.LENGTH_SHORT).show();

                    }else if(controlType ==9){
                        Toast.makeText(JobDetailActivity.this, "Job have been removed", Toast.LENGTH_SHORT).show();
                        Intent resultInt = new Intent();
                        resultInt.putExtra("mainactivitystatus", 1);
                        setResult(RESULT_OK,resultInt);
                        finish();
                    }
                }, this::handleMultiPartResponseError);
    }
    void savedJob(){
        showProgress();
        String apilink = API.PUT_JOBSAVE + "/add";

        JSONObject params = new JSONObject();
        try {

            params.put("jid",String.valueOf(jobModel.getJobID()));

        } catch (JSONException e) {

        }
        new BaseJsonObjectRequest(
                Request.Method.PUT, apilink, params,
                response -> {
                    closeProgress();
                    Log.d("aaaaaa", response.toString());
                    Toast.makeText(this, "Job Saved Successfully", Toast.LENGTH_SHORT).show();
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
}