package com.hr.pereless.model.candidate;

import android.util.Log;

import com.hr.pereless.fragment.candidate.ScorecardModel;
import com.hr.pereless.fragment.candidate.SectionModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScorecardUserModel {
    String lastname,userlink,recruiter_id,firstname;
    int uid,role;
    ScorecardModel scorecardModel = new ScorecardModel();

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserlink() {
        return userlink;
    }

    public void setUserlink(String userlink) {
        this.userlink = userlink;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public ScorecardModel getScorecardModel() {
        return scorecardModel;
    }

    public void setScorecardModel(ScorecardModel scorecardModel) {
        this.scorecardModel = scorecardModel;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setLastname(jsonObject.getString("lastname"));
            setUserlink(jsonObject.getString("userlink"));
            setUid(jsonObject.getInt("uid"));
            setRole(jsonObject.getInt("role"));
            setFirstname(jsonObject.getString("firstname"));
            JSONObject groupObject = jsonObject.getJSONObject("group");
            scorecardModel.initDetailModel(groupObject);

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
