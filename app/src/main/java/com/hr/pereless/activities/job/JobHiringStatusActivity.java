package com.hr.pereless.activities.job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.job.JobFlowModel;
import com.hr.pereless.model.job.JobModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobHiringStatusActivity extends CommonActivity {

    @BindView(R.id.backarrow)
    ImageView backarrow;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.listview)
    ListView listview;
    JobHiringStatusAdapter jobHiringStatusAdapter;
    ArrayList<JobFlowModel>jobFlowModels = new ArrayList<>();
    String jobid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_hiring_status);
        ButterKnife.bind(this);
        titleTxt.setText("Job Recruit Flow");
        jobHiringStatusAdapter = new JobHiringStatusAdapter(this);
        listview.setAdapter(jobHiringStatusAdapter);
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                jobid =  bundle.getString("JobID");

            }
        }
        loadFlow();
    }
    @OnClick({R.id.backarrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backarrow:
                onBackPressed();
                break;

        }
    }

    void loadFlow(){
        showProgress();
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                API.GET_JOB_RECRUITER_FLOW + jobid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            jobFlowModels.clear();
                            for(int i =0;i<jsonArray.length();i++){
                                JobFlowModel jobFlowModel = new JobFlowModel();
                                jobFlowModel.initModel(jsonArray.getJSONObject(i));
                                jobFlowModels.add(jobFlowModel);
                            }
                            jobHiringStatusAdapter.setRoomData(jobFlowModels);
                        }catch (Exception e){

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
}