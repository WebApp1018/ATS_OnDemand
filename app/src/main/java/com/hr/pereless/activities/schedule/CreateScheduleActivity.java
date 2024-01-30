package com.hr.pereless.activities.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.hr.pereless.R;
import com.hr.pereless.activities.MainActivity;
import com.hr.pereless.activities.auth.WelcomeActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.application.AppController;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.CandidateModel;
import com.hr.pereless.model.onboarding.OnboardingModel;
import com.hr.pereless.preference.PrefConst;
import com.hr.pereless.preference.Preference;
import com.htmleditor.HtmlTextEditor;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateScheduleActivity extends CommonActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.spinner_candidate)
    MaterialSpinner spinner_candidate;
    @BindView(R.id.spinner_job)
    MaterialSpinner spinner_job;
    @BindView(R.id.spinner_type)
    MaterialSpinner spinner_type;
    @BindView(R.id.edt_subject)
    EditText edt_subject;
    @BindView(R.id.edt_location)
    EditText edt_location;
    @BindView(R.id.spinner_timezone)
    MaterialSpinner spinner_timezone;
    @BindView(R.id.edt_date)
    TextView edt_date;
    @BindView(R.id.edt_starttime)
    TextView edt_starttime;
    @BindView(R.id.edt_endtime)
    TextView edt_endtime;
    @BindView(R.id.checkbox_allday)
    CheckBox checkbox_allday;
    @BindView(R.id.html_editor)
    HtmlTextEditor html_editor;
    @BindView(R.id.add_schedule)
    TextView add_schedule;

    List<CandidateModel> candidateslist = new ArrayList<>();
    List<String> candidateNames = new ArrayList<>();

    List<AppliedJobModel> appliedJobModelList = new ArrayList<>();
    List<String> timezone = new ArrayList<>();
    int timeType = 0;
    String setDate = "";
    List<String> types = new ArrayList<String>(){
        {
            add("Call Back");
            add("Interview");
            add("Onsite Interview(with Location Map");
            add("Phone Interview");
            add("Prescreening");
            add("To-Do");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        ButterKnife.bind(this);
        txtTitle.setText("Create Schedule");
        imgBack.setOnClickListener(this);
        add_schedule.setOnClickListener(this);
        edt_date.setOnClickListener(this);
        edt_starttime.setOnClickListener(this);
        edt_endtime.setOnClickListener(this);
        loadApi(API.GET_ONBOARDING + "/0?days=-5000&canview=AA");

        for (int i = 0; i < Commons.timeZoneModels.size(); i++) {
            timezone.add(Commons.timeZoneModels.get(i).getName());
        }
        spinner_timezone.setItems(timezone);

        spinner_candidate.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                loadApi(API.GET_JOBAPPLIED + String.valueOf(candidateslist.get(position).getRid()));
            }
        });
        spinner_type.setItems(types);
        spinner_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                edt_subject.setText(spinner_type.getText()  + " with " + spinner_candidate.getText() );
            }
        });
    }


    void loadApi(String api_link) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection(api_link, params, Request.Method.GET);
    }

    @Override
    public void parseResponse(String apilink, String json) {
        closeProgress();
        try {
            if (apilink.contains(API.GET_ONBOARDING)) {
                JSONArray jsonArray = new JSONArray(json);
                candidateNames.clear();
                candidateslist.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    CandidateModel candidateModel = new CandidateModel();
                    candidateModel.initModel(jsonArray.getJSONObject(i));
                    candidateslist.add(candidateModel);
                    candidateNames.add(candidateModel.getCanname());
                }
                spinner_candidate.setItems(candidateNames);
            } else if (apilink.contains(API.GET_JOBAPPLIED)) {
                JSONArray jsonArray = new JSONArray(json);
                appliedJobModelList.clear();
                List<String> jobs = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AppliedJobModel appliedJobModel = new AppliedJobModel();
                    appliedJobModel.initModel(jsonObject);
                    appliedJobModelList.add(appliedJobModel);
                    jobs.add(appliedJobModel.getJob_title());
                }
                spinner_job.setItems(jobs);
            }

        } catch (Exception e) {

        }
    }

    void setDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CreateScheduleActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
