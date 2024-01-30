package com.hr.pereless.activities.job;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.hr.pereless.R;
import com.hr.pereless.activities.job.createJobStepper.Step1;
import com.hr.pereless.activities.job.createJobStepper.Step10;
import com.hr.pereless.activities.job.createJobStepper.Step2;
import com.hr.pereless.activities.job.createJobStepper.Step3;
import com.hr.pereless.activities.job.createJobStepper.Step4;
import com.hr.pereless.activities.job.createJobStepper.Step5;
import com.hr.pereless.activities.job.createJobStepper.Step6;
import com.hr.pereless.activities.job.createJobStepper.Step7;
import com.hr.pereless.activities.job.createJobStepper.Step8;
import com.hr.pereless.activities.job.createJobStepper.Step9;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.fragment.candidate.ScorecardModel;
import com.hr.pereless.model.job.BusinessUnitModel;
import com.hr.pereless.model.job.JobTemplateModel;
import com.hr.pereless.model.job.JobkeywordModel;
import com.hr.pereless.model.job.RecruiterModel;
import com.hr.pereless.model.onboarding.RecruitteamModel;
import com.htmleditor.HtmlTextEditor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;
import kotlinx.coroutines.Job;

public class CreateJobActivity extends CommonActivity implements StepperFormListener {
    @BindView(R.id.stepper_form)
    VerticalStepperFormView stepper_form;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.img_back)
    ImageView img_back;
    private Step1 step1;
    private Step2 step2;
    private Step3 step3;
    private Step4 step4;
    private Step5 step5;
    private Step6 step6;
    private Step7 step7;
    private Step8 step8;
    private Step9 step9;

    private Step10 step10;

    public JobTemplateModel jobTemplateModel = new JobTemplateModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        ButterKnife.bind(this);

        initLayout();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(CreateJobActivity.this);
            }
        });

    }

    void initLayout() {

        txt_title.setText("Create Job");
        step1 = new Step1("Job Detail", this );
        step2 = new Step2("Location", this);
        step3 = new Step3("Salary",this);
        step4 = new Step4("Additional Options", this);
//        step5 = new Step5("Custom Fields",this);
//        step6 = new Step6("Users");
//        step7 = new Step7("Questions");
//        step8 = new Step8("Resource");
//        step9 = new Step9("Scorecard");
//        step10 = new Step10("Save as template?");

        stepper_form
                .setup(this, step1, step2,step3,step4)//,step5,step6,step7,step8,step9,step10)
                .allowNonLinearNavigation(true)
                .displayBottomNavigation(false)
                .lastStepNextButtonText("Create Job")
                .init();

        showProgress();
        apiCall(API.GET_JOBTEPLETE);
        apiCall(API.GET_KEYWORD);
        apiCall(API.GET_BUSINESSUNIT);
    }

    public void apiCall(String apiLInk){
        Map<String, String> params = new HashMap<>();
        params.put("Authorization", Commons.token);
        apiConnection( apiLInk,params, Request.Method.GET);
    }

    @Override
    public void parseResponse(String api_link,String json) {
        if(api_link.equals(API.GET_JOBTEPLETE))   {
            closeProgress();
            ArrayList<JobTemplateModel>jobTemplateModels = new ArrayList<>();
            ArrayList<String>jobtitleArray = new ArrayList<>();
            jobtitleArray.add("None");
            jobTemplateModels.add(new JobTemplateModel());

            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JobTemplateModel jobTemplateModel = new JobTemplateModel();
                    jobTemplateModel.initModel(jsonObject);
                    jobTemplateModels.add(jobTemplateModel);
                    jobtitleArray.add(jobTemplateModel.getJTjobkeyword());
                }
                step1.setJobTemplates(jobTemplateModels,jobtitleArray);
            }catch (Exception e){

            }
        }else if(api_link.equals(API.GET_KEYWORD))   {
            ArrayList<JobkeywordModel>jobkeywordModels = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JobkeywordModel jobTemplateModel = new JobkeywordModel();
                    jobTemplateModel.initModel(jsonObject);
                    jobkeywordModels.add(jobTemplateModel);
                }
                step1.setJobkeyword(jobkeywordModels);
            }catch (Exception e){

            }
        }else if(api_link.equals(API.GET_BUSINESSUNIT))   {
            ArrayList<BusinessUnitModel>businessUnitModels = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    BusinessUnitModel businessUnitModel = new BusinessUnitModel();
                    businessUnitModel.initModel(jsonObject);
                    businessUnitModels.add(businessUnitModel);
                }
                step1.setBusinessUit(businessUnitModels);
            }catch (Exception e){

            }
        }else if(api_link.equals(API.GET_COUNTRIES))   {
            step2.setApiResponse(json, true);
        }else if(api_link.contains(API.GET_STATE))   {
            step2.setApiResponse(json, false);
        }
        else if(api_link.contains(API.GET_JOBCATEGORIES) || api_link.contains(API.GET_EEOJOBCATEGORIES) || api_link.contains(API.GET_JOTTYPE)||
        api_link.contains(API.GET_JOBCATEGORIES) || api_link.contains(API.GET_JOBCATEGORIES) || api_link.contains(API.GET_JOBDURATION) ||
                api_link.contains(API.GET_LANGUAGE)|| api_link.contains(API.GET_EDUCATIONLEVEL)|| api_link.contains(API.GET_JOBCATEGORIES)||
                api_link.contains(API.GET_CONFIDENTIAL) || api_link.contains(API.GET_YEAREXPERIENCE) ||  api_link.contains(API.GET_JOBLEVEL)  ||  api_link.contains(API.GET_FREQUENCE)||
                api_link.contains(API.GET_JOBSUBCATEGORIES)){
            step4.setData(api_link,json);
        }
    }
    @Override
    public void onCompletedForm() {
        showProgress();

        JSONObject params = new JSONObject();
        try {
            params.put("expiredate","");
            params.put("trackingcode","");
            params.put("resources","");
            params.put("job_title",jobTemplateModel.getTemplateTitle());
            params.put("jobdescription",jobTemplateModel.getTDescription());
            params.put("numopening",jobTemplateModel.getJTnumopening());
            params.put("jobtype",jobTemplateModel.getJTjobtype());
            params.put("salary",jobTemplateModel.getSalary());
            params.put("salmax",jobTemplateModel.getJTsalaryrange());
            params.put("salcurrency",jobTemplateModel.getJTsalarycurrency());
            params.put("saltype",jobTemplateModel.getJTsaltype());
            params.put("priority",jobTemplateModel.getJTpriority());
            params.put("aflag",jobTemplateModel.getAflag());
            params.put("duration",jobTemplateModel.getJTjobduration());
            params.put("eeoccat",jobTemplateModel.getJTeeoc_job_cat());
            params.put("jobcat",jobTemplateModel.getJTcatID());
            params.put("department",jobTemplateModel.getJTdepartment());
            params.put("zip",jobTemplateModel.getJTZip());
            params.put("country",jobTemplateModel.getJTcountry());
            params.put("state",jobTemplateModel.getJTstate());
            params.put("location",jobTemplateModel.getJTjoblocation());
            params.put("City",jobTemplateModel.getJTcity());
            params.put("joblev",jobTemplateModel.getJTjoblevel());
            params.put("reqsexp",jobTemplateModel.getTDescription());
            params.put("experyrs",jobTemplateModel.getJTexpyears());
            params.put("degree",jobTemplateModel.getJTdegree());
            params.put("travel",jobTemplateModel.getJTtravel());
            params.put("start","null");
            params.put("intnotes",jobTemplateModel.getJTIntNotes());
            params.put("hmjobs","");
            params.put("rtjobs","");
            params.put("jobQ",jobTemplateModel.getJTJobQ_num());
            params.put("jobkeyword",jobTemplateModel.getJTjobkeyword());
            params.put("jobbenefits",jobTemplateModel.getJTbenefit());
            params.put("language",jobTemplateModel.getJTlanguage());
            params.put("joblocationstr","");
            params.put("Jobhours","");
            params.put("jobconfidential",jobTemplateModel.getJTtotalconfidential());
            params.put("jobowner",jobTemplateModel.getUID());
            params.put("sgid","");
            params.put("template_title","");
            params.put("customID","1609,1626");
            params.put("custID_noFvalue","");
            params.put("cfvalue_1609","");
            params.put("cfvalue_1626","");


        } catch (JSONException e) {

        }
        Log.d("Insert Job params ", params.toString());
        new BaseJsonObjectRequest(
                Request.Method.POST, API.POST_INSERTJOB, params,
                response -> {
                    closeProgress();
                    showToast("After today, there will be 7 days to allow the user to edit the position. It will be locked to make any further edits in the freemium account.\n");
                    finish(this);
                }, this::handleMultiPartResponseError);
    }

    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
        onCancelledForm();
    }

    @Override
    public void onCancelledForm() {

    }

    @Override
    public void onStepAdded(int index, Step<?> addedStep) {

    }

    @Override
    public void onStepRemoved(int index) {

    }
}