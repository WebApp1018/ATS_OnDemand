package com.hr.pereless.model.candidate.eeo;

import android.util.Log;

import org.json.JSONObject;

public class EthnicityModel {
    String EEOone2007,EEODescription,EEOoneOld,ethnicity,EthCode,EEOoneID,EEOone;
    int EEOorder,ethnicity_id;

    public String getEEOone2007() {
        return EEOone2007;
    }

    public void setEEOone2007(String EEOone2007) {
        this.EEOone2007 = EEOone2007;
    }

    public String getEEODescription() {
        return EEODescription;
    }

    public void setEEODescription(String EEODescription) {
        this.EEODescription = EEODescription;
    }

    public String getEEOoneOld() {
        return EEOoneOld;
    }

    public void setEEOoneOld(String EEOoneOld) {
        this.EEOoneOld = EEOoneOld;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthCode() {
        return EthCode;
    }

    public void setEthCode(String ethCode) {
        EthCode = ethCode;
    }

    public String getEEOoneID() {
        return EEOoneID;
    }

    public void setEEOoneID(String EEOoneID) {
        this.EEOoneID = EEOoneID;
    }

    public String getEEOone() {
        return EEOone;
    }

    public void setEEOone(String EEOone) {
        this.EEOone = EEOone;
    }

    public int getEEOorder() {
        return EEOorder;
    }

    public void setEEOorder(int EEOorder) {
        this.EEOorder = EEOorder;
    }

    public int getEthnicity_id() {
        return ethnicity_id;
    }

    public void setEthnicity_id(int ethnicity_id) {
        this.ethnicity_id = ethnicity_id;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setEEOone2007(jsonObject.getString("EEOone2007"));
            setEEOorder(jsonObject.getInt("EEOorder"));
            setEEODescription(jsonObject.getString("EEODescription"));
            setEEOoneOld(jsonObject.getString("EEOoneOld"));
            setEthnicity_id(jsonObject.getInt("ethnicity_id"));
            setEthnicity(jsonObject.getString("ethnicity"));
            setEthCode(jsonObject.getString("EthCode"));
            setEEOoneID(jsonObject.getString("EEOoneID"));
            setEEOone(jsonObject.getString("EEOone"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
