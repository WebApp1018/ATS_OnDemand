package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobLevelModel {
    String CID,LevelName,LevelValue,aflag,language;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String levelName) {
        LevelName = levelName;
    }

    public String getLevelValue() {
        return LevelValue;
    }

    public void setLevelValue(String levelValue) {
        LevelValue = levelValue;
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
            setLevelName(jsonObject.getString("LevelName"));
            setLevelValue(jsonObject.getString("LevelValue"));
            setAflag(jsonObject.getString("aflag"));
            setLanguage(jsonObject.getString("language"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
