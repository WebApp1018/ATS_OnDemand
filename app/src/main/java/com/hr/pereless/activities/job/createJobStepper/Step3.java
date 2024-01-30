package com.hr.pereless.activities.job.createJobStepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.model.job.JobModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step3 extends Step<JobModel> {

    CreateJobActivity content ;
    RadioGroup radio_salary_group,salaryType;
    EditText edt_min, edt_max;
    MaterialSpinner spinner_currency;
    public Step3(String stepTitle , CreateJobActivity createJobActivity) {
        super(stepTitle);
        this.content = createJobActivity;
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
        View view = inflater.inflate(R.layout.createjob_step3, null, false);
        radio_salary_group = view.findViewById(R.id.radio_salary_group);
        edt_min = view.findViewById(R.id.edt_min);
        edt_max = view.findViewById(R.id.edt_max);
        radio_salary_group = view.findViewById(R.id.radio_salary_group);
        salaryType = view.findViewById(R.id.salaryType);
        spinner_currency = view.findViewById(R.id.spinner_currency);
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

}
