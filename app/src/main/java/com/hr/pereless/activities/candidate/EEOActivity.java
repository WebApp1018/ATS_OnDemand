package com.hr.pereless.activities.candidate;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.JobUpdateAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Helper;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.CandidateUserModel;
import com.hr.pereless.model.candidate.eeo.EeoDisposstionModel;
import com.hr.pereless.model.candidate.eeo.EthnicityModel;
import com.hr.pereless.model.candidate.eeo.JobdisposstionModel;
import com.hr.pereless.model.job.RecruiterModel;
import com.hr.pereless.util.RoundedCornersTransformation;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EEOActivity extends CommonActivity {

    List<RecruiterModel> managerModels = new ArrayList<>();
    @BindView(R.id.img_back)
    Button imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.logout)
    ImageView logout;
    @BindView(R.id.tick)
    ImageView tick;
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
    @BindView(R.id.save_btn)
    Button save_btn;
    @BindView(R.id.profileimage)
    ImageView profileimage;
    @BindView(R.id.txv_name)
    TextView txv_name;
    @BindView(R.id.txv_type)
    TextView txv_type;
    CandidateModel candidateModel= new CandidateModel();
    JobUpdateAdapter jobUpdateAdapter;
    ArrayList<EeoDisposstionModel>eeoDisposstionModels = new ArrayList<>();
    ArrayList<String >disposstionTitle = new ArrayList<>();

    ArrayList<String>eeovet = new ArrayList<>();
    ArrayList<String>eeosources = new ArrayList<>();
    ArrayList<EthnicityModel>ethnicityModels = new ArrayList<>();
    ArrayList<String >ethnicityTitle = new ArrayList<>();
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeoactivity);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }
        jobUpdateAdapter = new JobUpdateAdapter(this);
        list_jobs.setAdapter(jobUpdateAdapter);
        count =0;
        showProgress();
        for(int i =0;i<4;i++)
            loadData(i);
        initLayout();
    }
    void initLayout(){
        txtTitle.setText("EEO");
        spinner_ethancity.setItems(ethnicityTitle);
        spinner_vetaran.setItems(eeovet);
        spinner_source.setItems(eeosources);
        jobUpdateAdapter.setRoomData(Commons.appliedJobModelList,eeoDisposstionModels,disposstionTitle);
        Helper.getListViewSize(list_jobs);

        Glide.with(_context).load(candidateModel.getCandidateUserModel().getImage()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, "#FFFECC53", Commons.glide_boder))).into(profileimage);
        txv_name.setText(candidateModel.getCandidateUserModel().getName());
        txv_type.setText(candidateModel.getCandidateUserModel().getDesignation());
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
    @OnClick({R.id.img_back, R.id.btn_male, R.id.btn_female, R.id.btn_dis_yes, R.id.btn_dis_no, R.id.btn_ver_yes, R.id.btn_ver_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
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
        }
    }

    private void data() {


    }
}