package com.hr.pereless.activities.job.createJobStepper;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.job.BusinessUnitModel;
import com.hr.pereless.model.job.JobModel;
import com.hr.pereless.model.job.JobTemplateModel;
import com.hr.pereless.model.job.JobkeywordModel;
import com.htmleditor.HtmlTextEditor;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import ernestoyaquello.com.verticalstepperform.Step;
public class Step1 extends Step<JobTemplateModel> {

    ArrayList<JobTemplateModel> jobTemplateModels = new ArrayList<>();
    ArrayList<JobkeywordModel> jobkeywordModels = new ArrayList<>();
    ArrayList<BusinessUnitModel> businessUnitModels = new ArrayList<>();
    MaterialSpinner spinner_templates , spinner_keyword,spinner_business_unit,spinner_department;
    EditText edt_jobbtitle;
    HtmlTextEditor html_jobdescription,html_jobbenefit;
    CreateJobActivity content ;
    public Step1(String stepTitle , CreateJobActivity createJobActivity) {
        super(stepTitle);
        this.content = createJobActivity;

    }

    @Override
    public JobTemplateModel getStepData() {

        return content.jobTemplateModel;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    protected void restoreStepData(JobTemplateModel data) {

    }

    @Override
    protected IsDataValid isStepDataValid(JobTemplateModel stepData) {
        if(stepData.getTemplateTitle() == null || stepData.getTemplateTitle().length() == 0 )
            return new IsDataValid(false, "Please input job Title");
        return new IsDataValid(true, "Please input job Title");
    }

    @Override
    protected View createStepContentLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.createjob_step1, null, false);
        spinner_templates = view.findViewById(R.id.spinner_templates);
        spinner_keyword = view.findViewById(R.id.spinner_keyword);
        spinner_business_unit = view.findViewById(R.id.spinner_business_unit);
        spinner_department = view.findViewById(R.id.spinner_department);
        edt_jobbtitle = view.findViewById(R.id.edt_jobbtitle);
        html_jobdescription = view.findViewById(R.id.html_jobdescription);
        html_jobbenefit = view.findViewById(R.id.html_jobdescription);

        spinner_templates.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                setupData(position);
            }
        });

        spinner_keyword.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            }
        });
        spinner_business_unit.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                List<String> array =  Arrays.asList(businessUnitModels.get(position).getDepartmentModels().stream().map(element -> element.getDepartmentName()).toArray(String[]::new));
                spinner_department.setItems(array);
            }
        });

        edt_jobbtitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setContextData();
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        return view;
    }

    @Override
    protected void onStepOpened(boolean animated) {

    }

    @Override
    protected void onStepClosed(boolean animated) {
        setContextData();
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }

    void setContextData(){
        content.jobTemplateModel = jobTemplateModels.get(spinner_templates.getSelectedIndex());
        content.jobTemplateModel.setTemplateTitle(edt_jobbtitle.getText().toString());
        content.jobTemplateModel.setTDescription(html_jobdescription.getText().toString());
        content.jobTemplateModel.setTDescription(html_jobbenefit.getText().toString());
        content.jobTemplateModel.setJTbenefit(jobkeywordModels.get(spinner_keyword.getSelectedIndex()).getHotbookName());
        content.jobTemplateModel.setBusinessunit(String.valueOf(businessUnitModels.get(spinner_business_unit.getSelectedIndex()).getId()));
        content.jobTemplateModel.setJTdepartment(String.valueOf(businessUnitModels.get(spinner_business_unit.getSelectedIndex()).getDepartmentModels().get(spinner_department.getSelectedIndex()).getDepartmentName()));
    }


    public void setBusinessUit(ArrayList<BusinessUnitModel> businessUnitModels){
        this.businessUnitModels = businessUnitModels;
        List<String> array =  Arrays.asList(businessUnitModels.stream().map(element -> element.getName()).toArray(String[]::new));
        spinner_business_unit.setItems(array);

        array =  Arrays.asList(businessUnitModels.get(0).getDepartmentModels().stream().map(element -> element.getDepartmentName()).toArray(String[]::new));
        spinner_department.setItems(array);
    }
    public void setJobkeyword(ArrayList<JobkeywordModel> jobkeywordModels){
        this.jobkeywordModels = jobkeywordModels;
        List<String> array =  Arrays.asList(jobkeywordModels.stream().map(element -> element.getHotbookName()).toArray(String[]::new));

        spinner_keyword.setItems(array);
    }
    public void setJobTemplates(ArrayList<JobTemplateModel> jobTemplateModels,ArrayList<String> arrayList){
        this.jobTemplateModels = jobTemplateModels;
        spinner_templates.setItems(arrayList);
    }
    private void setupData(int position){
        JobTemplateModel jobTemplateModel = jobTemplateModels.get(position);
        edt_jobbtitle.setText(jobTemplateModel.getTemplateTitle());

        List<String> array =  Arrays.asList(jobkeywordModels.stream().map(element -> element.getHotbookName()).toArray(String[]::new));
        int keywordPostion = array.indexOf(jobTemplateModel.getKeyword());
        if( keywordPostion >=0) spinner_keyword.setSelectedIndex(keywordPostion);
        html_jobdescription.setText(jobTemplateModel.getTDescription());
        html_jobbenefit.setText(jobTemplateModel.getJTbenefit());

    }
}
