package com.hr.pereless.activities.job.createJobStepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hr.pereless.R;
import com.hr.pereless.model.job.JobModel;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step8 extends Step<JobModel> {

    private EditText userNameView;

    public Step8(String stepTitle) {
        super(stepTitle);
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
        View view = inflater.inflate(R.layout.createjob_step8, null, false);
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
