package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobTypeModel {
    String jobTypeCode,jobTypedesc;

    public String getJobTypeCode() {
        return jobTypeCode;
    }

    public void setJobTypeCode(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode;
    }

    public String getJobTypedesc() {
        return jobTypedesc;
    }

    public void setJobTypedesc(String jobTypedesc) {
        this.jobTypedesc = jobTypedesc;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setJobTypeCode(jsonObject.getString("jobTypeCode"));
            setJobTypedesc(jsonObject.getString("jobTypedesc"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
