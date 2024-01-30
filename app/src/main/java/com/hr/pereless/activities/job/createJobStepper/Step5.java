package com.hr.pereless.activities.job.createJobStepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.model.job.JobModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step5 extends Step<JobModel> {

    private EditText userNameView;
    CreateJobActivity content ;
    String[] sparkJobStatus = {"Yes","No"};
    CheckBox checkbox_remote, checkbox_office,checkbox_both,checkbox_first,checkbox_second,checkbox_third;
    RadioGroup group_year;
    MaterialSpinner spinner_sparkjob,spinner_indeed;
    public Step5(String stepTitle ,CreateJobActivity createJobActivity) {
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
        View view = inflater.inflate(R.layout.createjob_step5, null, false);
        checkbox_remote = view.findViewById(R.id.checkbox_remote);
        checkbox_office = view.findViewById(R.id.checkbox_office);
        checkbox_both = view.findViewById(R.id.checkbox_both);
        checkbox_first = view.findViewById(R.id.checkbox_first);
        checkbox_second = view.findViewById(R.id.checkbox_second);
        checkbox_third = view.findViewById(R.id.checkbox_third);
        group_year = view.findViewById(R.id.group_year);
        spinner_sparkjob = view.findViewById(R.id.spinner_sparkjob);
        spinner_indeed = view.findViewById(R.id.spinner_indeed);
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
