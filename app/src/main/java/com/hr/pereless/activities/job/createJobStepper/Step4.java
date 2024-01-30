package com.hr.pereless.activities.job.createJobStepper;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.model.job.EducationlevelModel;
import com.hr.pereless.model.job.JobCategoryModel;
import com.hr.pereless.model.job.JobDurationModel;
import com.hr.pereless.model.job.JobEEOModel;
import com.hr.pereless.model.job.JobLevelModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.model.job.JobSubcategoryModel;
import com.hr.pereless.model.job.JobTypeModel;
import com.hr.pereless.model.job.JobYearExperienceModel;
import com.hr.pereless.model.job.LanguageModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step4 extends Step<JobModel> {

    CreateJobActivity content ;
    MaterialSpinner spinner_jobcategory,spinner_subcategory,spinner_eeo_category,spinner_job_state,spinner_job_type
                ,spinner_language,spinner_level,spinner_experience,spinner_education,spinner_duration,spinner_frequence;
    EditText edt_openning,edt_comment;
    String[] statusArray = {"Active","Inactive","Potential","Closed","Approved"};
    RadioGroup group_jobpriority,group_confidential;

    ArrayList<JobCategoryModel>jobCategoryModels = new ArrayList<>();
    ArrayList<JobEEOModel>jobEEOModels = new ArrayList<>();
    ArrayList<JobSubcategoryModel>jobSubcategoryModels = new ArrayList<>();

    ArrayList<JobTypeModel>jobTypeModels = new ArrayList<>();
    ArrayList<LanguageModel>languageModels = new ArrayList<>();
    ArrayList<EducationlevelModel> educationlevelModels = new ArrayList<>();
    ArrayList<JobDurationModel> jobDurationModels = new ArrayList<>();
    ArrayList<JobDurationModel> jobtravelFrequencys = new ArrayList<>();
    ArrayList<JobDurationModel> jobconfidents = new ArrayList<>();
    ArrayList<JobYearExperienceModel> yearExperienceModels = new ArrayList<>();
    ArrayList<JobLevelModel> jobLevelModels = new ArrayList<>();


    public Step4(String stepTitle, CreateJobActivity createJobActivity) {
        super(stepTitle);
        this.content = createJobActivity;
        content.apiCall(API.GET_JOBCATEGORIES);
        content.apiCall(API.GET_EEOJOBCATEGORIES);
        content.apiCall(API.GET_JOTTYPE);
        content.apiCall(API.GET_LANGUAGE);
        content.apiCall(API.GET_EDUCATIONLEVEL);
        content.apiCall(API.GET_JOBDURATION);
        content.apiCall(API.GET_FREQUENCE);
        content.apiCall(API.GET_CONFIDENTIAL);
        content.apiCall(API.GET_YEAREXPERIENCE);
        content.apiCall(API.GET_JOBLEVEL);
    }

    @Override
    public JobModel getStepData() {
        return null;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    protected void restoreStepData(JobModel data) {

    }

    @Override
    protected IsDataValid isStepDataValid(JobModel stepData) {
        return null;
    }

    @Override
    protected View createStepContentLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.createjob_step4, null, false);
        spinner_jobcategory = view.findViewById(R.id.spinner_jobcategory);
        spinner_subcategory = view.findViewById(R.id.spinner_subcategory);
        spinner_eeo_category = view.findViewById(R.id.spinner_eeo_category);
        spinner_job_type = view.findViewById(R.id.spinner_job_type);
        spinner_language = view.findViewById(R.id.spinner_language);
        spinner_level = view.findViewById(R.id.spinner_level);
        spinner_experience = view.findViewById(R.id.spinner_experience);
        spinner_education = view.findViewById(R.id.spinner_education);
        spinner_job_state = view.findViewById(R.id.spinner_job_state);
        spinner_duration = view.findViewById(R.id.spinner_duration);
        spinner_frequence = view.findViewById(R.id.spinner_frequence);
        edt_openning = view.findViewById(R.id.edt_openning);
        edt_comment = view.findViewById(R.id.edt_comment);
        group_jobpriority = view.findViewById(R.id.group_jobpriority);
        group_confidential = view.findViewById(R.id.group_confidential);
        spinner_job_state.setItems(statusArray);

        spinner_jobcategory.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                content.apiCall(API.GET_JOBSUBCATEGORIES + jobCategoryModels.get(position).getCat_id());
            }
        });
        return view;
    }

    @Override
    protected void onStepOpened(boolean animated) {
    }

    @Override
    protected void onStepClosed(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }

    public void setData(String apilink, String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(apilink.equals(API.GET_JOBCATEGORIES)){
                    JobCategoryModel jobCategoryModel = new JobCategoryModel();
                    jobCategoryModel.initModel(jsonObject);
                    jobCategoryModels.add(jobCategoryModel);
                }else if(apilink.contains(API.GET_JOBSUBCATEGORIES)){
                    if(i == 0)jobSubcategoryModels.clear();
                    JobSubcategoryModel jobSubcategoryModel = new JobSubcategoryModel();
                    jobSubcategoryModel.initModel(jsonObject);
                    jobSubcategoryModels.add(jobSubcategoryModel);
                }else if(apilink.equals(API.GET_EEOJOBCATEGORIES)){
                    JobEEOModel jobEEOModel = new JobEEOModel();
                    jobEEOModel.initModel(jsonObject);
                    jobEEOModels.add(jobEEOModel);
                }else if(apilink.equals(API.GET_JOTTYPE)){
                    JobTypeModel jobTypeModel = new JobTypeModel();
                    jobTypeModel.initModel(jsonObject);
                    jobTypeModels.add(jobTypeModel);
                }else if(apilink.equals(API.GET_LANGUAGE)){
                    LanguageModel languageModel = new LanguageModel();
                    languageModel.initModel(jsonObject);
                    languageModels.add(languageModel);
                }else if(apilink.equals(API.GET_EDUCATIONLEVEL)){
                    EducationlevelModel educationlevelModel = new EducationlevelModel();
                    educationlevelModel.initModel(jsonObject);
                    educationlevelModels.add(educationlevelModel);
                }else if(apilink.equals(API.GET_JOBDURATION)){
                    JobDurationModel jobDurationModel = new JobDurationModel();
                    jobDurationModel.initModel(jsonObject);
                    jobDurationModels.add(jobDurationModel);

                }else if(apilink.equals(API.GET_FREQUENCE)){
                    JobDurationModel jobDurationModel = new JobDurationModel();
                    jobDurationModel.initModel(jsonObject);
                    jobtravelFrequencys.add(jobDurationModel);

                }else if(apilink.equals(API.GET_CONFIDENTIAL)){
                    JobDurationModel jobDurationModel = new JobDurationModel();
                    jobDurationModel.initModel(jsonObject);
                    jobconfidents.add(jobDurationModel);
                }else if(apilink.equals(API.GET_YEAREXPERIENCE)){
                    JobYearExperienceModel yearExperienceModel = new JobYearExperienceModel();
                    yearExperienceModel.initModel(jsonObject);
                    yearExperienceModels.add(yearExperienceModel);
                }else if(apilink.equals(API.GET_JOBLEVEL)){
                    JobLevelModel jobLevelModel = new JobLevelModel();
                    jobLevelModel.initModel(jsonObject);
                    jobLevelModels.add(jobLevelModel);
                }
            }
            if(apilink.equals(API.GET_JOBCATEGORIES)){
                List<String> array =  Arrays.asList(jobCategoryModels.stream().map(element -> element.getCatname()).toArray(String[]::new));
                spinner_jobcategory.setItems(array);
                content.apiCall(API.GET_JOBSUBCATEGORIES + jobCategoryModels.get(0).getCat_id());
            }else if(apilink.contains(API.GET_JOBSUBCATEGORIES)){
                List<String> array =  Arrays.asList(jobSubcategoryModels.stream().map(element -> element.getSubcatname()).toArray(String[]::new));
                spinner_subcategory.setItems(array);
            }else if(apilink.equals(API.GET_EEOJOBCATEGORIES)){
                List<String> array =  Arrays.asList(jobEEOModels.stream().map(element -> element.getCategory()).toArray(String[]::new));
                spinner_eeo_category.setItems(array);
            }else if(apilink.equals(API.GET_JOTTYPE)){
                List<String> array =  Arrays.asList(jobTypeModels.stream().map(element -> element.getJobTypedesc()).toArray(String[]::new));
                spinner_job_type.setItems(array);
            }else if(apilink.equals(API.GET_LANGUAGE)){
                List<String> array =  Arrays.asList(languageModels.stream().map(element -> element.getLanguageLocal()).toArray(String[]::new));
                spinner_language.setItems(array);
            }else if(apilink.equals(API.GET_EDUCATIONLEVEL)){
                List<String> array =  Arrays.asList(educationlevelModels.stream().map(element -> element.getDegreeName()).toArray(String[]::new));
                spinner_education.setItems(array);
            }else if(apilink.equals(API.GET_JOBDURATION)){
                List<String> array =  Arrays.asList(jobDurationModels.stream().map(element -> element.getReferenceName()).toArray(String[]::new));
                spinner_duration.setItems(array);
            }else if(apilink.equals(API.GET_FREQUENCE)){
                List<String> array =  Arrays.asList(jobtravelFrequencys.stream().map(element -> element.getReferenceName()).toArray(String[]::new));
                spinner_frequence.setItems(array);
            }else if(apilink.equals(API.GET_CONFIDENTIAL)){
                List<String> array =  Arrays.asList(jobconfidents.stream().map(element -> element.getReferenceName()).toArray(String[]::new));
            }else if(apilink.equals(API.GET_YEAREXPERIENCE)){
                List<String> array =  Arrays.asList(yearExperienceModels.stream().map(element -> element.getYearExp()).toArray(String[]::new));
                spinner_experience.setItems(array);
            }else if(apilink.equals(API.GET_JOBLEVEL)){
                List<String> array =  Arrays.asList(jobLevelModels.stream().map(element -> element.getLevelName()).toArray(String[]::new));
                spinner_level.setItems(array);
            }

        }catch (Exception e){

        }
    }

}
