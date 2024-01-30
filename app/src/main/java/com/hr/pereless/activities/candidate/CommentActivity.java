package com.hr.pereless.activities.candidate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hr.pereless.R;
import com.hr.pereless.adapter.candidate.CommentHistoryAdapter;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Helper;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.candidate.CommunicationModel;
import com.hr.pereless.model.candidate.QuestionModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.job.RecruiterModel;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zcw.togglebutton.ToggleButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends CommonActivity {
    CandidateModel candidateModel = new CandidateModel();
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.txv_history)
    TextView txv_history;
    @BindView(R.id.list_history)
    ListView list_history;
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
    @BindView(R.id.save_btn)
    Button save_btn;
    int count =0 ;
    int ranking = 4;
    boolean comment_history = false;
    ArrayList<CommunicationModel>communicationModels = new ArrayList<>();
    CommentHistoryAdapter commentHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            if (bundle != null) {
                String feed= bundle.getString("CandidateModel");
                Gson gson = new Gson();
                candidateModel = gson.fromJson(feed, CandidateModel.class);
            }
        }
        commentHistoryAdapter = new CommentHistoryAdapter(this);
        list_history.setAdapter(commentHistoryAdapter);
        showProgress();
        initLayout();
        loadData(0);
    }

    void initLayout(){
        ArrayList<String>jobTitle = new ArrayList<>();
        for(int i =0;i<Commons.appliedJobModelList.size();i++)
            jobTitle.add(Commons.appliedJobModelList.get(i).getJob_title());
        spinner_jobselection.setItems(jobTitle);

        ArrayList<String>type = new ArrayList<>();
        for(int i =0;i<Commons.recruitflowModels.size();i++)
            type.add(Commons.recruitflowModels.get(i).getRecruitFlowName());
        spinner_type.setItems(type);

        commentHistoryAdapter.setRoomData(communicationModels);
        Helper.getListViewSize(list_history);

    }

    void loadData(int type){
        String api_link = API.GET_COMMENT + String.valueOf(candidateModel.getRid()) ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        count++;
                        closeProgress();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            if(type ==0 ) {
                                communicationModels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    CommunicationModel communicationModel = new CommunicationModel();
                                    communicationModel.initModel(jsonObject);
                                    communicationModels.add(communicationModel);
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

    void submitComment(){

    }
    @OnClick({R.id.btn_back, R.id.save_btn,R.id.txv_date,R.id.txv_minus,R.id.txv_plus,R.id.txv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                //finish();
                onBackPressed();
                break;

            case R.id.save_btn:
                submitComment();
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
            case R.id.txv_history:
                comment_history =  !comment_history;
                if(comment_history)
                    list_history.setVisibility(View.VISIBLE);
                else
                    list_history.setVisibility(View.GONE);
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