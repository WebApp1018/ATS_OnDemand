package com.hr.pereless.model.candidate.eeo;

import android.util.Log;

import org.json.JSONObject;

public class EeoDisposstionModel {
    String ProOrderID,EEOGroup,ProfileText,EEOAbbr,ProfileNewText;
    int PROUID,ProID,EEO_CID;

    public String getProOrderID() {
        return ProOrderID;
    }

    public void setProOrderID(String proOrderID) {
        ProOrderID = proOrderID;
    }

    public String getEEOGroup() {
        return EEOGroup;
    }

    public void setEEOGroup(String EEOGroup) {
        this.EEOGroup = EEOGroup;
    }

    public String getProfileText() {
        return ProfileText;
    }

    public void setProfileText(String profileText) {
        ProfileText = profileText;
    }

    public String getEEOAbbr() {
        return EEOAbbr;
    }

    public void setEEOAbbr(String EEOAbbr) {
        this.EEOAbbr = EEOAbbr;
    }

    public String getProfileNewText() {
        return ProfileNewText;
    }

    public void setProfileNewText(String profileNewText) {
        ProfileNewText = profileNewText;
    }

    public int getPROUID() {
        return PROUID;
    }

    public void setPROUID(int PROUID) {
        this.PROUID = PROUID;
    }

    public int getProID() {
        return ProID;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public int getEEO_CID() {
        return EEO_CID;
    }

    public void setEEO_CID(int EEO_CID) {
        this.EEO_CID = EEO_CID;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setProOrderID(jsonObject.getString("ProOrderID"));
            setEEOGroup(jsonObject.getString("EEOGroup"));
            setProfileText(jsonObject.getString("ProfileText"));
            setProID(jsonObject.getInt("PROUID"));
            setEEOAbbr(jsonObject.getString("EEOAbbr"));
            setProfileNewText(jsonObject.getString("ProfileNewText"));
            setProID(jsonObject.getInt("ProID"));
            setEEO_CID(jsonObject.getInt("EEO_CID"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
