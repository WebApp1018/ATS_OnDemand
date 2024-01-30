package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class EducationlevelModel {
    String DegreeName,DegreeOrder;

    public String getDegreeName() {
        return DegreeName;
    }

    public void setDegreeName(String degreeName) {
        DegreeName = degreeName;
    }

    public String getDegreeOrder() {
        return DegreeOrder;
    }

    public void setDegreeOrder(String degreeOrder) {
        DegreeOrder = degreeOrder;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setDegreeName(jsonObject.getString("DegreeName"));
            setDegreeOrder(jsonObject.getString("DegreeOrder"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
