package com.hr.pereless.activities.job.createJobStepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hr.pereless.R;
import com.hr.pereless.activities.job.CreateJobActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.model.job.BusinessUnitModel;
import com.hr.pereless.model.job.CountryStateModel;
import com.hr.pereless.model.job.JobModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step2 extends Step<JobModel> {

    CreateJobActivity content ;
    ArrayList<CountryStateModel>countries =  new ArrayList<>();
    ArrayList<CountryStateModel>status =  new ArrayList<>();
    EditText edt_city,edt_postalcode,edt_addlocation;
    MaterialSpinner spinner_country,spinner_state;
    public Step2(String stepTitle , CreateJobActivity createJobActivity) {
        super(stepTitle);
        this.content = createJobActivity;
        content.apiCall(API.GET_COUNTRIES);
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
        View view = inflater.inflate(R.layout.createjob_step2, null, false);
        edt_city = view.findViewById(R.id.edt_city);
        edt_addlocation = view.findViewById(R.id.edt_addlocation);
        edt_postalcode = view.findViewById(R.id.edt_postalcode);
        spinner_country = view.findViewById(R.id.spinner_country);
        spinner_state = view.findViewById(R.id.spinner_state);
        spinner_country.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                content.apiCall(API.GET_STATE + countries.get(position).getIso());
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

    public void setApiResponse(String json,boolean countryFlag){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            status.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                CountryStateModel countryStateModel = new CountryStateModel();
                countryStateModel.initModel(jsonObject);
                if(countryFlag)
                    countries.add(countryStateModel);
                else
                    status.add(countryStateModel);
            }

            if(countryFlag){
                List<String> array =  Arrays.asList(countries.stream().map(element -> element.getName()).toArray(String[]::new));
                spinner_country.setItems(array);
                content.apiCall(API.GET_STATE + countries.get(0).getIso());
            }else{
                List<String> array =  Arrays.asList(status.stream().map(element -> element.getName()).toArray(String[]::new));
                spinner_state.setItems(array);
            }

        }catch (Exception e){

        }
    }
    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }


}
