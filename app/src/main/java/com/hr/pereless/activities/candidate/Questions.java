package com.hr.pereless.activities.candidate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.activities.job.JobDetailActivity;
import com.hr.pereless.adapter.candidate.QuestionUpdateAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Helper;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.CandidateUserModel;
import com.hr.pereless.model.candidate.QuestionModel;
import com.hr.pereless.model.candidate.QuicknoteModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

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

public class Questions extends CommonActivity {

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.backlayout)
    RelativeLayout backlayout;
    @BindView(R.id.profileimage)
    CircleImageView profileimage;
    @BindView(R.id.layout_tool)
    RelativeLayout layoutTool;
    @BindView(R.id.spinner_jobselection)
    MaterialSpinner spinner_jobselection;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.list_questions)
    ListView list_questions;
    CandidateModel candidateModel = new CandidateModel();
    ArrayList<String>jobtitles = new ArrayList<>();
    ArrayList<QuestionModel>questionModels = new ArrayList<>();
    ArrayList<QuestionModel>selectModels = new ArrayList<>();
    QuestionUpdateAdapter questionUpdateAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }
        for(int i =0;i< Commons.appliedJobModelList.size();i++){
            jobtitles.add(Commons.appliedJobModelList.get(i).getJob_title());
        }
        spinner_jobselection.setItems(jobtitles);
        spinner_jobselection.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                loadQuestions(position);
            }
        });
        questionUpdateAdapter = new QuestionUpdateAdapter(this);
        list_questions.setAdapter(questionUpdateAdapter);
        loadQuestions(0);
    }

    void initLayout(){
        questionUpdateAdapter.setRoomData(questionModels);
        selectModels = questionModels;
    }

    void loadQuestions(int poosstion){
        showProgress();
        String api_link = API.GET_CANDIDATE_QUESTIONS + String.valueOf(candidateModel.getRid()) + "/" + String.valueOf(Commons.appliedJobModelList.get(poosstion).getJid());
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            questionModels.clear();
                            for(int i =0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                QuestionModel questionModel = new QuestionModel();
                                questionModel.initModel(jsonObject);
                                questionModels.add(questionModel);
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


    void submitQuestion(){
        showProgress();
        String api_link = API.PUT_CANDIDATE_QUESTIONS;
        try {
            JSONArray jsonArray =  new JSONArray();
            for(int i =0;i<selectModels.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("answer",selectModels.get(i).getAnswer());
                jsonObject.put("AnsUUID",selectModels.get(i).getAnsUUID());
                jsonObject.put("jid",selectModels.get(i).getJID());
                jsonObject.put("rid",selectModels.get(i).getRID());
                jsonArray.put(jsonObject);
            }
            Log.d("aaaaa",jsonArray.toString());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, api_link, jsonArray, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    closeProgress();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    closeProgress();
                    if(error.toString().contains("Update Success!")){
                        showToast("Question submitted!");
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
    @OnClick({R.id.btn_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                //finish();
                onBackPressed();
                break;

            case R.id.btn_save:
                submitQuestion();
                break;
        }
    }
}