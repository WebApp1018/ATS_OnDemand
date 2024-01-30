package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobYearExperienceModel {
    String CID,YearExp,YearExpValue,aflag,language;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getYearExp() {
        return YearExp;
    }

    public void setYearExp(String yearExp) {
        YearExp = yearExp;
    }

    public String getYearExpValue() {
        return YearExpValue;
    }

    public void setYearExpValue(String yearExpValue) {
        YearExpValue = yearExpValue;
    }

    public String getAflag() {
        return aflag;
    }

    public void setAflag(String aflag) {
        this.aflag = aflag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setCID(jsonObject.getString("CID"));
            setYearExp(jsonObject.getString("YearExp"));
            setYearExpValue(jsonObject.getString("YearExpValue"));
            setAflag(jsonObject.getString("aflag"));
            setLanguage(jsonObject.getString("language"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