// If you're calling this from a support Fragment
        dpd.setAccentColor("#1461b9");
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.show(getSupportFragmentManager(), "Datepickerdialog");



    }

    void setTimePicker() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                CreateScheduleActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setAccentColor("#1461b9");
        // If you're calling this from a support Fragment
        tpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
        edt_date.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = hourOfDay +":" + minute + " AM";
        if(hourOfDay>=12) time = hourOfDay-12 +":" + minute + " PM";
        if(timeType ==0 )edt_starttime.setText(time);
        else edt_endtime.setText(time);
    }

    void addSchedule(){
        String comment = html_editor.getText();
        if(spinner_candidate.getText().length()  == 0){
            showAlertDialog("Please select candidate");
            return;
        }else if(spinner_job.getText().length()  == 0){
            showAlertDialog("Please select job");
            return;
        }else if(spinner_type.getText().length()  == 0){
            showAlertDialog("Please select candidate");
            return;
        }else if(edt_date.getText().length()  == 0){
            showAlertDialog("Please select date");
            return;
        }else if(!checkbox_allday.isChecked()){
            if(edt_starttime.getText().length()  == 0){
                showAlertDialog("Please select start time");
                return;
            }else if(edt_endtime.getText().length()  == 0){
                showAlertDialog("Please select end time");
                return;
            }
        }

        showProgress();
        String api_link = API.GET_SCHEDULES;
        StringRequest myRequest = new StringRequest(
                Request.Method.POST,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        Log.d("response", json);
                        closeProgress();
                        try {
                            JSONObject response = new JSONObject(json);
                            showAlertDialog(response.getString("message"));
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
            public byte[] getBody() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    /* fill your json here */
                    jsonObject.put("aFlag",1);
                    jsonObject.put("allday",checkbox_allday.isChecked()  ? "yes" : "No");
                    jsonObject.put("comments",comment);
                    jsonObject.put("contact","contact");
                    jsonObject.put("endtime",edt_date.getText().toString());
                    jsonObject.put("jid",appliedJobModelList.get(spinner_job.getSelectedIndex()).getJid());
                    jsonObject.put("linklocation","link location");
                    jsonObject.put("location",edt_location.getText().toString());
                    jsonObject.put("notime",checkbox_allday.isChecked()  ? "yes" : "No");
                    jsonObject.put("rid", candidateslist.get(spinner_candidate.getSelectedIndex()).getRid());
                    jsonObject.put("schtype", spinner_type.getSelectedIndex());
                    jsonObject.put("setdate", edt_date.getText().toString());
                    jsonObject.put("status", "0");
                    jsonObject.put("subject", edt_subject.getText().toString());
                    if(checkbox_allday.isChecked()){
                        jsonObject.put("timefrom", "13:00");
                        jsonObject.put("timeto", "17:00");
                    }else{
                        jsonObject.put("timefrom", edt_starttime.getText().toString());
                        jsonObject.put("timeto", edt_endtime.getText().toString());
                    }

                    jsonObject.put("timezone", Commons.timeZoneModels.get(spinner_timezone.getSelectedIndex()).getOffset());
                    jsonObject.put("ws_timezone", Commons.timeZoneModels.get(spinner_timezone.getSelectedIndex()).getOffset());
                    Log.d("Request",jsonObject.toString());
                    return jsonObject.toString().getBytes("utf-8");
                } catch (Exception e) { }

                return null;
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
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.edt_date:
                setDatePicker();
                break;
            case R.id.edt_starttime:
                timeType = 0;
                setTimePicker();
                break;
            case R.id.edt_endtime:
                timeType = 1;
                setTimePicker();
                break;
            case R.id.add_schedule:
                addSchedule();
                break;
        }
    }
}


