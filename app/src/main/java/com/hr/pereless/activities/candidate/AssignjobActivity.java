package com.hr.pereless.activities.candidate;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.JobUpdateAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Helper;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.QuicknoteModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.candidate.SkillLabelModel;
import com.hr.pereless.model.candidate.eeo.EeoDisposstionModel;
import com.hr.pereless.model.candidate.eeo.EthnicityModel;
import com.hr.pereless.model.job.JobModel;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zcw.togglebutton.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignjobActivity extends CommonActivity {
    @BindView(R.id.lyt_job)
    LinearLayout lyt_job;
    @BindView(R.id.spinnerstage)
    MaterialSpinner spinnerstage;
    @BindView(R.id.spinnerjob_type)
    MaterialSpinner spinnerjob_type;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.logout)
    ImageView logout;
    @BindView(R.id.tick)
    ImageView tick;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_preview)
    Button btn_preview;
    @BindView(R.id.txv_job)
    TextView txv_job;
    @BindView(R.id.lyt_page1)
    LinearLayout lyt_page1;
    @BindView(R.id.lyt_page2)
    NestedScrollView lyt_page2;
    @BindView(R.id.lyt_page3)
    NestedScrollView lyt_page3;
    @BindView(R.id.lyt_page4)
    NestedScrollView lyt_page4;

    @BindView(R.id.btn_male)
    Button btnMale;
    @BindView(R.id.btn_female)
    Button btnFemale;
    @BindView(R.id.btn_dis_yes)
    Button btnDisYes;
    @BindView(R.id.btn_dis_no)
    Button btnDisNo;
    @BindView(R.id.btn_ver_yes)
    Button btnVerYes;
    @BindView(R.id.btn_ver_no)
    Button btnVerNo;
    @BindView(R.id.spinner_ethancity)
    MaterialSpinner spinner_ethancity;
    @BindView(R.id.spinner_vetaran)
    MaterialSpinner spinner_vetaran;
    @BindView(R.id.spinner_source)
    MaterialSpinner spinner_source;
    @BindView(R.id.list_jobs)
    ListView list_jobs;
    @BindView(R.id.spinner_jobselection)
    MaterialSpinner spinner_jobselection;
    @BindView(R.id.spinner_type)
    MaterialSpinner spinner_type;
    @BindView(R.id.txv_date)
    TextView txv_date;
    @BindView(R.id.edt_message)
    EditText edt_message;
    @BindView(R.id.toogle_private)
    ToggleButton toogle_private;
    @BindView(R.id.txv_minus)
    TextView txv_minus;
    @BindView(R.id.txt_ranking)
    TextView txt_ranking;
    @BindView(R.id.txv_plus)
    TextView txv_plus;
    @BindView(R.id.spinner_skill)
    MaterialSpinner spinner_skill;
    @BindView(R.id.notes_box)
    EditText notes_box;
    @BindView(R.id.edit_score)
    EditText edit_score;
    @BindView(R.id.spinner_color)
    MaterialSpinner spinner_color;

    int ranking = 4;
    CandidateModel candidateModel = new CandidateModel();
    List<JobModel> jobModels = new ArrayList<>();
    int page = 1,job_type =1;
    public boolean isLastPage = false;
    int selected_job = -1,select_stage = 0;
    int count = 0,page_count=0,total_count =0,skill_count =0;
    ArrayList<EeoDisposstionModel>eeoDisposstionModels = new ArrayList<>();
    ArrayList<String >disposstionTitle = new ArrayList<>();
    JobUpdateAdapter jobUpdateAdapter;
    ArrayList<String>eeovet = new ArrayList<>();
    ArrayList<String>eeosources = new ArrayList<>();
    ArrayList<EthnicityModel>ethnicityModels = new ArrayList<>();
    ArrayList<String >ethnicityTitle = new ArrayList<>();
    RecruitflowModel recruitflowModel = new RecruitflowModel();
    List<SkillLabelModel>skillLabelModels = new ArrayList<>();
    List<String>colormodels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignjob);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }
        txtTitle.setText("Assign Job");
        spinnerjob_type.setItems(getResources().getString(R.string.active),getResources().getString(R.string.inactive),getResources().getString(R.string.closed));
        jobUpdateAdapter = new JobUpdateAdapter(this);
        list_jobs.setAdapter(jobUpdateAdapter);

        loadData(0,job_type);
        spinnerjob_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                page =1;
                jobModels.clear();
                selected_job = -1;
                txv_job.setText("");
                if(position==0)
                    job_type = 1;
                else if(position ==1)
                    job_type = 0;
                else if(position==2)
                    job_type = 8;
                loadData(0,job_type);
            }
        });
        verifyStage();
        spinnerstage.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                select_stage = position;
                page_count = 0;
                verifyStage();
            }
        });
    }
    void initLayout(){
        ArrayList<String>type = new ArrayList<>();
        for(int i =0;i<Commons.recruitflowModels.size();i++)
            type.add(Commons.recruitflowModels.get(i).getRecruitFlowName());
        spinnerstage.setItems(type);
        ArrayList<String>jobTitle = new ArrayList<>();
        for(int i =0;i<Commons.appliedJobModelList.size();i++)
            jobTitle.add(Commons.appliedJobModelList.get(i).getJob_title());
        spinner_jobselection.setItems(jobTitle);
        for(int i =0;i<Commons.recruitflowModels.size();i++)
            type.add(Commons.recruitflowModels.get(i).getRecruitFlowName());
        spinner_type.setItems(type);
        initsecondLayout();
        iniThirdLayout();
    }

    void iniThirdLayout(){
        List<String> skill_label = new ArrayList<>();
        for(int i =0;i<skillLabelModels.size();i++){
            skill_label.add(skillLabelModels.get(i).getHotbookName());
        }
        spinner_skill.setItems(skill_label);
        spinner_color.setItems(colormodels);
    }

    void initsecondLayout(){
        spinner_ethancity.setItems(ethnicityTitle);
        spinner_vetaran.setItems(eeovet);
        spinner_source.setItems(eeosources);
        jobUpdateAdapter.setRoomData(Commons.appliedJobModelList,eeoDisposstionModels,disposstionTitle);
        Helper.getListViewSize(list_jobs);
    }
    void verifyStage(){
        recruitflowModel = Commons.recruitflowModels.get(select_stage);
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
        total_count = page_count;
        if(page_count >0){
            btnSend.setText(getResources().getString(R.string.next));
        }
        else {
            btnSend.setText(getResources().getString(R.string.assign));
        }
    }

    public void loadNextPage(){
        page++;
        loadData(0,job_type);
    }
    void loadData(int type,int job_type){
        String api_link = API.GET_ASSIGN_JOBS + "?status=" + String.valueOf(job_type) + "&recordsPerPage=30" + "&page=" + String.valueOf(page);
        showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            if(type ==0 ) {
                                JSONObject jsonObject = new JSONObject(json);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                if(jsonObject.getJSONObject("pagination").getInt("totalPages") >= page-1){
                                    isLastPage = true;
                                }
                                for(int i =0;i<jsonArray.length();i++){
                                    JobModel jobModel = new JobModel();
                                    jobModel.initModel(jsonArray.getJSONObject(i));
                                    jobModels.add(jobModel);
                                }
                            }
                            initLayout();
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

    void loadSkill(int type){
        String api_link  =API.GET_SKILLLABEL;
        if(type == 2)
            api_link= API.GET_COLOR;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        skill_count++;
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            if(type ==1 ){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    SkillLabelModel timeLineModel = new SkillLabelModel();
                                    timeLineModel.initModel(jsonArray.getJSONObject(i));
                                    skillLabelModels.add(timeLineModel);
                                }
                            }else if(type ==2) {
                                colormodels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    colormodels.add((String) jsonArray.get(i));
                                }
                            }
                            if(skill_count ==2) {
                                closeProgress();
                                iniThirdLayout();
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

    void loadData(int type){
        String api_link = API.GET_EEODISPOSIION;
        if(type ==1)
            api_link = API.GET_EEOVET;
        else if(type ==2)
            api_link = API.GET_EEOSOURCE;
        else if(type ==3)
            api_link = API.GET_ETHNICITY;

        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        count++;
                        try {
                            JSONArray jsonArray = new JSONArray(json);

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if(type ==0) {
                                    EeoDisposstionModel eeoDisposstionModel= new EeoDisposstionModel();
                                    eeoDisposstionModel.initModel(jsonObject);
                                    disposstionTitle.add(eeoDisposstionModel.getProfileText());
                                    eeoDisposstionModels.add(eeoDisposstionModel);
                                }else if(type ==1){
                                    eeovet.add(jsonObject.getString("VETDESCRIPT"));
                                }else if(type==2){
                                    eeosources.add(jsonObject.getString("SOURCE"));
                                }else if(type ==3){
                                    EthnicityModel ethnicityModel = new EthnicityModel();
                                    ethnicityModel.initModel(jsonObject);
                                    ethnicityTitle.add(ethnicityModel.getEthnicity());
                                    ethnicityModels.add(ethnicityModel);
                                }
                            }

                            if(count == 4){
                                closeProgress();
                                initsecondLayout();
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
    void assignJob(){
        showProgress();
        String api_link = API.POST_ASSIGN_JOBS ;
        JSONObject params = new JSONObject();
        try {
            params.put("flownum",select_stage+1);
            params.put("jid",jobModels.get(selected_job).getJid());
            params.put("rid",candidateModel.getRid());
            params.put("fkjobowner",jobModels.get(selected_job).getJobownerID());
            params.put("abpostoffice",1); // select letter

        } catch (JSONException e) {

        }
        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.POST, api_link, params,
                response -> {
                    closeProgress();
                    Toast.makeText(this, "Job Assigned", Toast.LENGTH_SHORT).show();
                    finish(this);
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @OnClick({R.id.img_back, R.id.btn_send,R.id.lyt_job,R.id.btn_preview,R.id.btn_male, R.id.btn_female, R.id.btn_dis_yes, R.id.btn_dis_no, R.id.btn_ver_yes, R.id.btn_ver_no,
            R.id.txv_date,R.id.txv_minus,R.id.txv_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.img_back:

                onBackPressed();
                break;
            case R.id.btn_send:
                if(selected_job==-1) {
                    showAlertDialog("Please select job");
                    return;
                }
                if(page_count>0){
                    btnSend.setText(getResources().getString(R.string.next));
                    btn_preview.setVisibility(View.VISIBLE);
                    lyt_page1.setVisibility(View.GONE);
                    lyt_page2.setVisibility(View.GONE);
                    lyt_page3.setVisibility(View.GONE);
                    lyt_page4.setVisibility(View.GONE);

                    if(recruitflowModel.isRFEEO() && page_count - 9>=0) {
                        lyt_page2.setVisibility(View.VISIBLE);
                        if (count < 4) {
                            showProgress();
                            for (int i = 0; i < 4; i++)
                                loadData(i);
                        }
                        page_count -=9;
                    }else if( ( recruitflowModel.isRFHMD() || recruitflowModel.isRFcommentlog()||
                            recruitflowModel.isRFDocument() ) && page_count-5>=0){
                        lyt_page3.setVisibility(View.VISIBLE);
                        page_count -=5;
                    }else if(recruitflowModel.isRFQuickNote() && page_count-3>=0 ){
                        lyt_page4.setVisibility(View.VISIBLE);
                        page_count-=3;
                        if(skill_count<2) {
                            showProgress();
                            loadSkill(1);
                            loadSkill(2);
                        }
                    }

                    if(page_count == 0){
                        btnSend.setText(getResources().getString(R.string.assign));
                    }
                }else {

                    assignJob();
                }
                break;
            case R.id.btn_preview:
                btnSend.setText(getResources().getString(R.string.next));
                btn_preview.setVisibility(View.GONE);
                btn_preview.setVisibility(View.GONE);
                lyt_page1.setVisibility(View.VISIBLE);
                lyt_page2.setVisibility(View.GONE);
                lyt_page3.setVisibility(View.GONE);
                lyt_page4.setVisibility(View.GONE);
                page_count = total_count;
                break;
            case R.id.lyt_job:
                ArrayList<String>arrayList = new ArrayList<>();
                for(int i =0;i<jobModels.size();i++) {
                    arrayList.add(jobModels.get(i).getJob_title());
                }
                SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
                selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        txv_job.setText(jobModels.get(selectPosstion).getJob_title());
                        selected_job = selectPosstion;
                    }
                },arrayList,1);
                selectJobOneDialog.show(this.getSupportFragmentManager(), "DeleteMessage");
                break;
            case R.id.btn_male:
                btnMale.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_left));
                btnFemale.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_right));
                btnMale.setTextColor(getResources().getColor(R.color.colorWhite));
                btnFemale.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.btn_female:
                btnMale.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_left));
                btnFemale.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_right));
                btnMale.setTextColor(getResources().getColor(R.color.black));
                btnFemale.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.btn_dis_yes:
                btnDisYes.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_left));
                btnDisNo.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_right));
                btnDisYes.setTextColor(getResources().getColor(R.color.colorWhite));
                btnDisNo.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.btn_dis_no:
                btnDisYes.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_left));
                btnDisNo.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_right));
                btnDisYes.setTextColor(getResources().getColor(R.color.black));
                btnDisNo.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.btn_ver_yes:
                btnVerYes.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_left));
                btnVerNo.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_right));
                btnVerYes.setTextColor(getResources().getColor(R.color.colorWhite));
                btnVerNo.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.btn_ver_no:
                btnVerYes.setBackground(getResources().getDrawable(R.drawable.bg_unselect_gray_left));
                btnVerNo.setBackground(getResources().getDrawable(R.drawable.bg_select_blue_right));
                btnVerYes.setTextColor(getResources().getColor(R.color.black));
                btnVerNo.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.txv_date:
                SelectDay();
                break;
            case R.id.txv_minus:
                if(ranking ==0) break;
                ranking--;
                txt_ranking.setText(String.valueOf(ranking));
                break;
            case R.id.txv_plus:
                if(ranking==30)break;
                ranking++;
                txt_ranking.setText(String.valueOf(ranking));
                break;
        }
    }
    void SelectDay(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker , int year , int month , int day) {
                month = month + 1;
                Log.d( "onDateSet" , month + "/" + day + "/" + year );
                txv_date.setText(String.valueOf(day)+ " " + Commons.Months[month-1]+ " " + year);
                cal.set(year,month,day);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            }
        };
        DatePickerDialog dialog =  new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateListener,
                year,month,day
        );
        dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
        dialog.show();


    }
}