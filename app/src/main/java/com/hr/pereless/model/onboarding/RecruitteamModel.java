package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class RecruitteamModel {
    String Name,email,recruiter_id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setEmail(jsonObject.getString("email"));
            setName(jsonObject.getString("Name"));
            setRecruiter_id(jsonObject.getString("recruiter_id"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
